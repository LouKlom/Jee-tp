package org.eclipse.jakarta.resource;

import java.util.List;


import org.eclipse.jakarta.dao.ClientDao;
import org.eclipse.jakarta.model.Client;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/client")
public class ClientResource {

    @Inject
    ClientDao clientDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Client client) {
        clientDao.add(client);
        return Response.status(Status.CREATED).build();
    }
}
