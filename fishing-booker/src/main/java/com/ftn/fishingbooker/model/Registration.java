package com.ftn.fishingbooker.model;

import com.ftn.fishingbooker.enumeration.RegistrationType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Registration {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationType type;

    private String motivation;

    private boolean isApproved;

    private String adminResponse;

    @OneToOne(mappedBy="registration", fetch = FetchType.LAZY)
    @ToString.Exclude
    private User registeredUsers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Registration that = (Registration) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
