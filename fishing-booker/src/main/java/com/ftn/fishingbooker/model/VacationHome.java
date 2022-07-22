package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationHome {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String description;

    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY)
    private Set<FileEntity> images;

    private HashMap<Integer, Integer> bedRoom; //broj soba i kreveta?

    @OneToMany(mappedBy = "vacationHome", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Reservation> availableReservations;

    private String codeOfConduct;

    private HashMap<String, Float> priceList;

    private String information;

    @ManyToOne
    @JoinColumn(name = "home_owner_id")
    private HomeOwner homeOwner;

    //<editor-fold>
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FileEntity> getImages() {
        return images;
    }

    public void setImages(Set<FileEntity> images) {
        this.images = images;
    }

    public HashMap<Integer, Integer> getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(HashMap<Integer, Integer> bedRoom) {
        this.bedRoom = bedRoom;
    }

    public Set<Reservation> getAvailableReservations() {
        return availableReservations;
    }

    public void setAvailableReservations(Set<Reservation> availableReservations) {
        this.availableReservations = availableReservations;
    }

    public String getCodeOfConduct() {
        return codeOfConduct;
    }

    public void setCodeOfConduct(String codeOfConduct) {
        this.codeOfConduct = codeOfConduct;
    }

    public HashMap<String, Float> getPriceList() {
        return priceList;
    }

    public void setPriceList(HashMap<String, Float> priceList) {
        this.priceList = priceList;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(HomeOwner homeOwner) {
        this.homeOwner = homeOwner;
    }

    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VacationHome that = (VacationHome) o;
        return id != null && Objects.equals(id, that.id);
    }

    public VacationHome(String name, String address, String description, HashMap<Integer, Integer> bedRoom, String codeOfConduct, HashMap<String, Float> priceList, String information, HomeOwner homeOwner) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.bedRoom = bedRoom;
        this.codeOfConduct = codeOfConduct;
        this.priceList = priceList;
        this.information = information;
        this.homeOwner = homeOwner;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
