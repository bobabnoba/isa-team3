package com.ftn.fishingbooker.model.boat;

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
public class BoatUtility extends Utility {
    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private Boat boat;
}
