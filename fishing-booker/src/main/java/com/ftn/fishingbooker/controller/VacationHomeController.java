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
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/images/{id}")
    public ResponseEntity<List<FileEntity>> get(@PathVariable Long id) {
        try {
            List<FileEntity> images  = fileService.getAllForHome(id);
            return new ResponseEntity<List<FileEntity>>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<FileEntity>>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: dodati autorizaciju
    @PostMapping(value = {"/addNew"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<VacationHomeDto> addNewVacationHome(@RequestPart("home") NewVacationHomeDto home,
                                                              @RequestPart("imageFile") MultipartFile[] files){
        try{
            String userEmail = "mejlic2@mejl.com"; //TODO: izvuci iz autorizacije
            HomeOwner owner = (HomeOwner) userRepository.findByEmail(userEmail);
            VacationHome vacationHome = vacationHomeService.createVacationHome(home,owner);
            String fileNames = "";
            System.out.println("uuu");
            for (MultipartFile file: files) {
                fileService.save(file,vacationHome);
                fileNames.concat(file.getOriginalFilename()).concat(", ");
            }
            List<FileEntity> images  = fileService.getAllForHome(vacationHome.getId());
            VacationHomeDto vacationHomeDto = vacationHomeMapper.mapToVacationHomeDto(vacationHome,images);

            return new ResponseEntity<VacationHomeDto>(vacationHomeDto,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
