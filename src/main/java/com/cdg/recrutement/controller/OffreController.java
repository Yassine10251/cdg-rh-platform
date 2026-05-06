package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.Offre;
import com.cdg.recrutement.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/offres")
@CrossOrigin(origins = "http://localhost:4200")
public class OffreController {

    private final OffreService offreService;

    @Autowired
    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping
    public ResponseEntity<List<Offre>> getToutesLesOffres() {
        return ResponseEntity.ok(offreService.getToutesLesOffres());
    }

    @GetMapping("/actives")
    public ResponseEntity<List<Offre>> getOffresActives() {
        return ResponseEntity.ok(offreService.getOffresActives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreParId(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.getOffreParId(id));
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Offre>> rechercherParTitre(@RequestParam String titre) {
        return ResponseEntity.ok(offreService.rechercherParTitre(titre));
    }

    @PostMapping
    public ResponseEntity<Offre> creerOffre(@RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.creerOffre(offre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> modifierOffre(
            @PathVariable Long id, @RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.modifierOffre(id, offre));
    }

    @PutMapping("/{id}/publier")
    public ResponseEntity<Offre> publierOffre(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.publierOffre(id));
    }

    @PutMapping("/{id}/cloturer")
    public ResponseEntity<Offre> cloturerOffre(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.cloturerOffre(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerOffre(@PathVariable Long id) {
        offreService.supprimerOffre(id);
        return ResponseEntity.noContent().build();
    }
}