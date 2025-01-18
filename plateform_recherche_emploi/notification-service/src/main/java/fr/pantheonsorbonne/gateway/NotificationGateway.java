package fr.pantheonsorbonne.gateway;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NotificationGateway implements Processor {

    @RestClient
    NotificationRessource notificationRessource;

    @Override
    public void process(Exchange exchange) throws Exception {
        String notificationMessage = exchange.getMessage().getBody(String.class);

        try (Response resp = notificationRessource.pushNotif(notificationMessage)) {
            if (resp.getStatus() != 200) {
                throw new Exception("Erreur lors de l'envoi de la notification, Code: " + resp.getStatus());
            }
        }
    }
}
