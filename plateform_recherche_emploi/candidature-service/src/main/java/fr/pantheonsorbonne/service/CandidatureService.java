package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.OffreDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.UUID;

@ApplicationScoped
@Named("candidature")
@RegisterForReflection
public class CandidatureService {

    public OffreDTO createCandidatureForOffre(OffreDTO offreDTO) {
        return new OffreDTO(offreDTO.nom(), offreDTO.description(), offreDTO.entreprise(), offreDTO.candidatureNumber(), offreDTO.typeContrat(), offreDTO.salaire(), UUID.randomUUID().toString());
    }

}
