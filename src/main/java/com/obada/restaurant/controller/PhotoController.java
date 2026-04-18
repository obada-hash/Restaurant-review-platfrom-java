package com.obada.restaurant.controller;


import com.obada.restaurant.domain.dto.PhotoDto;
import com.obada.restaurant.domain.entity.Photo;
import com.obada.restaurant.mapper.PhotoMapper;
import com.obada.restaurant.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public ResponseEntity<PhotoDto> create(@RequestParam("file") MultipartFile file) {

        PhotoDto photoDto = photoMapper.toDto(photoService.uploadPhoto(file));
        return new ResponseEntity<>(photoDto, HttpStatus.CREATED);
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String photoId) {

        return photoService.getPhotoAsResource(photoId).
                map(photo ->
                        ResponseEntity.ok()
                                .contentType(MediaTypeFactory.getMediaType(photo)
                                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline").body(photo))
                .orElse(ResponseEntity.notFound().build());

    }
}
