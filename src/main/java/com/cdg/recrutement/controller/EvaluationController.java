package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.EvaluationStagiaire;
import com.cdg.recrutement.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/evaluations")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    public ResponseEntity<List<EvaluationStagiaire>> getToutesLesEvaluations() {
        return ResponseEntity.ok(evaluationService.getToutesLesEvaluations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationStagiaire> getEvaluationParId(
            @PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationParId(id));
    }

    @GetMapping("/stage/{stageId}")
    public ResponseEntity<EvaluationStagiaire> getEvaluationParStage(
            @PathVariable Long stageId) {
        return ResponseEntity.ok(evaluationService.getEvaluationParStage(stageId));
    }

    @PostMapping
    public ResponseEntity<EvaluationStagiaire> creerEvaluation(
            @RequestBody EvaluationStagiaire evaluation) {
        return ResponseEntity.ok(evaluationService.creerEvaluation(evaluation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationStagiaire> modifierEvaluation(
            @PathVariable Long id,
            @RequestBody EvaluationStagiaire evaluation) {
        return ResponseEntity.ok(evaluationService.modifierEvaluation(id, evaluation));
    }
}