package com.dimetro.discordbot.musicservice.playlist.service.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;
import com.dimetro.discordbot.musicservice.playlist.dto.request.playlist.CreatePlaylistRequestDTO;
import com.dimetro.discordbot.musicservice.playlist.dto.request.song.*;
import com.dimetro.discordbot.musicservice.playlist.dto.response.playlist.PlaylistResponseDTO;
import com.dimetro.discordbot.musicservice.playlist.entity.PlaylistSong;
import com.dimetro.discordbot.musicservice.playlist.service.playlist.exception.SongNotFoundInPlaylistException;
import com.dimetro.discordbot.musicservice.playlist.service.playlistsong.PlaylistSongService;

@Service
public class PlaylistManagementService {
    
    private final PlaylistService playlistService;
    private final PlaylistSongService playlistSongService;

    public PlaylistManagementService(PlaylistService playlistService, PlaylistSongService playlistSongService) {
        this.playlistService = playlistService;
        this.playlistSongService = playlistSongService;
    }

    public PlaylistResponseDTO getPlaylistByTitle(String title, UUID botId, String guildDiscordId) {
        Playlist playlist = playlistService.getPlaylistOrThrow(title, botId, guildDiscordId);
        return PlaylistResponseDTO.build(playlist);
    }

    public PlaylistResponseDTO getPlaylistById(UUID playlistId, UUID botId) {
        Playlist playlist = playlistService.getPlaylistOrThrow(playlistId, botId);
        return PlaylistResponseDTO.build(playlist);
    }

    public List<PlaylistResponseDTO> getPlaylistsByGuildDiscordId(String guildDiscordId, UUID botId) {
        List<Playlist> playlists = playlistService.getPlaylistByDiscordGuildId(guildDiscordId, botId);
        return playlists.stream()
                    .map(PlaylistResponseDTO::build).collect(Collectors.toList());
    }

    public PlaylistResponseDTO createPlaylist(CreatePlaylistRequestDTO requestDTO) {
        Playlist playlist = playlistService.createPlaylist(
                                    requestDTO.getTitle(), requestDTO.getOwnerDiscordId(), 
                                    requestDTO.getBotId(), requestDTO.getGuildDiscordId()
                                    );
        return PlaylistResponseDTO.build(playlist);                        
    }

    public PlaylistResponseDTO addSongsToPlaylistById(AddSongsToPlaylistByIdRequestDTO requestDTO) {

        Playlist playlist = playlistService.getPlaylistAuthOrThrow(requestDTO.getPlaylistId(), requestDTO.getRequesterDiscordId(), requestDTO.getBotId());

        List<PlaylistSong> pSongs = new ArrayList<>();

        if (requestDTO.getSongIds() != null) {

            for (UUID songId : requestDTO.getSongIds()) {
                pSongs.add(playlistSongService.createBarebonePlaylistSongBySongId(songId));
            }

        } else if (requestDTO.getSongExternalIds() != null) {

            for (String extId : requestDTO.getSongExternalIds()) {
                pSongs.add(playlistSongService.createBareBonePlaylistSongByExternalId(requestDTO.getPlatform(), extId));
            }

        }

        for (PlaylistSong pSong : pSongs) {
            playlist.addBarebonePlaylistSong(pSong);
        }

        return PlaylistResponseDTO.build(playlistService.savePlaylist(playlist));
    }

    public PlaylistResponseDTO addSongsToPlaylistByTitle(AddSongsToPlaylistByTitleRequestDTO requestDTO) {
        
        Playlist playlist = playlistService.getPlaylistAuthOrThrow(requestDTO.getPlaylistId(), requestDTO.getRequesterDiscordId(), requestDTO.getBotId());

        for (String title : requestDTO.getSongTitles()) {
            PlaylistSong pSong = playlistSongService.createBareBonePlaylistSongByTitle(requestDTO.getPlatform(), title);
            playlist.addBarebonePlaylistSong(pSong);
        }

        return PlaylistResponseDTO.build(playlistService.savePlaylist(playlist));
    }

    public PlaylistResponseDTO removeSongFromPlaylist(UUID playlistId, UUID botId, String requesterDiscordId, UUID songId, Integer position) {
        
        Playlist playlist = playlistService.getPlaylistAuthOrThrow(playlistId, requesterDiscordId, botId);
        boolean removed;

        if (songId != null) {
            removed = playlist.removeSong(songId);
        } else if (position != null) {
            removed =  playlist.removeSong(position);
        } else {
            return PlaylistResponseDTO.build(playlist);
        }

        if (!removed) {
            throw new SongNotFoundInPlaylistException(playlist.getId(), songId, position);
        }

        return PlaylistResponseDTO.build(playlistService.savePlaylist(playlist));
    }

    public void deletePlaylist(UUID playlistId, String requesterDiscordId, UUID botId) {
        playlistService.deletePlaylist(playlistId, requesterDiscordId, botId);
    }

}
