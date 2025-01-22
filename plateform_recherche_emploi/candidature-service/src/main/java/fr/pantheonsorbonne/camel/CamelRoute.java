package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.OffreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("sjms2:M1.CandidatureService")
                .log("Received a new offre")
                .unmarshal().json(OffreDTO.class)
                .bean("candidature", "createCandidatureForOffre")
                .multicast().parallelProcessing()
                .to("direct:sendToCvService")
                .to("direct:storeLocally");


        from("direct:sendToCvService")
                .marshal().json()
                .to("sjms2:M1.CvService");


        from("direct:storeLocally")
                .process(exchange -> {
                    OffreDTO offre = exchange.getMessage().getBody(OffreDTO.class);
                    exchange.getMessage().setBody(List.of(
                            offre.nom(),
                            offre.description(),
                            offre.entreprise(),
                            offre.localisation(),
                            offre.typeContrat(),
                            offre.salaire(),
                            offre.candidatureNumber()
                    ));
                })
                .marshal().csv()
                .to("file:data?fileName=candidature_storage.csv&appendChars=\\n&fileExist=Append");

    }
}