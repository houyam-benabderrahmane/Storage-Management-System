package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;



public class Admin {

    private Long idAdmin;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;

    // Getters and setters

    public Long getIdAdmin() {return idAdmin;}

    public void setIdAdmin(Long idAdmin) {this.idAdmin = idAdmin;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}



///////////////////////// Mappers /////////////////////////////////

    public Admin mapAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setIdAdmin(rs.getLong("id_Admin"));
        admin.setNom(rs.getString("nom"));
        admin.setPrenom(rs.getString("prenom"));
        admin.setEmail(rs.getString("Email"));
        admin.setPassword(rs.getString("Password"));
        admin.setRole(rs.getString("role"));
        return admin;
    }

///////////////////////////////////////////////////////////////////
}
