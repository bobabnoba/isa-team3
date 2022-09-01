package com.ftn.fishingbooker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int bedNumber;

//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    @ToString.Exclude
//    @JsonManagedReference
//    private VacationHome vacationHome;

}
