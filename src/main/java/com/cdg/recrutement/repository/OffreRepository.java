package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByStatut(Offre.StatutOffre statut);
    List<Offre> findByDepartement(String departement);
    List<Offre> findByTypeContrat(Offre.TypeContrat typeContrat);
    List<Offre> findByTitreContainingIgnoreCase(String titre);
}