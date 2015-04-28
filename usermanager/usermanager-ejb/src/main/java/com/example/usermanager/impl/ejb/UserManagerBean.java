package com.example.usermanager.impl.ejb;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.UserManager;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Stateless
@Remote(UserManager.class)
public class UserManagerBean implements UserManager {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public List<User> getAllUsers() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<User> query = em.createNamedQuery("User.getAllUsers", User.class);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<User> query = em.createNamedQuery("User.findUserByEmail", User.class);
            query.setParameter("email", email);
            List<User> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null.");
        }
        
        if (findUserByEmail(user.getEmail()) != null) {
            throw new UserException("A user with the email address already exists.");
        }

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } finally {
            if (em != null) {
                em.close();
            }
        }
                
    }
    
}
