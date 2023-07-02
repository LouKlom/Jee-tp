package org.eclipse.jakarta.dao;

import java.util.List;


import jakarta.ws.rs.NotFoundException;
import org.eclipse.jakarta.model.Phone;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class PhoneDao {
    @PersistenceContext(unitName = "jpa-unit")
    private EntityManager em;

    public void add(Phone phone) {
        em.persist(phone);
    }

    public List<Phone> getAll() {
        return em.createNamedQuery("Phone.findAll", Phone.class).getResultList();
    }

    public void delete(Phone phone) {
        em.remove(phone);
    }

    public void update(Phone phone) {
        em.merge(phone);
    }

    public Phone findById(Long id) {
        Phone phone = em.find(Phone.class, id);
        if(id == null) {
            throw new NotFoundException("Can't find Phone for ID ");
        }
        return phone;
    }
}
