package com.dimetro.discordbot.musicservice.handler.searcher.spotify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.config.searcher.SpotifySearcherConfig;
import com.dimetro.discordbot.musicservice.handler.searcher.core.SearchApiWrapper;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.NetworkException;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.SongNotFoundException;
import com.dimetro.discordbot.musicservice.handler.searcher.spotify.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.spotify.exception.*;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

@Component
public class SpotifyApiWrapper extends SearchApiWrapper {
    
    private final SpotifyApi spotifyAPI;
    private long expirationTime = 0;
    private final SpotifySearcherConfig config;

    @Autowired
    public SpotifyApiWrapper(SpotifySearcherConfig config) {
        spotifyAPI = new SpotifyApi.Builder()
                                .setClientId(config.getClientId())
                                .setClientSecret(config.getClientSecret())
                                .build();
        this.config = config;
    }

    private void checkCredentials() {

        long currentTime = System.currentTimeMillis() / 1000;

        if (currentTime < expirationTime - config.getCredentialsTimeout()) {
            return;
        } 
        
        try {
            
            ClientCredentials clientCredentials = spotifyAPI.clientCredentials().build().execute();
            spotifyAPI.setAccessToken(clientCredentials.getAccessToken());

            expirationTime = currentTime + clientCredentials.getExpiresIn();
    
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new CredentialsException(e);
        }
    }

    private Track getTrackById(String id) {

        checkCredentials();

        GetTrackRequest request = spotifyAPI.getTrack(id).build();

        try {
            return request.execute();
        } catch (IOException e) {
            throw new NetworkException(e);
        } catch (SpotifyWebApiException e) {
            throw new SpotifyApiException(e);
        } catch (ParseException e) {
            throw new DataParsingException(e);
        }
    }

    private Playlist getPlaylist(String playlistId) {

        checkCredentials();

        GetPlaylistRequest getPlaylistRequest = spotifyAPI.getPlaylist(playlistId).build();

        try {
            return getPlaylistRequest.execute();
        } catch (IOException e) {
            throw new NetworkException(e);
        } catch (SpotifyWebApiException e) {
            throw new SpotifyApiException(e);
        } catch (ParseException e) {
            throw new DataParsingException(e);
        }
    }

    private List<Track> searchPlaylistTracks(String playlistId) {

        Playlist playlist = getPlaylist(playlistId);
        List<Track> tracks = new ArrayList<>();

        for (PlaylistTrack pTrack : playlist.getTracks().getItems()) {
            tracks.add(getTrackById(pTrack.getTrack().getId()));
        }

        return tracks;
    }

    private Paging<Track> searchTrackByTitle(String title) {

        checkCredentials();

        SearchTracksRequest searchTracksRequest = spotifyAPI.searchTracks(title).build();
    
        try {
            return searchTracksRequest.execute();
        } catch (IOException e) {
            throw new NetworkException(e);
        } catch (SpotifyWebApiException e) {
            throw new SpotifyApiException(e);
        } catch (ParseException e) {
            throw new DataParsingException(e);
        }
    }

    private Album searchAlbum(String albumId) {

        checkCredentials();

        GetAlbumRequest getAlbumRequest = spotifyAPI.getAlbum(albumId).build();

        try {
            return getAlbumRequest.execute();
        } catch (IOException e) {
            throw new NetworkException(e);
        } catch (SpotifyWebApiException e) {
            throw new SpotifyApiException(e);
        } catch (ParseException e) {
            throw new DataParsingException(e);
        }
    }

    @Override
    public SearchData searchSongById(String id) {

        checkCredentials();

        Track track = getTrackById(id);

        return new SpotifyTrackData(track);
    }

    @Override
    public SearchData searchSongByTitle(String title) {

        Paging<Track> tracks = searchTrackByTitle(title);
        if (tracks.getItems().length == 0) {
            throw new SongNotFoundException(title);
        }
        return new SpotifyTrackData(tracks.getItems()[0]);
    }

    @Override
    public List<SearchData> searchSongsByPlaylistId(String playlistId) {
        List<Track> tracks = searchPlaylistTracks(playlistId);
        return tracks.stream()
                    .map(SpotifyTrackData::new).collect(Collectors.toList());
    }

    public List<SearchData> searchSongsByAlbumId(String albumId) {

        List<SearchData> data = new ArrayList<>();

        Album album = searchAlbum(albumId);

        for (TrackSimplified track : album.getTracks().getItems()) {
            data.add(new SpotifyAlbumTrackData(track, album.getImages()[0].getUrl()));
        }

        return data;
    }

}
