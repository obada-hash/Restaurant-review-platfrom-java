package com.obada.restaurant.service.impl;


import com.obada.restaurant.domain.entity.Photo;
import com.obada.restaurant.service.PhotoService;
import com.obada.restaurant.service.StorageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final StorageService storageService;


    @Override
    public Photo uploadPhoto(MultipartFile file) {
        String photoId= UUID.randomUUID().toString(); // conflicts are close to zero
        String url = storageService.store(file, photoId);
        return Photo.builder()
                .url(url)
                .uploadDate(LocalDateTime.now())
                .build();
    }

    @Override
    public Optional<Resource> getPhotoAsResource(String id) {
        return storageService.loadAsResource(id);
    }
}
