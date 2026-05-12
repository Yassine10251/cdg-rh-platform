package com.cdg.recrutement.repository;

import com.cdg.recrutement.entity.EvaluationStagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EvaluationStagiaireRepository extends JpaRepository<EvaluationStagiaire, Long> {
    Optional<EvaluationStagiaire> findByStageId(Long stageId);
    Boolean existsByStageId(Long stageId);
}