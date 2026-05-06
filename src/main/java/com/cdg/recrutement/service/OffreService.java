package com.cdg.recrutement.service;

import com.cdg.recrutement.entity.Offre;
import com.cdg.recrutement.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OffreService {

    private final OffreRepository offreRepository;

    @Autowired
    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public Offre creerOffre(Offre offre) {
        offre.setDatePublication(LocalDate.now());
        offre.setStatut(Offre.StatutOffre.BROUILLON);
        return offreRepository.save(offre);
    }

    public Offre publierOffre(Long id) {
        Offre offre = getOffreParId(id);
        offre.setStatut(Offre.StatutOffre.ACTIVE);
        offre.setDatePublication(LocalDate.now());
        return offreRepository.save(offre);
    }

    public Offre cloturerOffre(Long id) {
        Offre offre = getOffreParId(id);
        offre.setStatut(Offre.StatutOffre.CLOTUREE);
        return offreRepository.save(offre);
    }

    public List<Offre> getToutesLesOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getOffresActives() {
        return offreRepository.findByStatut(Offre.StatutOffre.ACTIVE);
    }

    public Offre getOffreParId(Long id) {
        return offreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre non trouvee : " + id));
    }

    public Offre modifierOffre(Long id, Offre offre) {
        Offre existante = getOffreParId(id);
        existante.setTitre(offre.getTitre());
        existante.setDescription(offre.getDescription());
        existante.setProfilRecherche(offre.getProfilRecherche());
        existante.setDepartement(offre.getDepartement());
        existante.setTypeContrat(offre.getTypeContrat());
        existante.setNombrePostes(offre.getNombrePostes());
        existante.setDateCloture(offre.getDateCloture());
        return offreRepository.save(existante);
    }

    public void supprimerOffre(Long id) {
        offreRepository.deleteById(id);
    }

    public List<Offre> rechercherParTitre(String titre) {
        return offreRepository.findByTitreContainingIgnoreCase(titre);
    }
}