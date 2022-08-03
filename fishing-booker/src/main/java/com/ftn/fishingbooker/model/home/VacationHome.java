package com.ftn.fishingbooker.model.home;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ftn.fishingbooker.model.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationHome {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deleted = false;

    private String name;

    private String address;

    private String description;

    private Double rating = 0.0;

    private Integer guestLimit;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "vacation_home_images",
            joinColumns = @JoinColumn(name = "home_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;


    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Room> rooms;

    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<VacationHomeReservation> availableReservations;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    private Set<String> codeOfConduct;

    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<VacationHomeUtility> utilities;

    private String information;

    @ManyToOne
    @JoinColumn(name = "home_owner_id")
    private VacationHomeOwner vacationHomeOwner;

}
