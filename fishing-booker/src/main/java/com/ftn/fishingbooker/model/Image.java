package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String url;
}
