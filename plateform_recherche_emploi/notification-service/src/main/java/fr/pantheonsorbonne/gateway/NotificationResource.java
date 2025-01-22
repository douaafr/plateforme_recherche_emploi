package fr.pantheonsorbonne.gateway;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://sanspapier.nextnet.top/")
public interface NotificationResource {

    @POST
    @Path("nouvelle_offre")
    public Response pushNotif(String str);
}