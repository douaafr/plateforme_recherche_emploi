package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.CvDTO;
import fr.pantheonsorbonne.service.CvService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/cv")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CvResource {

    @Inject
    CvService cvService;
/*
    // Endpoint pour créer un CV
    @POST
    public Response createCv(CvDTO cvDTO) {
        var cvEntity = cvService.saveCv(cvDTO);
        return Response.status(Response.Status.CREATED).entity(cvEntity).build();
    }

 */
    @POST
    public Response createCv(CvDTO cvDTO) {
        Long cvId = cvService.saveCvAndGetId(cvDTO); // Enregistrer le CV et obtenir son ID
        return Response.created(URI.create("/cv/" + cvId)).build(); // Retourne l'URI du CV créé
    }


    // Endpoint pour récupérer un CV par ID
    @GET
    @Path("/{id}")
    public Response getCvById(@PathParam("id") Long id) {
        CvDTO cvDTO = cvService.getCvById(id);
        if (cvDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cvDTO).build();
    }

    // Endpoint pour mettre à jour un CV
    @PUT
    @Path("/{id}")
    public Response updateCv(@PathParam("id") Long id, CvDTO cvDTO) {
        var updatedCv = cvService.updateCv(id, cvDTO);
        return Response.ok(updatedCv).build();
    }

    // Endpoint pour supprimer un CV
    @DELETE
    @Path("/{id}")
    public Response deleteCv(@PathParam("id") Long id) {
        cvService.deleteCv(id);
        return Response.noContent().build();
    }
}
