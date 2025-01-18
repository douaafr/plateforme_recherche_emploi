package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.gateway.NotificationGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @Inject
    NotificationGateway gateway;

    @Override
    public void configure() throws Exception {
        // Route pour écouter les événements de nouvelles offres publiées par offre-service
        from("activemq:queue:offres") // ActiveMQ (remplacez par votre broker, ex: Kafka)
                .unmarshal().json(OffreDTO.class) // Convertir le message JSON en OffreDTO
                .log(LoggingLevel.INFO, "Nouvelle offre détectée : ${body}")
                .process(exchange -> {
                    OffreDTO offreDTO = exchange.getMessage().getBody(OffreDTO.class);
                    // Construire un message de notification
                    String notificationMessage = "Nouvelle offre : " + offreDTO.getTitre() +
                            " - Description : " + offreDTO.getDescription();
                    exchange.getIn().setBody(notificationMessage);
                })
                .process(gateway) // Envoie la notification via NotificationGateway
                .log(LoggingLevel.INFO, "Notification envoyée avec succès pour l'offre : ${body}");
    }
}
