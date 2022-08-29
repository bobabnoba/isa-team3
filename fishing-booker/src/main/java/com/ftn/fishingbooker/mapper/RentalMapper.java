package com.ftn.fishingbooker.mapper;


import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.VacationHome;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class RentalMapper {
    public static Collection<RentalDto> mapBoatToRental(Collection<Boat> boats) {

        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();

        if (boats == null) {
            return rentals;
        }
        for (Boat boat : boats) {
            rentals.add(mapToRental(boat));

        }
        return rentals;
    }


    public static Collection<RentalDto> mapInstructorToRental(Collection<Instructor> instructors) {

        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();

        if (instructors == null) {
            return rentals;
        }
        for (Instructor instructor : instructors) {
            rentals.add(mapToRental(instructor));

        }
        return rentals;
    }

    public static Collection<RentalDto> mapAdventureToRental(Collection<Adventure> adventures) {

        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();
        if (adventures == null) {
            return rentals;
        }
        for (Adventure adventure : adventures) {
            rentals.add(mapToRental(adventure));
        }
        return rentals;
    }

    public static Collection<RentalDto> mapVacationHomeToRental(Collection<VacationHome> homes) {
        ArrayList<RentalDto> rentals = new ArrayList<>();
        if (homes == null) {
            return rentals;
        }
        for (VacationHome home : homes) {
            rentals.add(mapToRental(home));
        }
        return rentals;
    }

    private static RentalDto mapToRental(Boat boat) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(boat.getId());
        rentalDto.setAddress(boat.getAddress());
        rentalDto.setDescription(boat.getDescription());
        rentalDto.setName(boat.getName());
        rentalDto.setRating(boat.getRating());
        rentalDto.setRentalType(RentalType.boat);
        rentalDto.setPricePerDay(boat.getPricePerDay());
        rentalDto.setUtilities(UtilityMapper.map(boat.getUtilities()));
        rentalDto.setOwner(OwnerMapper.map(boat.getBoatOwner()));
        rentalDto.setImages( boat.getImagePaths());
        rentalDto.setCancelingPercentage(boat.getCancelingPercentage());

        return rentalDto;
    }

    public static RentalDto mapToRental(Adventure adventure) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(adventure.getId());
        rentalDto.setAddress(adventure.getAddress());
        rentalDto.setDescription(adventure.getDescription());
        rentalDto.setName(adventure.getTitle());
        rentalDto.setRating(adventure.getRating());
        rentalDto.setRentalType(RentalType.adventure);
        rentalDto.setPricePerDay(adventure.getPricePerDay());
        rentalDto.setUtilities(UtilityMapper.map(adventure.getUtilities()));
        rentalDto.setOwner(OwnerMapper.map(adventure.getInstructor()));
        rentalDto.setDuration(adventure.getDurationInHours());
        rentalDto.setImages( adventure.getImagePaths());
        rentalDto.setCancelingPercentage(adventure.getCancelingPercentage());

        return rentalDto;
    }

    public static RentalDto mapToRental(VacationHome home) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(home.getId());
        rentalDto.setAddress(home.getAddress());
        rentalDto.setDescription(home.getDescription());
        rentalDto.setName(home.getName());
        rentalDto.setRating(home.getRating());
        rentalDto.setRentalType(RentalType.home);
        rentalDto.setPricePerDay(home.getPricePerDay());
        rentalDto.setUtilities(UtilityMapper.map(home.getUtilities()));
        rentalDto.setOwner(OwnerMapper.map(home.getHomeOwner()));
        rentalDto.setImages( home.getImagePaths());
        rentalDto.setCancelingPercentage(home.getCancelingPercentage());

        return rentalDto;
    }

    public static RentalDto mapToRental(Instructor instructor) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(instructor.getId());
        rentalDto.setAddress(instructor.getAddress());
        rentalDto.setDescription(instructor.getBiography());
        rentalDto.setName(instructor.getFirstName() + " " + instructor.getLastName());
        rentalDto.setRating(instructor.getRating());
        rentalDto.setRentalType(RentalType.instructor);
        return rentalDto;
    }

}
