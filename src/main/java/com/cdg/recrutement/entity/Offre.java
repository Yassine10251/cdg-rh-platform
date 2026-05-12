package com.cdg.recrutement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offres")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String profilRecherche;

    @Column(nullable = false)
    private String departement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeContrat typeContrat;

    private Integer nombrePostes;
    private LocalDate datePublication;
    private LocalDate dateCloture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutOffre statut = StatutOffre.BROUILLON;

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    private List<Candidature> candidatures;

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

    public enum TypeContrat {
        CDI, CDD, STAGE_PFE, STAGE_ETE, STAGE_OBSERVATION
    }

    public enum StatutOffre {
        BROUILLON, ACTIVE, CLOTUREE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getProfilRecherche() { return profilRecherche; }
    public void setProfilRecherche(String profilRecherche) { this.profilRecherche = profilRecherche; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public TypeContrat getTypeContrat() { return typeContrat; }
    public void setTypeContrat(TypeContrat typeContrat) { this.typeContrat = typeContrat; }
    public Integer getNombrePostes() { return nombrePostes; }
    public void setNombrePostes(Integer nombrePostes) { this.nombrePostes = nombrePostes; }
    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public LocalDate getDateCloture() { return dateCloture; }
    public void setDateCloture(LocalDate dateCloture) { this.dateCloture = dateCloture; }
    public StatutOffre getStatut() { return statut; }
    public void setStatut(StatutOffre statut) { this.statut = statut; }
    public List<Candidature> getCandidatures() { return candidatures; }
    public void setCandidatures(List<Candidature> candidatures) { this.candidatures = candidatures; }
}