package fr.pantheonsorbonne.ressource;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://sanspapier.nextnet.top/") // Remplacez par l'URL de votre service ntfy
public interface notificationRessource {

    @POST
    @Path("notif_m1") // L'endpoint du service REST pour envoyer les notifications
    public Response pushNotif(String message);
}
