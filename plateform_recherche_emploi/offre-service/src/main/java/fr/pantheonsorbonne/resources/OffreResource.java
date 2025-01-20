package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.OffreDTO;
import fr.pantheonsorbonne.entity.Offre;
import fr.pantheonsorbonne.exception.InvalidOffreException;
import fr.pantheonsorbonne.service.OffreService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("offre")
public class OffreResource {


    @Inject
    OffreService offreService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffreById(@PathParam("id") Long id){

       OffreDTO offre = offreService.getOffreById(id);
       if(offre == null){
           throw new WebApplicationException(Response.Status.NOT_FOUND);
       }
       return Response.ok(offre).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postOffre(OffreDTO offreDTO){
        try {
            Long offreId = offreService.checkAndSaveOffre(offreDTO);
            return Response.created(URI.create("/offre/"+offreId)).build();
        } catch (InvalidOffreException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
    }

}
