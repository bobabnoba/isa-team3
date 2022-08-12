package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.dto.NewAdventureDto;
import com.ftn.fishingbooker.mapper.AdventureMapper;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.service.AdventureService;
import com.ftn.fishingbooker.util.FIleUploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{id}")
    public ResponseEntity<AdventureDto> getAdventureById(@PathVariable Long id){
        Adventure found = adventureService.getById(id);
        AdventureDto dto = AdventureMapper.mapToDto(found);
        return ok(dto);
    }

    @PostMapping
    public ResponseEntity<AdventureDto> addNewAdventure(@RequestBody NewAdventureDto dto) {
        Adventure adventure = AdventureMapper.toEntity(dto);
        Adventure saved = adventureService.addAdventure(adventure, dto.getInstructorEmail());
        return ok(AdventureMapper.mapToDto(saved));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/info-update/{id}")
    public ResponseEntity<AdventureDto> updateAdventureInfo(@PathVariable Long id, @RequestBody AdventureDto dto) {
        Adventure adventure = AdventureMapper.mapToEntity(dto);
        Adventure updated = adventureService.updateAdventureInfo(id, adventure);
        return ResponseEntity.ok(AdventureMapper.mapToDto(updated));
    }

    @PostMapping("/image-upload/{id}")
    public ResponseEntity<Object> uploadImages(@RequestParam MultipartFile file, @PathVariable Long id) throws  IOException{

        String uploadDir = "images/adventures/" + id;

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleUploadUtil.saveFile(uploadDir, fileName, file);
        adventureService.addImage(id, fileName);

        return ResponseEntity.ok().build();
    }

}
