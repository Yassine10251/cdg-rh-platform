package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.Candidature;
import com.cdg.recrutement.entity.Offre;
import com.cdg.recrutement.entity.Utilisateur;
import com.cdg.recrutement.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidatureService {

    private final CandidatureRepository candidatureRepository;
    private final OffreService offreService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public CandidatureService(CandidatureRepository candidatureRepository,
                              OffreService offreService,
                              UtilisateurService utilisateurService) {
        this.candidatureRepository = candidatureRepository;
        this.offreService = offreService;
        this.utilisateurService = utilisateurService;
    }

    public Candidature creerCandidature(Candidature candidature) {
        Offre offre = offreService.getOffreParId(candidature.getOffre().getId());
        Utilisateur candidat = utilisateurService.getUtilisateurParId(
                candidature.getCandidat().getId());
        if (candidatureRepository.existsByCandidatAndOffre(candidat, offre)) {
            throw new RuntimeException("Vous avez deja postule a cette offre");
        }
        candidature.setStatut(Candidature.StatutCandidature.EN_ATTENTE);
        return candidatureRepository.save(candidature);
    }

    public List<Candidature> getToutesLesCandidatures() {
        return candidatureRepository.findAll();
    }

    public Candidature getCandidatureParId(Long id) {
        return candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvee : " + id));
    }

    public List<Candidature> getCandidaturesParCandidat(Long candidatId) {
        Utilisateur candidat = utilisateurService.getUtilisateurParId(candidatId);
        return candidatureRepository.findByCandidat(candidat);
    }

    public List<Candidature> getCandidaturesParOffre(Long offreId) {
        Offre offre = offreService.getOffreParId(offreId);
        return candidatureRepository.findByOffre(offre);
    }

    public Candidature changerStatut(Long id, Candidature.StatutCandidature statut) {
        Candidature candidature = getCandidatureParId(id);
        candidature.setStatut(statut);
        return candidatureRepository.save(candidature);
    }

    public void supprimerCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
}