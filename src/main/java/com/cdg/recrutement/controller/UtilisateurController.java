package com.cdg.recrutement.controller;

import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getTousLesUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getTousLesUtilisateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurParId(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurParId(id));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Utilisateur>> getUtilisateursParRole(
            @PathVariable Utilisateur.Role role) {
        return ResponseEntity.ok(utilisateurService.getUtilisateursParRole(role));
    }

    @PostMapping
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.creerUtilisateur(utilisateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(
            @PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.modifierUtilisateur(id, utilisateur));
    }

    @PutMapping("/{id}/toggle-actif")
    public ResponseEntity<Utilisateur> toggleActif(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.toggleActif(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/en-attente")
    public ResponseEntity<List<Utilisateur>> getUtilisateursEnAttente() {
        return ResponseEntity.ok(utilisateurService.getUtilisateursEnAttente());
    }

    @PutMapping("/{id}/approuver")
    public ResponseEntity<Utilisateur> approuverUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.approuverUtilisateur(id));
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<Utilisateur> refuserUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.refuserUtilisateur(id));
    }
}