package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Complaint {
    @Id
    private int id;
    private int authorId;
    private int respondentId;
    private String explanation;
}
