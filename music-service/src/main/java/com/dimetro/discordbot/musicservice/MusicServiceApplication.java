package com.dimetro.discordbot.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dimetro.discordbot")
public class MusicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServiceApplication.class, args);
	}

}
