package com.dimetro.discordbot.securityservice.security.core.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "bot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "blocked", nullable = false)
    private boolean blocked;

    @OneToOne(mappedBy = "bot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ApiKey apiKey;

}
