package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new RuntimeException("Email deja utilise : " + utilisateur.getEmail());
        }
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve : " + id));
    }

    public Optional<Utilisateur> getUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public List<Utilisateur> getUtilisateursParRole(Utilisateur.Role role) {
        return utilisateurRepository.findByRole(role);
    }

    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur existant = getUtilisateurParId(id);
        existant.setNom(utilisateur.getNom());
        existant.setPrenom(utilisateur.getPrenom());
        existant.setTelephone(utilisateur.getTelephone());
        existant.setDepartement(utilisateur.getDepartement());
        return utilisateurRepository.save(existant);
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur toggleActif(Long id) {
        Utilisateur utilisateur = getUtilisateurParId(id);
        utilisateur.setActif(!utilisateur.getActif());
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getUtilisateursEnAttente() {
        return utilisateurRepository.findByActifFalse();
    }

    public Utilisateur approuverUtilisateur(Long id) {
        Utilisateur utilisateur = getUtilisateurParId(id);
        utilisateur.setActif(true);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur refuserUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
        return null;
    }
}