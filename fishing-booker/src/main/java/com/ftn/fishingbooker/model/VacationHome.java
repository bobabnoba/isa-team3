package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class VacationHome {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String address;

    private String description;

    //private Set<Base64> images;

    private HashMap<Integer, Integer> bedRoom;

    @OneToMany(mappedBy = "vacationHome")
    private List<Reservation> availableReservations;

    private String codeOfConduct;

    private HashMap<String, Float> priceList;

    private String information;

    @ManyToOne
    @JoinColumn(name = "home_owner_id")
    private HomeOwner homeOwner;
}
