package com.tabd.app.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ProduitCommercant {

    private Long idProduitCommercant;
    private String libelle;
    private double prix;
    private String categorie;
    private String description;
    private int stock;
    private Long commercant;

    public Long getCommercant() {return commercant;}

    public void setCommercant(Long commercant) {this.commercant = commercant;}

    public Long getIdProduitCommercant() {return idProduitCommercant;}

    public void setIdProduit(Long idProduitCommercant) {this.idProduitCommercant = idProduitCommercant;}

    public String getLibelle() {return libelle;}

    public void setLibelle(String libelle) {this.libelle = libelle;}

    public double getPrix() {return prix;}

    public void setPrix(double prix) {this.prix = prix;}


    public String getCategorie() {return categorie;}

    public void setCategorie(String categorie) {this.categorie = categorie;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}


///////////////////////// Mapper /////////////////////////////////

    public ProduitCommercant mapProduitCommercant(ResultSet rs) throws SQLException {
        ProduitCommercant produitCommercant = new ProduitCommercant();
        produitCommercant.setIdProduit(rs.getLong("id_produit"));
        produitCommercant.setLibelle(rs.getString("libelle"));
        produitCommercant.setPrix(rs.getDouble("prix"));
        produitCommercant.setCategorie(rs.getString("categorie"));
        produitCommercant.setDescription(rs.getString("description"));
        produitCommercant.setStock(rs.getInt("stock"));
        produitCommercant.setCommercant(rs.getLong("commercant"));

        return produitCommercant;
    }

///////////////////////////////////////////////////////////////////
}