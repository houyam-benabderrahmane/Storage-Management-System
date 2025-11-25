package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;




public class Commercant {
    private Long idCommercant;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private int discount;
    private String role;

    public void setDiscount(int discount) {this.discount = discount;}

    public int getDiscount() {return this.discount;}

    public void setPassword(String password) {this.password = password;}

    public String getPassword() {return password;}

    public Long getIdCommercant() {return idCommercant;}

    public void setIdCommercant(Long idCommercant) {this.idCommercant = idCommercant;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public void setRole(String role) {this.role = role;}

    public String getRole() {return role;}


///////////////////////// Mapper /////////////////////////////////

    public Commercant mapCommercant(ResultSet rs) throws SQLException {
        Commercant commercant = new Commercant();
        commercant.setIdCommercant(rs.getLong("id_Commercant"));
        commercant.setNom(rs.getString("nom"));
        commercant.setPrenom(rs.getString("prenom"));
        commercant.setAdresse(rs.getString("adresse"));
        commercant.setEmail(rs.getString("Email"));
        commercant.setPassword(rs.getString("Password"));
        commercant.setDiscount(rs.getInt("discount"));
        commercant.setRole(rs.getString("role"));
        return commercant;
    }


///////////////////////////////////////////////////////////////////

}