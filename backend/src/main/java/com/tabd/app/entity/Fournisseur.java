package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;


import java.util.Date;

public class Fournisseur {
    private Long idFournisseur;
    private int nReg;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private boolean compteValide;
    private String role;

    // Constructors

    // Setters and Getters

    public Long getIdFournisseur() {return idFournisseur;}

    public void setIdFournisseur(Long idFournisseur) {this.idFournisseur = idFournisseur;}

    public int getNReg() {return nReg;}

    public void setNReg(int nReg) {this.nReg = nReg;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public boolean isCompteValide() {return compteValide;}

    public void setCompteValide(boolean compteValide) {this.compteValide = compteValide;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

///////////////////////// Mapper /////////////////////////////////


    public Fournisseur mapFournisseur(ResultSet rs) throws SQLException {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(rs.getLong("id_Fournisseur"));
        fournisseur.setNReg(rs.getInt("n_Reg"));
        fournisseur.setNom(rs.getString("nom"));
        fournisseur.setPrenom(rs.getString("prenom"));
        fournisseur.setAdresse(rs.getString("adresse"));
        fournisseur.setEmail(rs.getString("Email"));
        fournisseur.setPassword(rs.getString("Password"));
        fournisseur.setCompteValide(rs.getBoolean("Compte_Valide"));
        fournisseur.setRole(rs.getString("role"));
        return fournisseur;
    }


///////////////////////////////////////////////////////////////////
}
