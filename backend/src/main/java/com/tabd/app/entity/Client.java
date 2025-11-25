package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;



public class Client {

    private Long id_client;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;

    public Client() {}

    @JsonCreator
    public Client(Long id_client,
                  String nom,
                  String prenom,
                  String email,
                  String password,
                  String role) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // getters and setters...

    public void setIdClient(Long id_client) {this.id_client = id_client;}

    public void setNom(String nom) {this.nom = nom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public void setRole(String role) {this.role = role;}

    // Getter methods
    public Long getIdClient() {return id_client;}

    public String getNom() {return nom;}

    public String getPrenom() {return prenom;}

    public String getEmail() {return email;}

    public String getPassword() {return password;}

    public String getRole() {return role;}

///////////////////////// Mapper /////////////////////////////////

    public Client mapClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setIdClient(rs.getLong("id_Client"));
        client.setNom(rs.getString("nom"));
        client.setPrenom(rs.getString("prenom"));
        client.setEmail(rs.getString("Email"));
        client.setPassword(rs.getString("Password"));
        client.setRole(rs.getString("role"));
        return client;
    }
    
///////////////////////////////////////////////////////////////////

}