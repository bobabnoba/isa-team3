package com.ftn.fishingbooker.model.home;

import com.ftn.fishingbooker.model.Utility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationHomeUtility extends Utility {
    @ManyToOne
    @JoinColumn(name = "vacation_home_id", nullable = false)
    private VacationHome vacationHome;
}
