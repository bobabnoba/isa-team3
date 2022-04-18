package com.ftn.fishingbooker.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class HomeOwner extends User {

    @OneToMany(mappedBy = "homeOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<VacationHome> vacationHomes;

    @OneToMany(mappedBy = "homeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Report> reservationReport;

    @OneToMany(mappedBy = "homeOwner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Complaint> complaints;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
