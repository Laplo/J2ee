package java.fr.epsi.jeeProject.beans;

import java.sql.Date;

public class Utilisateur {

    public Utilisateur() {
    }

    public Utilisateur(String email) {
        this.email = email;
    }

    private String email;
    private String nom;
    private String password;
    private Date dateCreation;
    private Boolean admin;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
