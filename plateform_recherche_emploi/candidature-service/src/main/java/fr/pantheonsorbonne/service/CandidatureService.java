package fr.pantheonsorbonne.service;

//import fr.pantheonsorbonne.dao.CandidatureDAO;
import fr.pantheonsorbonne.dto.OffreDTO;
//import fr.pantheonsorbonne.entity.Candidature;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
@Named("candidature")
@RegisterForReflection
public class CandidatureService {

    //@Inject
    //CandidatureDAO candidatureDAO;

    //@Transactional
    public OffreDTO createCandidatureForOffre(OffreDTO offreDTO) {
        return new OffreDTO(offreDTO.nom(), offreDTO.description(), offreDTO.entreprise(), offreDTO.candidatureNumber(), offreDTO.typeContrat(), offreDTO.salaire(), UUID.randomUUID().toString());
    }

    /*public Candidature getCandidatureById(Long id) {
        return candidatureDAO.getById(id);
    }*/
}
