package com.markfox.patientmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

//    @Column(name = "dob")
//    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    private LocalDate dateOfBirth;

    @ManyToOne(fetch=FetchType.LAZY, optional = true
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name="docId", nullable = true)   // name of join-column added to patients table
    @JsonIgnore
    private Doctor doc;

    @OneToMany(targetEntity = VisitNotes.class, mappedBy = "visitsPatient", cascade = {CascadeType.REMOVE})
    private List<VisitNotes> patientVisits;

    public Patient() {

    }

    public Patient(String firstName, String lastName, Doctor doc) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.doctor = doctor;
        this.doc = doc;
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

//    public String getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(String doctor) {
//        this.doctor = doctor;
//    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }


    public List<VisitNotes> getPatientVisits() {
        return patientVisits;
    }

    public void setPatientVisits(List<VisitNotes> patientVisits) {
        this.patientVisits = patientVisits;
    }
}
