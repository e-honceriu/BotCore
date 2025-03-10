package com.dimetro.discordbot.musicservice.playlist.entity;

import lombok.*;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.song.entity.Song;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="playlist_song")
public class PlaylistSong {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name="song_id", nullable = false)
    private Song song;

    @Column(name="position", nullable = false)
    private int position;

}
