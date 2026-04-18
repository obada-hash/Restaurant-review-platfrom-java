package com.obada.restaurant.service.impl;


import com.obada.restaurant.exception.StorageException;
import com.obada.restaurant.service.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class FileStorageService implements StorageService {


    @Value("${app.storage.location:photos}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct // after the class is constructed
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("could not initialize storage location", e);
        }
    }


    @Override
    public String store(MultipartFile file, String fileName) {

        try {
            if (file.isEmpty()) {
                throw new StorageException("file is empty");
            }
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFileName = fileName + "." + extension;

            Path destinationFile = rootLocation
                    .resolve(Paths.get(finalFileName))
                    .normalize()
                    .toAbsolutePath(); // directory traversal

            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("can not store file outside specified dir");
            } // check file size &

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING); // replace with asynchronous
            }

            return finalFileName;

        } catch (IOException e) {
            throw new StorageException("could not store file", e);
        }
    }

    @Override
    public Optional<Resource> loadAsResource(String fileName) {
        Path filePath = rootLocation.resolve(Paths.get(fileName));

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return Optional.of(resource);
            } else {
                return Optional.empty();
            }
        } catch (MalformedURLException e){
            log.warn("could not read file {}", fileName, e);
            return Optional.empty(); // I didn't use StorageException because I didn't treat that as
            // an exception (I think a lot of requst will be made while the file doesn't exist)
        }
    }
}
