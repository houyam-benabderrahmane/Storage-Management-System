package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProduitFourni {
    private Long idProduitFourni;
    private Long idFournisseur;
    private String nomProduit;
    private String categorie;
    private String description;
    private double prixUnitaire;
    private double prixGros;
    private int quantiteGros;

    // Getters and setters

    public void setCategorie(String categorie) {this.categorie = categorie;}

    public String getCategorie() {return categorie;}

    public void setPrixGros(double prixGros) {this.prixGros = prixGros;}

    public double getPrixGros() {return prixGros;}

    public Long getIdProduitFourni() {return idProduitFourni;}

    public void setIdProduitFourni(Long idProduitFourni) {this.idProduitFourni = idProduitFourni;}

    public Long getIdFournisseur() {return idFournisseur;}

    public void setIdFournisseur(Long idFournisseur) {this.idFournisseur = idFournisseur;}

    public String getNomProduit() {return nomProduit;}

    public void setNomProduit(String nomProduit) {this.nomProduit = nomProduit;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Double getPrixUnitaire() {return prixUnitaire;}

    public void setPrixUnitaire(Double prixUnitaire) {this.prixUnitaire = prixUnitaire;}

    public Integer getQuantiteGros() {return quantiteGros;}

    public void setQuantiteGros(int quantiteGros) {this.quantiteGros = quantiteGros;}


///////////////////////// Mappers /////////////////////////////////

    public ProduitFourni mapProduitFourni(ResultSet rs) throws SQLException {
        ProduitFourni produitFourni = new ProduitFourni();
        produitFourni.setIdProduitFourni(rs.getLong("id"));
        produitFourni.setIdFournisseur(rs.getLong("fournisseur"));
        produitFourni.setNomProduit(rs.getString("nom_produit"));
        produitFourni.setDescription(rs.getString("description"));
        produitFourni.setPrixUnitaire(rs.getDouble("prix"));
        produitFourni.setQuantiteGros(rs.getInt("quantity_gros"));
        produitFourni.setPrixGros(rs.getDouble("prix_gros"));
        produitFourni.setCategorie(rs.getString("categorie"));
        return produitFourni;
    }
///////////////////////////////////////////////////////////////////

}