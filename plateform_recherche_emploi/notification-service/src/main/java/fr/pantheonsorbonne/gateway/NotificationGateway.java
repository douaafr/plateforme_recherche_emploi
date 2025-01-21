package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.OffreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NotificationGateway implements Processor {


    @RestClient
    NotificationResource notificationResource;

    @Override
    public void process(Exchange exchange) throws Exception {
        // Log du corps brut reçu
        System.out.println("Message brut reçu : " + exchange.getMessage().getBody(String.class));

        // Convertir le message en OffreDTO
        var offreDTO = exchange.getMessage().getBody(OffreDTO.class);

        if (offreDTO == null) {
            throw new NullPointerException("L'objet OffreDTO est null après conversion !");
        }

        // Appeler le service REST
        try (Response resp = notificationResource.pushNotif("Nouvelle offre " + offreDTO.toString())) {
            if (resp.getStatus() != 200) {
                throw new Exception("Error Code: " + resp.getStatus());
            }
        }
    }

}
