package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:newOffre")
                .log("new offre sent to Candidature MS")
                .marshal().json()
                .to("sjms2:M1.CandidatureService");
    }
}
