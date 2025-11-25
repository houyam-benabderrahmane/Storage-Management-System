package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;




public class DemandeFournisseur {
    private Long idDemande;
    private Long fournisseur;
    private String nomProduit;
    private String description;
    private double prix;
    private double prixGros;
    private int quantityGros;
    private String categorie;
    private String etat;

    // Getters and setters for each field

    public Long getIdDemande() {return idDemande;}

    public void setIdDemande(Long idDemande) {this.idDemande = idDemande;}

    public Long getFournisseur() {return fournisseur;}

    public void setFournisseur(Long fournisseur) {this.fournisseur = fournisseur;}

    public String getNomProduit() {return nomProduit;}

    public void setNomProduit(String nomProduit) {this.nomProduit = nomProduit;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public double getPrix() {return prix;}

    public void setPrix(double prix) {this.prix = prix;}

    public double getPrixGros() {return prixGros;}

    public void setPrixGros(double prixGros) {this.prixGros = prixGros;}

    public int getQuantityGros() {return quantityGros;}

    public void setQuantityGros(int quantityGros) {this.quantityGros = quantityGros;}

    public String getCategorie() {return categorie;}

    public void setCategorie(String categorie) {this.categorie = categorie;}

    public void setEtat(String etat) {this.etat = etat;}

    public String getEtat() {return etat;}


///////////////////////// Mapper /////////////////////////////////


    public DemandeFournisseur mapDemandeFournisseur(ResultSet rs) throws SQLException {
        DemandeFournisseur demandeFournisseur = new DemandeFournisseur();
        demandeFournisseur.setIdDemande(rs.getLong("id_demande"));
        demandeFournisseur.setFournisseur(rs.getLong("Fournisseur"));
        demandeFournisseur.setNomProduit(rs.getString("nom_Produit"));
        demandeFournisseur.setDescription(rs.getString("description"));
        demandeFournisseur.setPrix(rs.getDouble("prix"));
        demandeFournisseur.setPrixGros(rs.getDouble("prix_gros"));
        demandeFournisseur.setQuantityGros(rs.getInt("quantity_gros"));
        demandeFournisseur.setCategorie(rs.getString("categorie"));
        demandeFournisseur.setEtat(rs.getString("etat"));
        return demandeFournisseur;
    }



///////////////////////////////////////////////////////////////////
}
