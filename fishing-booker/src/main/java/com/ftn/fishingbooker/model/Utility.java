package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Utility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    public Utility(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
