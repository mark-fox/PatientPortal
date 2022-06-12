package com.markfox.patientmanager.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="visitnotes")
public class VisitNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="visitId", nullable = false)
    private Long id;

    @Column(name="reason", nullable = false)
    private String visitReason;

    @Column(name="description")
    private String description;

    @Column(name="visitdate", nullable = false)
    private Date visitDate;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="patientId")
    @JsonIgnore
    private Patient patient;
}
