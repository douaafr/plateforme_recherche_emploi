package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.CandidatureDAO;
import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.entity.Candidature;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Named("candidature")
public class CandidatureService {

    @Inject
    CandidatureDAO candidatureDAO;

    @Transactional
    public Candidature createCandidatureForOffre(OffreDTO offreDTO) {
        if (offreDTO == null || offreDTO.id() == null) {
            throw new IllegalArgumentException("Les informations de l'offre sont invalides.");
        }

        Candidature candidature = new Candidature();
        candidature.setCandidatId("defaultCandidatId");
        candidature.setOffreId(offreDTO.id());
        candidature.setStatut("En cours");

        candidatureDAO.saveCandidature(candidature);

        return candidature;
    }

    public Candidature getCandidatureById(Long id) {
        return candidatureDAO.getById(id);
    }

    @Transactional
    public void updateStatut(Long id, String statut) {
        candidatureDAO.updateStatut(id, statut);
    }
}
