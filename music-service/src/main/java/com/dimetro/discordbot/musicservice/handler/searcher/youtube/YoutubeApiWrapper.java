package com.dimetro.discordbot.musicservice.handler.searcher.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.searcher.YoutubeSearcherConfig;
import com.dimetro.discordbot.musicservice.handler.searcher.core.SearchApiWrapper;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.NetworkException;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.SongNotFoundException;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.youtube.exception.*;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

@Component
public class YoutubeApiWrapper extends SearchApiWrapper {
    
    private final YoutubeSearcherConfig config;

    private static final Gson gson = new Gson();

    private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search";
    private static final String VIDEOS_URL = "https://www.googleapis.com/youtube/v3/videos";
    private static final String PLAYLIST_URL = "https://www.googleapis.com/youtube/v3/playlistItems";

    @Autowired
    public YoutubeApiWrapper(YoutubeSearcherConfig config) {
        this.config = config;
    }

    private JsonObject searchJson(String url) {
        
        try {
            
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                
                switch (responseCode) {

                    case HttpURLConnection.HTTP_UNAUTHORIZED -> throw new UnauthorizedRequestException();
                    case HttpURLConnection.HTTP_NOT_FOUND -> throw new ResourceNotFoundException();
                    default -> throw new BadRequestException();

                }
            }

            StringBuilder response = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return gson.fromJson(response.toString(), JsonObject.class);
        } catch (MalformedURLException e) {
            throw new BadRequestException();
        } catch (IOException e) {
            throw new NetworkException(e);
        } 

    }


    @Override
    public SearchData searchSongById(String songId) {

        String url = String.format("%s?part=snippet&id=%s&type=video&key=%s", VIDEOS_URL, songId, config.getAPIKey());

        JsonObject result = searchJson(url);
        
        SearchData data = YoutubeSearchDataBuilder.build(result);

        if (data instanceof YoutubeVideoListData youtubeData) {

            if (youtubeData.getItems().isEmpty()) {
                throw new SongNotFoundException(songId);
            }

            return youtubeData.getItems().get(0);
        }

        throw new InvalidAPIResponseException(YoutubeVideoListData.class, data);
    }

    private String encodeSafe(String title) {
        try {
            return URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return title;
        }
    }

    @Override
    public SearchData searchSongByTitle(String title) {

        String url = String.format("%s?part=snippet&q=%s&type=video&key=%s", SEARCH_URL, encodeSafe(title), config.getAPIKey());

        JsonObject result = searchJson(url);

        SearchData data = YoutubeSearchDataBuilder.build(result);

        if (data instanceof YoutubeSearchListData youtubeData) {

            if (youtubeData.getItems().isEmpty()) {
                throw new SongNotFoundException(title);
            }

            return youtubeData.getItems().get(0);
        }

        throw new InvalidAPIResponseException(YoutubeSearchListData.class, data);
    }

    @Override
    public List<SearchData> searchSongsByPlaylistId(String playlistId) {

        String url = String.format(
            "%s?part=snippet&playlistId=%s&type=video&key=%s&maxResults=%d",
            PLAYLIST_URL, playlistId, config.getAPIKey(), 50);

        List<SearchData> searches = new ArrayList<>();

        String nextPageToken = null;

        do {

            String reqUrl = url;

            if (nextPageToken != null) {
                reqUrl += "&pageToken=" + nextPageToken;
            }

            JsonObject responseJson = searchJson(reqUrl);

            SearchData searchData = YoutubeSearchDataBuilder.build(responseJson);

            if (searchData instanceof YoutubePlaylistData youtubeData) {

                for (SearchData data : youtubeData.getItems()) {
                    searches.add(data);
                }

                Optional<String> pageToken = youtubeData.getNextPageToken();

                if (youtubeData.getNextPageToken().isPresent()) {
                    nextPageToken = pageToken.get();
                } else {
                    nextPageToken = null;
                }

            } else {
                throw new InvalidAPIResponseException(YoutubePlaylistData.class, searchData);
            }

        } while (nextPageToken != null);

        return searches;
    }

}
