package com.markfox.patientmanager.models;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(targetEntity = Patient.class, mappedBy = "doc")  // mappedBy the Doctor attribute in Patient class
    private List<Patient> docsPatients;

    public Doctor() {

    }

    public Doctor(String firstName, String lastName) { //, List<Patient> docsPatients) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.docsPatients = new ArrayList<>();
    }

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
