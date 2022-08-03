package com.ftn.fishingbooker.model.adventure;

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
public class AdventureUtility extends Utility {
    @ManyToOne
    @JoinColumn(name = "adventure_id", nullable = false)
    private Adventure adventure;
}
