package com.ftn.fishingbooker.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Admin extends User {

    private boolean firstLogin;

    private boolean headAdmin;

}
