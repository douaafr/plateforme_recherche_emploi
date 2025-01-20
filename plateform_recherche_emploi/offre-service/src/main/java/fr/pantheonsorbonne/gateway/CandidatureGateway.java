package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.OffreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class CandidatureGateway {

    @Inject
    ProducerTemplate producerTemplate;

    public void handleNewOffre (OffreDTO offre) {

       producerTemplate.sendBody("direct:newOffre", offre);
    }
}
