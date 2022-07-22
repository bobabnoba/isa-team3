package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

public interface FileService {
    FileEntity save(MultipartFile file,VacationHome vacationHome) throws IOException;
    List<FileEntity> getAllFiles();
    Optional<FileEntity> getFile(String id);
    List<FileEntity> getAllForHome(Long homeId);

    List<FileEntity> saveVacationHomeImages(List<MultipartFile> images,Long id) throws IOException;
}
