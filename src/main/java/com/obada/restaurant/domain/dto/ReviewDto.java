package com.obada.restaurant.domain.dto;

import com.obada.restaurant.domain.entity.Photo;
import com.obada.restaurant.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private String id;

    private String content;

    private Integer rating;

    private LocalDateTime datePosted;

    private LocalDateTime lastUpdated;

    private List<PhotoDto> photos = new ArrayList<>();

    private UserDto writtenBy;
}
