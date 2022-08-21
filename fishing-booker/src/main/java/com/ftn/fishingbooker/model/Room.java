package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int bedNumber;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private VacationHome vacationHome;

}
