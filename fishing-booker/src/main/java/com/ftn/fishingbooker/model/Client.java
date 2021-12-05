package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.ClientType;
import lombok.Data;

import javax.persistence.Entity;
import java.util.*;

@Entity
@Data
public class Client extends User {
    private float points;
    private ClientType type;
    private List<Reservation> reservationsMade;
}
