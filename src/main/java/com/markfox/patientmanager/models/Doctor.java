package com.markfox.patientmanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

// Entity class for Doctors and the associated database table
@Entity
@Table(name="doctors")
public class Doctor {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long docId;

    // Required Name attributes with data validation annotation
    @Column(name="firstname", nullable = false)
    @NotEmpty(message = "Must enter a First Name")
    private String firstName;

    @Column(name="lastname", nullable = false)
    @NotEmpty(message = "Must enter a Last Name")
    private String lastName;

    // List of Patients associated with a single Doctor
    // mappedBy the Doctor attribute in Patient class
    @OneToMany(targetEntity = Patient.class, mappedBy = "doc")
    private List<Patient> docsPatients;

    // Constructors
    public Doctor() {
    }
    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Overridden methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(docId, doctor.docId) && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(docsPatients, doctor.docsPatients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docId, firstName, lastName, docsPatients);
    }

    // Getters and Setters
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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

    public List<Patient> getDocsPatients() {
        return docsPatients;
    }

    public void setDocsPatients(List<Patient> docsPatients) {
        this.docsPatients = docsPatients;
    }
}
