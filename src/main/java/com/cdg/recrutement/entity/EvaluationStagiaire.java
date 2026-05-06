package com.cdg.recrutement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations_stagiaires")
public class EvaluationStagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stage_id", nullable = false)
    @JsonIgnoreProperties({"evaluation", "hibernateLazyInitializer"})
    private Stage stage;

    @Column(nullable = false)
    private Integer ponctualite;

    @Column(nullable = false)
    private Integer implication;

    @Column(nullable = false)
    private Integer competencesTechniques;

    @Column(nullable = false)
    private Integer communication;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    private LocalDate dateEvaluation;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        dateEvaluation = LocalDate.now();
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }
    public Integer getPonctualite() { return ponctualite; }
    public void setPonctualite(Integer ponctualite) { this.ponctualite = ponctualite; }
    public Integer getImplication() { return implication; }
    public void setImplication(Integer implication) { this.implication = implication; }
    public Integer getCompetencesTechniques() { return competencesTechniques; }
    public void setCompetencesTechniques(Integer competencesTechniques) { this.competencesTechniques = competencesTechniques; }
    public Integer getCommunication() { return communication; }
    public void setCommunication(Integer communication) { this.communication = communication; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}