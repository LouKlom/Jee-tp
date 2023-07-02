package org.eclipse.jakarta.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jakarta.dao.PhoneDao;
import org.eclipse.jakarta.model.Phone;

import java.util.List;

@Path("/phone")
public class PhoneResource {

    @Inject
    PhoneDao phoneDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Phone> getAll() {
        return phoneDao.getAll();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Phone phone) {
        phoneDao.add(phone);
        return Response.status(Response.Status.CREATED).build();
    }

    @Transactional
    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Phone phone = phoneDao.findById(id);
        if(phone == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        phoneDao.delete(phone);
        return Response.status(Response.Status.ACCEPTED).build();
    }


    @Transactional
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id) {
        Phone phone = phoneDao.findById(id);
        if(phone == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        phoneDao.update(phone);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
