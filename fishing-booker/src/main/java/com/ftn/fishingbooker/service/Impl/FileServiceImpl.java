package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    @Autowired
    private VacationHomeRepository vacationHomeRepository;

    public FileEntity save(MultipartFile file, VacationHome vacationHome) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setVacationHome(vacationHome);

        return fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFile(String id) {
        return fileRepository.findById(id);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public List<FileEntity> getAllForHome(Long homeId) {
        List<FileEntity> fileEntities = fileRepository.getAllByVacationHomeId(homeId);
        return  fileEntities;
    }

    @Override
    public List<FileEntity> saveVacationHomeImages(List<MultipartFile> images,Long id) throws IOException {
        List<FileEntity> fileEntities = new ArrayList<FileEntity>();
        VacationHome vacationHome = vacationHomeRepository.getById(id);
        for (MultipartFile image : images) {
            FileEntity fileEntity = save(image,vacationHome);
            fileEntities.add(fileEntity);
        }
        return  fileEntities;
    }


}
