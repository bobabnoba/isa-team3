package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.*;
import com.ftn.fishingbooker.model.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class VacationHomeMapper {

    public VacationHomeDto mapToVacationHomeDto(VacationHome vacationHome, List<FileEntity> images){
        VacationHomeDto vDto =  new VacationHomeDto();
        vDto.id = vacationHome.getId();
        vDto.name = vacationHome.getName();
        vDto.address = vacationHome.getAddress();
        vDto.description = vacationHome.getDescription();
        //vDto.bedRoom = vacationHome.getBedRoom();
        vDto.codeOfConduct = vacationHome.getCodeOfConduct();
        //vDto.priceList = vacationHome.getPriceList();
        vDto.information = vacationHome.getInformation();
        if(images != null){
            vDto.images  = getImages(images);
        }
        return vDto;
    }

    private List<byte[]> getImages(List<FileEntity> images) {
        List<byte[]> imgData  = new ArrayList<byte[]>();
        for (FileEntity img : images) {
            imgData.add(img.getData());
        }
        return imgData;
    }

}
