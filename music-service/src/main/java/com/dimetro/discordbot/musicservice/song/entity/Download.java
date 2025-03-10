package com.dimetro.discordbot.musicservice.song.entity;

import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "song_download"
)
public class Download {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DownloadStatus status;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "finish_time", nullable = true)
    private LocalDateTime finishTime;

}
