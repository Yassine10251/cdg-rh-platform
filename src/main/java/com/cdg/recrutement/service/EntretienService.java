package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.Entretien;
import com.cdg.recrutement.repository.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class EntretienService {

    private final EntretienRepository entretienRepository;

    @Autowired
    public EntretienService(EntretienRepository entretienRepository) {
        this.entretienRepository = entretienRepository;
    }

    public Entretien planifierEntretien(Entretien entretien) {
        entretien.setStatut(Entretien.StatutEntretien.PLANIFIE);
        return entretienRepository.save(entretien);
    }

    public List<Entretien> getTousLesEntretiens() {
        return entretienRepository.findAll();
    }

    public Entretien getEntretienParId(Long id) {
        return entretienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entretien non trouve : " + id));
    }

    public List<Entretien> getEntretiensParCandidature(Long candidatureId) {
        return entretienRepository.findByCandidatureId(candidatureId);
    }

    public Entretien ajouterFeedback(Long id, Map<String, Object> feedbackData) {
        Entretien entretien = getEntretienParId(id);
        if (feedbackData.get("feedback") != null) {
            entretien.setFeedback(feedbackData.get("feedback").toString());
        }
        if (feedbackData.get("score") != null) {
            entretien.setScore(Integer.parseInt(feedbackData.get("score").toString()));
        }
        entretien.setStatut(Entretien.StatutEntretien.EFFECTUE);
        return entretienRepository.save(entretien);
    }

    public Entretien annulerEntretien(Long id) {
        Entretien entretien = getEntretienParId(id);
        entretien.setStatut(Entretien.StatutEntretien.ANNULE);
        return entretienRepository.save(entretien);
    }

    public void supprimerEntretien(Long id) {
        entretienRepository.deleteById(id);
    }
}