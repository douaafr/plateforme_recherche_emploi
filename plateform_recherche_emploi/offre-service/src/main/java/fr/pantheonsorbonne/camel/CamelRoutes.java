package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:newOffre")
                .log("new offre sent to multiple services")
                .marshal().json()
                .multicast().parallelProcessing()
                .to("sjms2:M1.CandidatureService", "sjms2:M1.NotificationService");
    }
}
