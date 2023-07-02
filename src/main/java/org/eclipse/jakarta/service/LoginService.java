package org.eclipse.jakarta.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.jakarta.dao.ClientDao;
import org.eclipse.jakarta.model.Client;

import java.security.SecureRandom;
import java.util.Base64;

@RequestScoped
public class LoginService {
    @Inject
    ClientDao clientDao;
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    public boolean authenticate(String username, String password) {
        return clientDao.isValidUser(username, password);
    }

    public String getTokenFromBdd(String username) {
        Client client = clientDao.getClientByUsername(username);
        return client.getToken();
    }

    public String generateToken(String username) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}