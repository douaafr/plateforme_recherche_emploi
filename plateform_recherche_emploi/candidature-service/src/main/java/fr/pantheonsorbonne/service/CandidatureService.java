package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.CandidatureDAO;
import fr.pantheonsorbonne.dto.CandidatureDTO;
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
    public CandidatureDTO createCandidatureForOffre(OffreDTO offreDTO) {
        // Vérifiez que l'offre n'est pas nulle
        if (offreDTO == null || offreDTO.id() == null) {
            throw new IllegalArgumentException("Les informations de l'offre sont invalides.");
        }

        // Création de l'objet Candidature
        Candidature candidature = new Candidature();
        candidature.setCandidatId("defaultCandidatId"); // Remplacez par votre logique réelle
        candidature.setOffreId(offreDTO.id());
        candidature.setStatut("En cours");

        candidatureDAO.saveCandidature(candidature);

        // Retourne un DTO de candidature
        return new CandidatureDTO(
                candidature.getId(),
                candidature.getCandidatId(),
                offreDTO,
                candidature.getStatut()
        );
    }

    // Autres méthodes pour récupérer et mettre à jour une candidature

    public CandidatureDTO getCandidatureById(Long id) {
        Candidature candidature = candidatureDAO.getById(id);
        if (candidature == null) {
            return null;
        }

        // Stub pour OffreDTO si nécessaire
        OffreDTO offreDTO = new OffreDTO(
                candidature.getOffreId(),
                "Nom Offre",
                "Description Offre",
                "Entreprise",
                "Localisation",
                "Type Contrat",
                0.0,
                "0"
        );

        return new CandidatureDTO(
                candidature.getId(),
                candidature.getCandidatId(),
                offreDTO,
                candidature.getStatut()
        );
    }

    @Transactional
    public void updateStatut(Long id, String statut) {
        candidatureDAO.updateStatut(id, statut);
    }
}
