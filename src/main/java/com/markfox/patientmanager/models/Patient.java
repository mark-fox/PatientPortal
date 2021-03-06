package com.markfox.patientmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Entity class for Patients and the associated database table
@Entity
@Table(name="patients")
public class Patient {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Required Name attributes with data validation annotation
    @Column(name = "firstname", nullable = false)
    @NotEmpty(message = "Must enter a First Name")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @NotEmpty(message = "Must enter a Last Name")
    private String lastName;

    // Date field with data validation annotation
    // Value must be a Past date
    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of Birth can only be in the past")
    @NotNull(message = "Please enter the Date of Birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Must enter a valid email address")
    @NotEmpty(message = "Must enter an email address")
    private String emailAddress;

    // Date could be on the current day or before, but not in the future
    @Column(name = "lastvisit")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Last Visit must be a past date")
    private LocalDate lastVisitDate;

    @Column(name = "ethnicity")
    private String raceEthnicity;

    // Mapped Doctor entity
    @ManyToOne(fetch=FetchType.LAZY, optional = true)
    @JoinColumn(name="docId", nullable = true)   // name of join-column added to patients table
    @JsonIgnore
    private Doctor doc;

    // Mapped list of Visit Notes for this Patient
    @OneToMany(targetEntity = VisitNotes.class, mappedBy = "visitsPatient", cascade = {CascadeType.REMOVE})
    private List<VisitNotes> patientVisits;

    // Constructors
    public Patient() {
    }

    // Currently, the only required fields are in this constructor
    public Patient(String firstName, String lastName, Doctor doc, LocalDate dateOfBirth, String email, LocalDate lastVisitDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.doc = doc;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = email;
        this.lastVisitDate = lastVisitDate;
    }

    // Static lists of values for dropdown menus and called with Thymeleaf
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


    // Overridden methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(gender, patient.gender) && Objects.equals(phoneNumber, patient.phoneNumber) && Objects.equals(emailAddress, patient.emailAddress) && Objects.equals(lastVisitDate, patient.lastVisitDate) && Objects.equals(raceEthnicity, patient.raceEthnicity) && Objects.equals(doc, patient.doc) && Objects.equals(patientVisits, patient.patientVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, phoneNumber, emailAddress, lastVisitDate, raceEthnicity, doc, patientVisits);
    }


    // Getters and Setters
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
