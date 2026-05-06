package com.cdg.recrutement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "candidatures")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)
    @JsonIgnoreProperties({"candidatures", "hibernateLazyInitializer"})
    private Offre offre;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    @JsonIgnoreProperties({"candidatures", "password", "hibernateLazyInitializer"})
    private Utilisateur candidat;

    private LocalDate dateCandidature;

    @Column(columnDefinition = "TEXT")
    private String lettreMotivation;

    private String cvUrl;
    private String sujetPfe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCandidature statut = StatutCandidature.EN_ATTENTE;

    @OneToMany(mappedBy = "candidature", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"candidature", "hibernateLazyInitializer"})
    private List<Entretien> entretiens;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        dateCandidature = LocalDate.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum StatutCandidature {
        EN_ATTENTE, EN_COURS, RETENU, REFUSE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Offre getOffre() { return offre; }
    public void setOffre(Offre offre) { this.offre = offre; }
    public Utilisateur getCandidat() { return candidat; }
    public void setCandidat(Utilisateur candidat) { this.candidat = candidat; }
    public LocalDate getDateCandidature() { return dateCandidature; }
    public void setDateCandidature(LocalDate dateCandidature) { this.dateCandidature = dateCandidature; }
    public String getLettreMotivation() { return lettreMotivation; }
    public void setLettreMotivation(String lettreMotivation) { this.lettreMotivation = lettreMotivation; }
    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }
    public String getSujetPfe() { return sujetPfe; }
    public void setSujetPfe(String sujetPfe) { this.sujetPfe = sujetPfe; }
    public StatutCandidature getStatut() { return statut; }
    public void setStatut(StatutCandidature statut) { this.statut = statut; }
    public List<Entretien> getEntretiens() { return entretiens; }
    public void setEntretiens(List<Entretien> entretiens) { this.entretiens = entretiens; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}