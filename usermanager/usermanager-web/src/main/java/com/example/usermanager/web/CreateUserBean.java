package com.example.usermanager.web;

import com.example.usermanager.model.User;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Named
@RequestScoped
public class CreateUserBean {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private String email;
    private String displayName;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null ? null : email.trim().toLowerCase());
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkAvailability() {
        if (email == null) {
            return;
        }

        FacesMessage msg;
        if (isEmailAvailable(email)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The email is available.", null);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The email is NOT available.", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String createUser() {
        User user = new User(email, displayName, password);
        if (!isEmailAvailable(email)) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "A user with the email address already exists.",
                    null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return "BrowseUsers?faces-redirect=true";
    }

    private boolean isEmailAvailable(String email) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getResultList().isEmpty();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
