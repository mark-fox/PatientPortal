package com.markfox.patientmanager.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long docId;

    @Column(name="firstname", nullable = false)
    private String firstName;

    @Column(name="lastname", nullable = false)
    private String lastName;

//    @OneToMany(targetEntity = Patient.class)
//    private List<Patient> docsPatients;
}
