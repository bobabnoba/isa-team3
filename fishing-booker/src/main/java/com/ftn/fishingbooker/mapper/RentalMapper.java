package com.ftn.fishingbooker.mapper;


import com.ftn.fishingbooker.dto.RentalDto;
import com.ftn.fishingbooker.enumeration.RentalType;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.VacationHome;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class RentalMapper {
    public static Collection<RentalDto> mapBoatToRental(Collection<Boat> boats) {

        Collection<RentalDto> rentals = new ArrayList<RentalDto>();
        for (Boat boat : boats) {
            rentals.add(mapToRental(boat));

        }
        return rentals;
    }

    public static Collection<RentalDto> mapInstructorToRental(Collection<Adventure> adventures) {

        Collection<RentalDto> rentals = new ArrayList<RentalDto>();
        for (Adventure adventure : adventures) {
            rentals.add(mapToRental(adventure));

        }
        return rentals;
    }

    public static Collection<RentalDto> mapVacationHomeToRental(Collection<VacationHome> homes) {
        Collection<RentalDto> rentals = new ArrayList<>();
        for (VacationHome home : homes) {
            rentals.add(mapToRental(home));

        }
        return rentals;
    }

    private static RentalDto mapToRental(Boat boat) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setAddress(boat.getAddress());
        rentalDto.setDescription(boat.getDescription());
        rentalDto.setName(boat.getName());
        rentalDto.setRating(boat.getRating());
        rentalDto.setRentalType(RentalType.BOAT);

        return rentalDto;
    }

    public static RentalDto mapToRental(Adventure adventure) {
        //TODO:Images
        RentalDto rentalDto = new RentalDto();
        rentalDto.setAddress(adventure.getAddress());
        rentalDto.setDescription(adventure.getDescription());
        rentalDto.setName(adventure.getTitle());
        rentalDto.setRating(adventure.getRating());
        rentalDto.setRentalType(RentalType.ADVENTURE);

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

        return rentalDto;
    }
}
