package com.dimetro.discordbot.musicservice.song.entity;

import lombok.*;

import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false, unique = false)
    private String title;

    @Column(name = "thumbnail_url", nullable = false, unique = false)
    private String thumbnailUrl;

    @Column(name = "audio_file_path", nullable = true, unique = true)
    private String audioFilePath;

    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExternalId externalId;
    
}
