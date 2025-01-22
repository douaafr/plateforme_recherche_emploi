package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.CandidatureDTO;
import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.exception.InvalidCandidatureException;
import fr.pantheonsorbonne.service.CandidatureService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/candidature")
public class CandidatureResource {
    @Inject
    CandidatureService candidatureService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCandidature(OffreDTO offreDTO) {
        try {
            CandidatureDTO candidatureDTO = candidatureService.createCandidatureForOffre(offreDTO);
            return Response.ok(candidatureDTO).build();
        } catch (InvalidCandidatureException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidatureById(@PathParam("id") Long id) {
        try {
            CandidatureDTO candidature = candidatureService.getCandidatureById(id);
            if (candidature == null) {
                throw new InvalidCandidatureException("Candidature with ID " + id + " not found.");
            }
            return Response.ok(candidature).build();
        } catch (InvalidCandidatureException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}/statut")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatut(@PathParam("id") Long id, String statut) {
        candidatureService.updateStatut(id, statut);
        return Response.noContent().build();
    }
}
