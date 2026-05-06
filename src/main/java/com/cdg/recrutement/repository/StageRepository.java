package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.Stage;
import com.cdg.recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByStatut(Stage.StatutStage statut);
    List<Stage> findByStagiaire(Utilisateur stagiaire);
    List<Stage> findByEncadrant(Utilisateur encadrant);
    List<Stage> findByDepartement(String departement);
    List<Stage> findByTypeStage(Stage.TypeStage typeStage);
    Optional<Stage> findByStagiaireId(Long stagiaireId);
    Long countByStatut(Stage.StatutStage statut);
}