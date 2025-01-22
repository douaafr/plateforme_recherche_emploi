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

    @POST
    public Response createCv(CvDTO cvDTO) {
        Long cvId = cvService.saveCvAndGetId(cvDTO);
        return Response.created(URI.create("/cv/" + cvId)).build();
    }



    @GET
    @Path("/{id}")
    public Response getCvById(@PathParam("id") Long id) {
        CvDTO cvDTO = cvService.getCvById(id);
        if (cvDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cvDTO).build();
    }


    @PUT
    @Path("/{id}")
    public Response updateCv(@PathParam("id") Long id, CvDTO cvDTO) {
        var updatedCv = cvService.updateCv(id, cvDTO);
        return Response.ok(updatedCv).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCv(@PathParam("id") Long id) {
        cvService.deleteCv(id);
        return Response.noContent().build();
    }
}