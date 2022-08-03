package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.model.adventure.Adventure;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Instructor extends User {

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Adventure> adventures;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;
    
}
