package com.dimetro.discordbot.musicservice.handler.downloader.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;

import com.dimetro.discordbot.musicservice.handler.downloader.core.exception.DirectoryCreationException;

@Component
public class FileService {
    
    public boolean fileExists(String path) {
        return new File(path).exists();
    }

    public File createDirectory(String path) {
        
        File file = new File(path);

        if (file.exists() || file.mkdirs()) {
            return file;
        }
        
        throw new DirectoryCreationException(path);
    }

    public void moveFile(String sourcePath, String destinationPath) throws IOException {
        Files.move(new File(sourcePath).toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
