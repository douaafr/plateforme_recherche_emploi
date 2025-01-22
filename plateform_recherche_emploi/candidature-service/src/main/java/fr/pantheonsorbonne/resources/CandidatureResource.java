package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.CandidatureDTO;
import fr.pantheonsorbonne.dto.OffreDTO;
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

    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidature(@PathParam("id") Long id) {
        Candidature candidature = candidatureService.getCandidatureById(id);
        if (candidature == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(candidature).build();
    } */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCandidature(OffreDTO offreDTO) {
        try {
            CandidatureDTO candidatureDTO = candidatureService.createCandidatureForOffre(offreDTO);
            return Response.ok(candidatureDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidatureById(@PathParam("id") Long id) {
        CandidatureDTO candidature = candidatureService.getCandidatureById(id);
        if (candidature == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(candidature).build();
    }

    @PUT
    @Path("{id}/statut")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatut(@PathParam("id") Long id, String statut) {
        candidatureService.updateStatut(id, statut);
        return Response.noContent().build();
    }

}
