package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.EvaluationStagiaire;
import com.cdg.recrutement.entity.Stage;
import com.cdg.recrutement.repository.EvaluationStagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluationService {

    private final EvaluationStagiaireRepository evaluationRepository;
    private final StageService stageService;

    @Autowired
    public EvaluationService(EvaluationStagiaireRepository evaluationRepository,
                             StageService stageService) {
        this.evaluationRepository = evaluationRepository;
        this.stageService = stageService;
    }

    public List<EvaluationStagiaire> getToutesLesEvaluations() {
        return evaluationRepository.findAll();
    }

    public EvaluationStagiaire creerEvaluation(EvaluationStagiaire evaluation) {
        if (evaluationRepository.existsByStageId(evaluation.getStage().getId())) {
            throw new RuntimeException("Ce stage a deja ete evalue");
        }
        Stage stage = stageService.getStageParId(evaluation.getStage().getId());
        evaluation.setStage(stage);
        return evaluationRepository.save(evaluation);
    }

    public EvaluationStagiaire getEvaluationParStage(Long stageId) {
        return evaluationRepository.findByStageId(stageId)
                .orElseThrow(() -> new RuntimeException(
                        "Evaluation non trouvee pour le stage : " + stageId));
    }

    public EvaluationStagiaire getEvaluationParId(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Evaluation non trouvee : " + id));
    }

    public EvaluationStagiaire modifierEvaluation(Long id,
                                                  EvaluationStagiaire evaluation) {
        EvaluationStagiaire existante = getEvaluationParId(id);
        existante.setPonctualite(evaluation.getPonctualite());
        existante.setImplication(evaluation.getImplication());
        existante.setCompetencesTechniques(evaluation.getCompetencesTechniques());
        existante.setCommunication(evaluation.getCommunication());
        existante.setCommentaire(evaluation.getCommentaire());
        return evaluationRepository.save(existante);
    }
}