package com.ftn.fishingbooker.model.home;

import com.ftn.fishingbooker.model.Complaint;
import com.ftn.fishingbooker.model.Report;
import com.ftn.fishingbooker.model.User;
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
public class VacationHomeOwner extends User {

    @OneToMany(mappedBy = "vacationHomeOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<VacationHome> vacationHomes;

    @OneToMany(mappedBy = "vacationHomeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "vacationHomeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;


}
