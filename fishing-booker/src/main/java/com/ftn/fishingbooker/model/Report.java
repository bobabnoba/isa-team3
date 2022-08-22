package com.ftn.fishingbooker.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "reports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Report {

    @Id
    @SequenceGenerator(name = "reportSeqGen", sequenceName = "reportGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeqGen")
    private Long id;

    private String comment;

    private boolean clientShowedUp;

    private boolean adminReviewed;

}
