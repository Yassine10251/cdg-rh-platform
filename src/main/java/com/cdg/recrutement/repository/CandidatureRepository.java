package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.Candidature;
import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByCandidat(Utilisateur candidat);
    List<Candidature> findByOffre(Offre offre);
    List<Candidature> findByStatut(Candidature.StatutCandidature statut);
    Boolean existsByCandidatAndOffre(Utilisateur candidat, Offre offre);
    Long countByStatut(Candidature.StatutCandidature statut);
}