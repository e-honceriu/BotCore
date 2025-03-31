package com.dimetro.discordbot.musicservice.playlist.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "playlist",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "botId", "guildDiscordId"})
    }
)
public class Playlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="title", nullable = false, unique = false)
    private String title;

    @Column(name="bot_id", nullable = false)
    private UUID botId;

    @Column(name="owner_discord_id", nullable = false)
    private String ownerDiscordId;

    @Column(name="guild_discord_id", nullable = false)
    private String guildDiscordId;

    @OneToMany(mappedBy="playlist", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<PlaylistSong> songs = new ArrayList<>();

    public void addBarebonePlaylistSong(PlaylistSong barebonePlaylistSong) {
        barebonePlaylistSong.setPlaylist(this);
        barebonePlaylistSong.setPosition(songs.size() + 1);
        songs.add(barebonePlaylistSong);
    }

    public boolean removeSong(UUID songId) {
        if (songId == null) {
            return false;
        }
    
        PlaylistSong songToRemove = songs.stream()
                                         .filter(playlistSong -> playlistSong.getSong().getId().equals(songId))
                                         .findFirst()
                                         .orElse(null);
    
        if (songToRemove == null) {
            return false;
        }
    
        int removedSongPosition = songToRemove.getPosition();
    
        songs.stream()
             .filter(song -> song.getPosition() > removedSongPosition)
             .forEach(song -> song.setPosition(song.getPosition() - 1));
    
        return songs.remove(songToRemove);
    }
    
    public boolean removeSong(Integer position) {
        
        if (position == null || position < 1) {
            return false;
        }
    
        PlaylistSong songToRemove = songs.stream()
                                         .filter(playlistSong -> playlistSong.getPosition() == position)
                                         .findFirst()
                                         .orElse(null);
    
        if (songToRemove == null) {
            return false;
        }
    
        songs.stream()
             .filter(song -> song.getPosition() > position)
             .forEach(song -> song.setPosition(song.getPosition() - 1));
    
        return songs.remove(songToRemove);
    }

}
