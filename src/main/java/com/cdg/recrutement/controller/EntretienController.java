package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.Entretien;
import com.cdg.recrutement.service.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entretiens")
@CrossOrigin(origins = "http://localhost:4200")
public class EntretienController {

    private final EntretienService entretienService;

    @Autowired
    public EntretienController(EntretienService entretienService) {
        this.entretienService = entretienService;
    }

    @GetMapping
    public ResponseEntity<List<Entretien>> getTousLesEntretiens() {
        return ResponseEntity.ok(entretienService.getTousLesEntretiens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entretien> getEntretienParId(@PathVariable Long id) {
        return ResponseEntity.ok(entretienService.getEntretienParId(id));
    }

    @GetMapping("/candidature/{candidatureId}")
    public ResponseEntity<List<Entretien>> getEntretiensParCandidature(
            @PathVariable Long candidatureId) {
        return ResponseEntity.ok(
                entretienService.getEntretiensParCandidature(candidatureId));
    }

    @PostMapping
    public ResponseEntity<Entretien> planifierEntretien(
            @RequestBody Entretien entretien) {
        return ResponseEntity.ok(entretienService.planifierEntretien(entretien));
    }

    @PutMapping("/{id}/feedback")
    public ResponseEntity<Entretien> ajouterFeedback(
            @PathVariable Long id,
            @RequestBody Map<String, Object> feedbackData) {
        return ResponseEntity.ok(
                entretienService.ajouterFeedback(id, feedbackData));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Entretien> annulerEntretien(@PathVariable Long id) {
        return ResponseEntity.ok(entretienService.annulerEntretien(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEntretien(@PathVariable Long id) {
        entretienService.supprimerEntretien(id);
        return ResponseEntity.noContent().build();
    }
}