package com.markfox.patientmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "lastvisit")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastVisitDate;

    @Column(name = "ethnicity")
    private String raceEthnicity;

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



    public List<String> getEthnicityList() {
        return Arrays.asList("White",
                "Black or African American",
                "American Indian or Alaska Native",
                "Asian",
                "Native Hawaiian or Other Pacific Islander");
    }

    public List<String> getGenderList() {
        return Arrays.asList("Male", "Female");
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getRaceEthnicity() {
        return raceEthnicity;
    }

    public void setRaceEthnicity(String raceEthnicity) {
        this.raceEthnicity = raceEthnicity;
    }
}
