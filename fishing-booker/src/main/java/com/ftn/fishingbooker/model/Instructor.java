package com.ftn.fishingbooker.model;

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

    @OneToMany(targetEntity = InstructorAvailability.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<InstructorAvailability> availability;

}
