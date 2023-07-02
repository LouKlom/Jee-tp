package org.eclipse.jakarta.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.eclipse.jakarta.model.Client;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ClientDao {
    @PersistenceContext(unitName = "jpa-unit")
    private EntityManager em;
    private static List<Client> clients = new ArrayList<>();
    public void add(Client client) {
        em.persist(client);
    }

    public List<Client> getAll() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    public Client getClientByUsername(String username) {
        clients = getAll();
        return clients.stream()
                .filter(client -> client.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public boolean isValidUser(String username, String password) {
        clients = getAll();
        return clients.stream()
                .anyMatch(client -> client.getUsername().equals(username) && client.getPassword().equals(password));
    }

    public void delete(Client client) {
        em.remove(client);
    }

    public void update(Client client) {
        em.merge(client);
    }
}