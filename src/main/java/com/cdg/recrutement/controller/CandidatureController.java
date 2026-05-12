package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.Candidature;
import com.cdg.recrutement.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/candidatures")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatureController {

    private final CandidatureService candidatureService;

    @Autowired
    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    @GetMapping
    public ResponseEntity<List<Candidature>> getToutesLesCandidatures() {
        return ResponseEntity.ok(candidatureService.getToutesLesCandidatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidature> getCandidatureParId(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.getCandidatureParId(id));
    }

    @GetMapping("/candidat/{candidatId}")
    public ResponseEntity<List<Candidature>> getCandidaturesParCandidat(
            @PathVariable Long candidatId) {
        return ResponseEntity.ok(
                candidatureService.getCandidaturesParCandidat(candidatId));
    }

    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<Candidature>> getCandidaturesParOffre(
            @PathVariable Long offreId) {
        return ResponseEntity.ok(
                candidatureService.getCandidaturesParOffre(offreId));
    }

    @PostMapping
    public ResponseEntity<Candidature> creerCandidature(
            @RequestBody Candidature candidature) {
        return ResponseEntity.ok(candidatureService.creerCandidature(candidature));
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Candidature> changerStatut(
            @PathVariable Long id,
            @RequestParam Candidature.StatutCandidature statut) {
        return ResponseEntity.ok(candidatureService.changerStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCandidature(@PathVariable Long id) {
        candidatureService.supprimerCandidature(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload-cv")
    public ResponseEntity<String> uploadCv(
            @RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        new java.io.File(uploadDir).mkdirs();
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Path.of(uploadDir + fileName);
        Files.write(path, file.getBytes());
        return ResponseEntity.ok(fileName);
    }
}