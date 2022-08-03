package com.ftn.fishingbooker.model.home;

import com.ftn.fishingbooker.model.home.VacationHome;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Room {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numberOfBeds", nullable = false)
    private Integer numberOfBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private VacationHome vacationHome;

}
