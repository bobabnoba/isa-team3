package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.model.adventure.Adventure;
import com.ftn.fishingbooker.model.boat.Boat;
import com.ftn.fishingbooker.model.home.VacationHome;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RentalMapper {
    private static String url = "http://localhost:8090/";

    public static ArrayList<RentalDto> mapBoatToRental(ArrayList<Boat> boats) {

        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();
        for (Boat boat : boats) {
            rentals.add(mapToRental(boat));

        }
        return rentals;
    }

    public static ArrayList<RentalDto> mapInstructorToRental(ArrayList<Adventure> adventures) {

        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();
        for (Adventure adventure : adventures) {
            rentals.add(mapToRental(adventure));

        }
        return rentals;
    }

    public static ArrayList<RentalDto> mapVacationHomeToRental(ArrayList<VacationHome> homes) {
        ArrayList<RentalDto> rentals = new ArrayList<RentalDto>();
        for (VacationHome home : homes) {
            rentals.add(mapToRental(home));

        }
        return rentals;
    }

    public static RentalDto mapToRental(Boat boat) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setAddress(boat.getAddress());
        rentalDto.setDescription(boat.getDescription());
        rentalDto.setName(boat.getName());
        rentalDto.setRating(boat.getRating());
        rentalDto.setRentalType(RentalType.BOAT);
        rentalDto.setLink(url + "boats/" + boat.getId());
        return rentalDto;
    }

    public static RentalDto mapToRental(Adventure adventure) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setAddress(adventure.getAddress());
        rentalDto.setDescription(adventure.getDescription());
        rentalDto.setName(adventure.getName());
        rentalDto.setRating(adventure.getRating());
        rentalDto.setRentalType(RentalType.ADVENTURE);
        rentalDto.setLink(url + "adventures/" + adventure.getId());
        return rentalDto;
    }

    public static RentalDto mapToRental(VacationHome home) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setAddress(home.getAddress());
        rentalDto.setDescription(home.getDescription());
        rentalDto.setName(home.getName());
        rentalDto.setRating(home.getRating());
        rentalDto.setRentalType(RentalType.HOME);

        rentalDto.setLink(url + "vacation/homes/" + home.getId());
        return rentalDto;
    }
}
