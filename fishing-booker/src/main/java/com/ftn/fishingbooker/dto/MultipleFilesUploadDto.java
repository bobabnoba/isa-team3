package com.ftn.fishingbooker.dto;

import org.springframework.web.multipart.MultipartFile;

public class MultipleFilesUploadDto {

    public Long adventureId;
    public MultipartFile[] files;
    public String[] imageNames;

    public Long getAdventureId() {
        return adventureId;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public String[] getImageNames() {
        return imageNames;
    }
}
