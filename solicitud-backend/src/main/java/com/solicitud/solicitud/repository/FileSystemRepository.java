package com.solicitud.solicitud.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

@Repository
public class FileSystemRepository {
    String RESOURCES_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public String saveImageFileSystem(byte[] content, String imageName) throws Exception{
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);

        return newFile.toAbsolutePath().toString();
    }

    //Buscar imagen de los archivos del sistema
    public FileSystemResource findInFileSystem(String location) {
        try{
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void deleteByPath(String location) {
        Path path = Paths.get(location);
        try {
            // Delete file or directory
            Files.delete(path);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
