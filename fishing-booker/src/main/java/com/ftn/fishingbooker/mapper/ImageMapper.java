package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ImageDto;
import com.ftn.fishingbooker.model.Image;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ImageMapper {
    public static Set<ImageDto> map(Set<Image> images) {
        Set<ImageDto> imagesDto = new HashSet<>();

        for (Image image : images
        ) {
            ImageDto imageDto = new ImageDto();
            imageDto.setUrl(image.getUrl());
            imagesDto.add(imageDto);
        }
        return imagesDto;
    }
}
