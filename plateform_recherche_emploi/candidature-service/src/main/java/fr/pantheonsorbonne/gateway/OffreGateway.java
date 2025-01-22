package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.OffreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class OffreGateway {

    @Inject
    ProducerTemplate producerTemplate;
    public OffreDTO getOffreById(Long id) {
        return producerTemplate.requestBody("direct:getOffreById", id, OffreDTO.class);
    }

}
