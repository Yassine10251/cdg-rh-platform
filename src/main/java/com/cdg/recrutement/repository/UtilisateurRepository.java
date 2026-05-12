package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Utilisateur> findByRole(Utilisateur.Role role);
    List<Utilisateur> findByDepartement(String departement);
    List<Utilisateur> findByActifFalse();
}