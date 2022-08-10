package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Utility {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    public Utility(String name, Double price){
        	this.name = name;
        	this.price = price;
    }
}
