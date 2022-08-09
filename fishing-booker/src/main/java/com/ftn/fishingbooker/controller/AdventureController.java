package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.dto.MultipleFilesUploadDto;
import com.ftn.fishingbooker.mapper.AdventureMapper;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.util.FIleUploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/adventures")
public class AdventureController {

    private final AdventureService adventureService;

    public AdventureController(AdventureService adventureService){
        this.adventureService = adventureService;
    }

    @GetMapping
    public ResponseEntity<Collection<AdventureDto>> getAllAdventures() {
        Collection<Adventure> found = adventureService.getAll();

        Collection<AdventureDto> dtos = found.stream()
                .map(AdventureMapper::mapToDto)
                .collect(Collectors.toList());

        return ok(dtos);
    }

    @PostMapping("/image-upload/{id}")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile[] files, @PathVariable Long id)  {

        String uploadDir = "images/adventures/" + id;

        Arrays.asList(files).forEach(file -> {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                FIleUploadUtil.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            adventureService.addImage(id, fileName);
        });

        return ResponseEntity.ok().build();
    }

}
