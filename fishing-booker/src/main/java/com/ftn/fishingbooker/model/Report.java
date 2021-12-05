package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Report {
    @Id
    private int id;
    private int reservationId;
    private String text;
}
