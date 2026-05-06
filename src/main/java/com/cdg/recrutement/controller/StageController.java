package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.Stage;
import com.cdg.recrutement.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stages")
@CrossOrigin(origins = "http://localhost:4200")
public class StageController {

    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping
    public ResponseEntity<List<Stage>> getTousLesStages() {
        return ResponseEntity.ok(stageService.getTousLesStages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageParId(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getStageParId(id));
    }

    @GetMapping("/en-cours")
    public ResponseEntity<List<Stage>> getStagesEnCours() {
        return ResponseEntity.ok(stageService.getStagesEnCours());
    }

    @GetMapping("/encadrant/{encadrantId}")
    public ResponseEntity<List<Stage>> getStagesParEncadrant(
            @PathVariable Long encadrantId) {
        return ResponseEntity.ok(stageService.getStagesParEncadrant(encadrantId));
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public ResponseEntity<Stage> getStageParStagiaire(
            @PathVariable Long stagiaireId) {
        return stageService.getStageParStagiaire(stagiaireId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stage> creerStage(@RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.creerStage(stage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> modifierStage(
            @PathVariable Long id, @RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.modifierStage(id, stage));
    }

    @PutMapping("/{stageId}/encadrant/{encadrantId}")
    public ResponseEntity<Stage> affecterEncadrant(
            @PathVariable Long stageId,
            @PathVariable Long encadrantId) {
        return ResponseEntity.ok(stageService.affecterEncadrant(stageId, encadrantId));
    }

    @PutMapping("/{id}/terminer")
    public ResponseEntity<Stage> terminerStage(
            @PathVariable Long id,
            @RequestParam Double noteFinale) {
        return ResponseEntity.ok(stageService.terminerStage(id, noteFinale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerStage(@PathVariable Long id) {
        stageService.supprimerStage(id);
        return ResponseEntity.noContent().build();
    }
}