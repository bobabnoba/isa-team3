package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Complaint {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    //private int authorId;

    //private int respondentId;

    private String explanation;

    //TODO: Da li zamijeniti sa user , da user sadrzi listu zalbi ?

    @ManyToOne
    @JoinColumn(name = "boat_owner_id", nullable = false)
    private BoatOwner boatOwner;

    @ManyToOne
    @JoinColumn(name = "home_owner_id", nullable = false)
    private HomeOwner homeOwner;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

}
