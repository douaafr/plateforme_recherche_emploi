/*package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.entity.Candidature;
import fr.pantheonsorbonne.service.CandidatureService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/candidature")

public class CandidatureResource {
    @Inject
    CandidatureService candidatureService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidature(@PathParam("id") Long id) {
        Candidature candidature = candidatureService.getCandidatureById(id);
        if (candidature == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(candidature).build();
    }
}
*/