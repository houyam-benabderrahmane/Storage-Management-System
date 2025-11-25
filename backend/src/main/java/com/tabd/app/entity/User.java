package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class User {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String role;
    private String adresse;
    private int nReg;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String email, String password, String name, String lastName, String role, String adresse, int nReg) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.adresse = adresse;
        this.nReg = nReg;
    }

    // Getters and setters

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getAdresse() {return adresse;}

    public void setAdresse(String adresse) {this.adresse = adresse;}

    public int getnReg() {return nReg;}

    public void setnReg(int nReg) {this.nReg = nReg;}
}
