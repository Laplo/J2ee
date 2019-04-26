package fr.epsi.jeeProject.domain;

import com.sun.security.auth.UserPrincipal;

import java.util.Date;

public class Article {

    private String title;
    private String body;
    private Date dateCreation;
    private UserPrincipal proprietaire;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public UserPrincipal getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(UserPrincipal proprietaire) {
        this.proprietaire = proprietaire;
    }
}