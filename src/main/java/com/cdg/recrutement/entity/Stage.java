package com.cdg.recrutement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "stages")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", nullable = false)
    @JsonIgnoreProperties({"stages", "password", "hibernateLazyInitializer"})
    private Utilisateur stagiaire;

    @ManyToOne
    @JoinColumn(name = "encadrant_id")
    @JsonIgnoreProperties({"stages", "password", "hibernateLazyInitializer"})
    private Utilisateur encadrant;

    @Column(nullable = false)
    private String sujet;

    private String departement;
    private String etablissement;
    private String niveauEtude;

    @Enumerated(EnumType.STRING)
    private TypeStage typeStage;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutStage statut = StatutStage.EN_ATTENTE;

    private Double noteFinale;
    private String rapportUrl;
    private String conventionUrl;
    private String attestationUrl;

    @OneToOne(mappedBy = "stage", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"stage", "hibernateLazyInitializer"})
    private EvaluationStagiaire evaluation;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum TypeStage {
        PFE, STAGE_ETE, STAGE_OBSERVATION
    }

    public enum StatutStage {
        EN_ATTENTE, EN_COURS, TERMINE, ABANDONNE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Utilisateur getStagiaire() { return stagiaire; }
    public void setStagiaire(Utilisateur stagiaire) { this.stagiaire = stagiaire; }
    public Utilisateur getEncadrant() { return encadrant; }
    public void setEncadrant(Utilisateur encadrant) { this.encadrant = encadrant; }
    public String getSujet() { return sujet; }
    public void setSujet(String sujet) { this.sujet = sujet; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getEtablissement() { return etablissement; }
    public void setEtablissement(String etablissement) { this.etablissement = etablissement; }
    public String getNiveauEtude() { return niveauEtude; }
    public void setNiveauEtude(String niveauEtude) { this.niveauEtude = niveauEtude; }
    public TypeStage getTypeStage() { return typeStage; }
    public void setTypeStage(TypeStage typeStage) { this.typeStage = typeStage; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public StatutStage getStatut() { return statut; }
    public void setStatut(StatutStage statut) { this.statut = statut; }
    public Double getNoteFinale() { return noteFinale; }
    public void setNoteFinale(Double noteFinale) { this.noteFinale = noteFinale; }
    public String getRapportUrl() { return rapportUrl; }
    public void setRapportUrl(String rapportUrl) { this.rapportUrl = rapportUrl; }
    public String getConventionUrl() { return conventionUrl; }
    public void setConventionUrl(String conventionUrl) { this.conventionUrl = conventionUrl; }
    public String getAttestationUrl() { return attestationUrl; }
    public void setAttestationUrl(String attestationUrl) { this.attestationUrl = attestationUrl; }
    public EvaluationStagiaire getEvaluation() { return evaluation; }
    public void setEvaluation(EvaluationStagiaire evaluation) { this.evaluation = evaluation; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}