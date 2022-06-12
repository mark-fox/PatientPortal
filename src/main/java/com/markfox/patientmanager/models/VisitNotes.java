package com.markfox.patientmanager.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
    private LocalDate visitDate;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="patientId", nullable = true)
    @JsonIgnore
    private Patient visitsPatient;

    public VisitNotes() {

    }
    public VisitNotes(String visitReason, String description, LocalDate visitDate) {
        this.visitReason = visitReason;
        this.description = description;
        this.visitDate = visitDate;
    }

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
