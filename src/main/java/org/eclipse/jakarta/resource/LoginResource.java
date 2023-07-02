package org.eclipse.jakarta.resource;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.*;
import org.eclipse.jakarta.dao.ClientDao;
import org.eclipse.jakarta.model.Client;
import org.eclipse.jakarta.service.LoginService;

@Path("/login")
public class LoginResource {

    @Inject
    private LoginService loginService;

    @Inject
    ClientDao clientDao;

    @POST
    @Path("/login")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(JsonObject credentials, @Context UriInfo uriInfo) {
        System.err.println("hello");

        String username = credentials.getString("username");
        String password = credentials.getString("password");

        // Vérification des informations d'identification
        if (!loginService.authenticate(username, password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Client client = clientDao.getClientByUsername(username);

        // Génération du jeton d'accès
        String token = loginService.generateToken(username);

        // Construction de la réponse
        JsonObject responseJson = Json.createObjectBuilder()
                .add("token", token)
                .build();

        client.setToken(token);
        clientDao.update(client);


        // Ajout du jeton d'accès dans l'en-tête de réponse
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(token);
        return Response.ok(responseJson)
                .header("Authorization", "Bearer " + token)
                .location(uriBuilder.build())
                .build();
    }
}