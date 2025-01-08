package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.CvDTO;
import fr.pantheonsorbonne.service.CvService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("cvs")
public class CvResource {


    @Inject
    CvService cvService;


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCVbyId(@PathParam("id") Long id) { //retourner un cv
        CvDTO cv = cvService.getCVbyId(id);
        if (cv == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(cv).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCV(CvDTO cvDTO) { //prend le CV pour l'enregistrer
        try {
            Long cvId = cvService.saveCV(cvDTO);
            return Response.created(URI.create("/cv/" + cvId)).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}