package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

public class CommandeCommercant {

    private Long idCommande;
    private Long commercant;
    private Long produitFourni;
    private Date dateCommand;
    private Integer quantity;
    private double prixTotal;
    private String etat;

    // getters, and setters

    public Long getIdCommande() {return idCommande;}

    public void setIdCommande(Long idCommande) {this.idCommande = idCommande;}

    public Long getCommercant() {return commercant;}

    public void setCommercant(Long commercant) {this.commercant = commercant;}

    public Long getProduitFourni() {return produitFourni;}

    public void setProduitFourni(Long produitFourni) {this.produitFourni = produitFourni;}

    public Date getDateCommand() {return dateCommand;}

    public void setDateCommand(Date dateCommand) {this.dateCommand = dateCommand;}

    public Integer getQuantity() {return quantity;}

    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    public double getPrixTotal() {return prixTotal;}

    public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}

    public String getEtat() {return etat;}

    public void setEtat(String etat) {this.etat = etat;}


///////////////////////// Mapper /////////////////////////////////

    public CommandeCommercant mapCommandeCommercant(ResultSet rs) throws SQLException {
        CommandeCommercant commandeCommercant = new CommandeCommercant();
        commandeCommercant.setIdCommande(rs.getLong("id_Commande"));
        commandeCommercant.setCommercant(rs.getLong("Commercant"));
        commandeCommercant.setProduitFourni(rs.getLong("produit_fourni"));
        commandeCommercant.setDateCommand(rs.getDate("date_command"));
        commandeCommercant.setQuantity(rs.getInt("quantity"));
        commandeCommercant.setPrixTotal(rs.getDouble("prix_total"));
        commandeCommercant.setEtat(rs.getString("etat"));

        return commandeCommercant;
    }

///////////////////////////////////////////////////////////////////
}
