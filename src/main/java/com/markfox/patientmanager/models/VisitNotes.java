package com.markfox.patientmanager.models;


import javax.persistence.*;

@Entity
@Table(name="visitnotes")
public class VisitNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="visitId", nullable = false)
    private int id;

    @Column(name="description")
    private String description;
}
