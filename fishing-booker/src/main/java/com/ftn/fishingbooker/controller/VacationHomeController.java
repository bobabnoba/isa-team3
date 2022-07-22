package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.mapper.*;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.*;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.util.*;

@ControllerAdvice
@RestController
@RequestMapping("/home")
public class VacationHomeController {

    @Autowired
    private VacationHomeRepository vacationHomeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VacationHomeMapper vacationHomeMapper;
    @Autowired
    private VacationHomeService vacationHomeService;
    @Autowired
    private FileService fileService;

    @PreAuthorize("hasRole('HOME_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<VacationHomeDto> getVacationHome(@PathVariable Long id){
        try{
            Optional<VacationHome> vh = vacationHomeRepository.findById(id);
            List<FileEntity> images  = fileService.getAllForHome(vh.get().getId());
            VacationHomeDto vhDto = vacationHomeMapper.mapToVacationHomeDto(vh.get(),images);
            return new ResponseEntity<VacationHomeDto>(vhDto,HttpStatus.OK);
        }catch (Exception e ){
            e.getMessage();
        }
        return new ResponseEntity<VacationHomeDto>(HttpStatus.CONFLICT);
    }

    //TODO: dodati autorizaciju
    @PostMapping("/")
    public ResponseEntity<VacationHomeDto> createVacationHome(@RequestBody NewVacationHomeDto newVacationHomeDto){
        try{
            String userEmail = "mejlic2@mejl.com"; //TODO: izvuci iz autorizacije
            HomeOwner owner = (HomeOwner) userRepository.findByEmail(userEmail);
            VacationHome vacationHome = vacationHomeService.createVacationHome(newVacationHomeDto,owner);
            //List<FileEntity> images = fileService.saveVacationHomeImages(newVacationHomeDto.images,vacationHome.getId());
            List<FileEntity> images  = fileService.getAllForHome(13L);
            VacationHomeDto vacationHomeDto = vacationHomeMapper.mapToVacationHomeDto(vacationHome,images);

            return new ResponseEntity<VacationHomeDto>(vacationHomeDto,HttpStatus.OK);
        }catch (Exception e ){
            e.getMessage();
        }
        return new ResponseEntity<VacationHomeDto>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/images")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile[] files) {
        String fileNames = "";
        try {
            for (MultipartFile file: files) {
                VacationHome vacationHome = vacationHomeRepository.getById(13L);
                fileService.save(file,vacationHome);
                fileNames.concat(file.getOriginalFilename()).concat(", ");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", fileNames));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", fileNames));
        }
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<List<FileEntity>> get(@PathVariable Long id) {
        try {
            List<FileEntity> images  = fileService.getAllForHome(id);
            return new ResponseEntity<List<FileEntity>>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<FileEntity>>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
