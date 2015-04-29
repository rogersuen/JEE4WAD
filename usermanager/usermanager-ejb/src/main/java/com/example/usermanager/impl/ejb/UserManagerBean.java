package com.example.usermanager.impl.ejb;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.UserManager;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@WebService
@Stateless
@Remote(UserManager.class)
public class UserManagerBean implements UserManager {

    @PersistenceContext
    private EntityManager em;

    @WebMethod
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("User.getAllUsers", User.class);
        return query.getResultList();
    }

    @WebMethod
    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }

        TypedQuery<User> query = em.createNamedQuery("User.findUserByEmail", User.class);
        query.setParameter("email", email);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
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

        em.persist(user);
        
        return user;
    }

}
