package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.Entretien;
import com.cdg.recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    List<Entretien> findByCandidatureId(Long candidatureId);
    List<Entretien> findByInterviewer(Utilisateur interviewer);
    List<Entretien> findByStatut(Entretien.StatutEntretien statut);
    List<Entretien> findByDateEntretienBetween(LocalDateTime debut, LocalDateTime fin);
}