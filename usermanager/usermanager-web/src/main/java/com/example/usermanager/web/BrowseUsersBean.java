package com.example.usermanager.web;

import com.example.usermanager.model.User;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class BrowseUsersBean {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public List<User> getAllUsers() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
