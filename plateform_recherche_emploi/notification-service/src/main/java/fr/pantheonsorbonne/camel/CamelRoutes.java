package fr.pantheonsorbonne.camel;
import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.gateway.NotificationGateway;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.model.dataformat.JsonLibrary;


@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    NotificationGateway gateway;
    @Override
    public void configure() throws Exception {
        from("sjms2:M1.NotificationService")
                .unmarshal().json(JsonLibrary.Jackson, OffreDTO.class).
                process(gateway).end();

    }
}