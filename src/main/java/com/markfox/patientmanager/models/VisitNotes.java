package com.markfox.patientmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

// Entity class for Visit Notes and the associated database table
@Entity
@Table(name="visitnotes")
public class VisitNotes {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="visitId", nullable = false)
    private Long id;

    @Column(name="reason", nullable = false)
    private String visitReason;

    // Field for large String entries
    @Lob
    @Column(name="description")
    private String description;

    @Column(name="visitdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    // Mapped Patient entity
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="patientId", nullable = true)
    @JsonIgnore
    private Patient visitsPatient;

    // Constructors
    public VisitNotes() {
    }
    public VisitNotes(String visitReason, String description, LocalDate visitDate) {
        this.visitReason = visitReason;
        this.description = description;
        this.visitDate = visitDate;
    }

    // Overridden methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitNotes that = (VisitNotes) o;
        return Objects.equals(id, that.id) && Objects.equals(visitReason, that.visitReason) && Objects.equals(description, that.description) && Objects.equals(visitDate, that.visitDate) && Objects.equals(visitsPatient, that.visitsPatient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visitReason, description, visitDate, visitsPatient);
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Patient getVisitsPatient() {
        return visitsPatient;
    }

    public void setVisitsPatient(Patient visitsPatient) {
        this.visitsPatient = visitsPatient;
    }
}
