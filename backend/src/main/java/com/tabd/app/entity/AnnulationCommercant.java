package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

public class AnnulationCommercant {

    private Long idCommande;
    private Long commercant;
    private Long produitFourni;
    private Date dateCommand;
    private Integer quantity;
    private double prixTotal;
    private String etat;
    private Date dateAnnulation;

    // Getter and setter methods for idCommande
    public Long getIdCommande() {return idCommande;}

    public void setIdCommande(Long idCommande) {this.idCommande = idCommande;}

    // Getter and setter methods for commercant
    public Long getCommercant() {return commercant;}

    public void setCommercant(Long commercant) {this.commercant = commercant;}

    // Getter and setter methods for produitFourni
    public Long getProduitFourni() {return produitFourni;}

    public void setProduitFourni(Long produitFourni) {this.produitFourni = produitFourni;}

    // Getter and setter methods for dateCommand
    public Date getDateCommand() {return dateCommand;}

    public void setDateCommand(Date dateCommand) {this.dateCommand = dateCommand;}

    // Getter and setter methods for quantity
    public Integer getQuantity() {return quantity;}

    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    // Getter and setter methods for prixTotal
    public double getPrixTotal() {return prixTotal;}

    public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}

    // Getter and setter methods for etat
    public String getEtat() {return etat;}

    public void setEtat(String etat) {this.etat = etat;}

    // Getter and setter methods for dateAnnulation
    public Date getDateAnnulation() {return dateAnnulation;}

    public void setDateAnnulation(Date dateAnnulation) {this.dateAnnulation = dateAnnulation;}

///////////////////////// Mapper /////////////////////////////////

    public AnnulationCommercant mapAnnulationCommercant(ResultSet rs) throws SQLException {
        AnnulationCommercant annulationCommercant = new AnnulationCommercant();
        annulationCommercant.setIdCommande(rs.getLong("id_commande"));
        annulationCommercant.setCommercant(rs.getLong("commercant"));
        annulationCommercant.setProduitFourni(rs.getLong("produit_fourni"));
        annulationCommercant.setDateCommand(rs.getDate("date_command"));
        annulationCommercant.setQuantity(rs.getInt("quantity"));
        annulationCommercant.setPrixTotal(rs.getDouble("prix_total"));
        annulationCommercant.setEtat(rs.getString("etat"));
        annulationCommercant.setDateAnnulation(rs.getDate("date_annulation"));

        return annulationCommercant;
    }

///////////////////////////////////////////////////////////////////

}