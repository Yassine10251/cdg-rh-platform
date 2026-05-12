package com.cdg.recrutement.controller;

import com.cdg.recrutement.config.JwtUtil;
import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UtilisateurService utilisateurService,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<Utilisateur> optUtilisateur =
                utilisateurService.getUtilisateurParEmail(email);

        if (optUtilisateur.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Email ou mot de passe incorrect"));
        }

        Utilisateur utilisateur = optUtilisateur.get();

        if (!passwordEncoder.matches(password, utilisateur.getPassword())) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Email ou mot de passe incorrect"));
        }

        if (utilisateur.getActif() != null && !utilisateur.getActif()) {
            return ResponseEntity.status(403)
                    .body(Map.of("message",
                            "Votre compte est en attente d'approbation par l'administrateur"));
        }

        String token = jwtUtil.generateToken(
                utilisateur.getEmail(),
                utilisateur.getRole().name()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("id", utilisateur.getId());
        response.put("nom", utilisateur.getNom());
        response.put("prenom", utilisateur.getPrenom());
        response.put("email", utilisateur.getEmail());
        response.put("role", utilisateur.getRole().name());
        response.put("departement", utilisateur.getDepartement());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> existing =
                utilisateurService.getUtilisateurParEmail(utilisateur.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(400)
                    .body(Map.of("message", "Cet email est deja utilise"));
        }

        if (utilisateur.getRole() == Utilisateur.Role.ADMIN ||
                utilisateur.getRole() == Utilisateur.Role.RH ||
                utilisateur.getRole() == Utilisateur.Role.MANAGER) {
            utilisateur.setActif(false);
        } else {
            utilisateur.setActif(true);
        }

        utilisateurService.creerUtilisateur(utilisateur);
        return ResponseEntity.ok(Map.of("message", "Compte cree avec succes"));
    }
}