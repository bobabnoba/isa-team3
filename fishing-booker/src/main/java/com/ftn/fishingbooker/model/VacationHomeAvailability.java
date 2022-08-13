package com.ftn.fishingbooker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "VacationHomeAvailability")
public class VacationHomeAvailability {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "home_id", nullable = false)
    private VacationHome vacationHome;
}

