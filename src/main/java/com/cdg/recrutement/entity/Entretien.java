package com.cdg.recrutement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entretiens")
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    @JsonIgnoreProperties({"entretiens", "hibernateLazyInitializer"})
    private Candidature candidature;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    @JsonIgnoreProperties({"entretiens", "password", "hibernateLazyInitializer"})
    private Utilisateur interviewer;

    private LocalDateTime dateEntretien;
    private String lieu;

    @Enumerated(EnumType.STRING)
    private TypeEntretien type;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private Integer score;

    @Enumerated(EnumType.STRING)
    private StatutEntretien statut = StatutEntretien.PLANIFIE;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum TypeEntretien {
        RH, TECHNIQUE, FINAL
    }

    public enum StatutEntretien {
        PLANIFIE, EFFECTUE, ANNULE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Candidature getCandidature() { return candidature; }
    public void setCandidature(Candidature candidature) { this.candidature = candidature; }
    public Utilisateur getInterviewer() { return interviewer; }
    public void setInterviewer(Utilisateur interviewer) { this.interviewer = interviewer; }
    public LocalDateTime getDateEntretien() { return dateEntretien; }
    public void setDateEntretien(LocalDateTime dateEntretien) { this.dateEntretien = dateEntretien; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public TypeEntretien getType() { return type; }
    public void setType(TypeEntretien type) { this.type = type; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public StatutEntretien getStatut() { return statut; }
    public void setStatut(StatutEntretien statut) { this.statut = statut; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}