package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ImageDto;
import com.ftn.fishingbooker.model.Image;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ImageMapper {
    public static Collection<ImageDto> map(Collection<Image> images) {
        Collection<ImageDto> imagesDto = new ArrayList<>();

        for (Image image : images
        ) {
            ImageDto imageDto = new ImageDto();
            imageDto.setUrl(image.getUrl());
            imagesDto.add(imageDto);
        }
        return imagesDto;
    }
}
