package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Complaint {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: Ovo
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Complaint complaint = (Complaint) o;
        return id != null && Objects.equals(id, complaint.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
