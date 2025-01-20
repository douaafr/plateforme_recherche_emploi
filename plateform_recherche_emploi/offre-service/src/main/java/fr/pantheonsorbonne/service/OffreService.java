package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.OffreDAO;
import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.entity.Offre;
import fr.pantheonsorbonne.exception.InvalidOffreException;
import fr.pantheonsorbonne.gateway.CandidatureGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OffreService {

    @Inject
    OffreDAO offreDAO;

    @Inject
    CandidatureGateway candidatureGateway;

    public OffreDTO getOffreById(Long id) {
        Offre offre = offreDAO.getById(id);
        if (offre == null) {
            return null;
        }
        return new OffreDTO(offre.getNom(), offre.getDescription(), offre.getEntreprise(), offre.getLocalisation(), offre.getTypeContrat(), offre.getSalaire());
    }

    @Transactional
    public Long checkAndSaveOffre(OffreDTO offreDTO) throws InvalidOffreException {

        //vérification métier

        if (offreDTO.nom() == null || offreDTO.nom().isBlank()) {
            throw new InvalidOffreException("Le titre de l'offre est obligatoire.");
        }

        if (offreDTO.description() == null || offreDTO.description().isBlank()) {
            throw new InvalidOffreException("La description de l'offre est obligatoire.");
        }

        if (offreDTO.entreprise() == null || offreDTO.entreprise().isBlank()) {
            throw new InvalidOffreException("Le nom de l'entreprise est obligatoire.");
        }

        if (offreDTO.localisation() == null || offreDTO.localisation().isBlank()) {
            throw new InvalidOffreException("La localisation est obligatoire.");
        }

        if (offreDTO.typeContrat() == null || offreDTO.typeContrat().isBlank()) {
            throw new InvalidOffreException("Le type de contrat est obligatoire.");
        }

        if (offreDTO.salaire() != null && offreDTO.salaire() < 0) {
            throw new InvalidOffreException("Le salaire ne peut pas être négatif.");
        }


        Offre offre = new Offre();
        offre.setNom(offreDTO.nom());
        offre.setDescription(offreDTO.description());
        offre.setEntreprise(offreDTO.entreprise());
        offre.setLocalisation(offreDTO.localisation());
        offre.setTypeContrat(offreDTO.typeContrat());
        offre.setSalaire(offreDTO.salaire());

        offreDAO.saveOffre(offre);
        candidatureGateway.handleNewOffre(offreDTO);

        return offre.getId();

    }

}
