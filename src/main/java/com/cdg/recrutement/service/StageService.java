package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.Stage;
import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;
    private final UtilisateurService utilisateurService;

    @Autowired
    public StageService(StageRepository stageRepository,
                        UtilisateurService utilisateurService) {
        this.stageRepository = stageRepository;
        this.utilisateurService = utilisateurService;
    }

    public Stage creerStage(Stage stage) {
        stage.setStatut(Stage.StatutStage.EN_ATTENTE);
        return stageRepository.save(stage);
    }

    public List<Stage> getTousLesStages() {
        return stageRepository.findAll();
    }

    public Stage getStageParId(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage non trouve : " + id));
    }

    public Optional<Stage> getStageParStagiaire(Long stagiaireId) {
        return stageRepository.findByStagiaireId(stagiaireId);
    }

    public List<Stage> getStagesEnCours() {
        return stageRepository.findByStatut(Stage.StatutStage.EN_COURS);
    }

    public List<Stage> getStagesParEncadrant(Long encadrantId) {
        Utilisateur encadrant = utilisateurService.getUtilisateurParId(encadrantId);
        return stageRepository.findByEncadrant(encadrant);
    }

    public Stage affecterEncadrant(Long stageId, Long encadrantId) {
        Stage stage = getStageParId(stageId);
        Utilisateur encadrant = utilisateurService.getUtilisateurParId(encadrantId);
        stage.setEncadrant(encadrant);
        stage.setStatut(Stage.StatutStage.EN_COURS);
        return stageRepository.save(stage);
    }

    public Stage terminerStage(Long id, Double noteFinale) {
        Stage stage = getStageParId(id);
        stage.setStatut(Stage.StatutStage.TERMINE);
        stage.setNoteFinale(noteFinale);
        return stageRepository.save(stage);
    }

    public Stage modifierStage(Long id, Stage stage) {
        Stage existant = getStageParId(id);
        existant.setSujet(stage.getSujet());
        existant.setDepartement(stage.getDepartement());
        existant.setDateDebut(stage.getDateDebut());
        existant.setDateFin(stage.getDateFin());
        return stageRepository.save(existant);
    }

    public void supprimerStage(Long id) {
        stageRepository.deleteById(id);
    }
}