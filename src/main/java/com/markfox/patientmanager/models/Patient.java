package com.markfox.patientmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "doctor")
    private String doctor;

//    @ManyToOne(fetch=FetchType.LAZY, optional = false)
//    @JoinColumn()//name="docId")
//    @JsonIgnore
//    private Doctor doc;


    public Patient() {

    }

    public Patient(String firstName, String lastName, String doctor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

//    public Doctor getDoc() {
//        return doc;
//    }
//
//    public void setDoc(Doctor doc) {
//        this.doc = doc;
//    }
}
