package com.tabd.app.entity;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.SQLException;
import java.sql.ResultSet;






public class CommandeClient {
    private Long idCommande;
    private Long client;
    private Long produitIdProduit;
    private Long produitCommercant;
    private Date dateCommand;
    private Date dateLivraison;
    private int quantity;
    private double prixTotal;
    private String etat;

    // Getters
    public Long getIdCommande() {return idCommande;}

    public Long getClient() {return client;}

    public Long getProduitIdProduit() {return produitIdProduit;}

    public Long getProduitCommercant() {return produitCommercant;}

    public Date getDateCommand() {return dateCommand;}

    public Date getDateLivraison() {return dateLivraison;}

    public int getQuantity() {return quantity;}

    public double getPrixTotal() {return prixTotal;}

    public String getEtat() {return etat;}

    // Setters
    public void setIdCommande(Long idCommande) {this.idCommande = idCommande;}

    public void setClient(Long client) {this.client = client;}

    public void setProduitIdProduit(Long produitIdProduit) {this.produitIdProduit = produitIdProduit;}

    public void setProduitCommercant(Long produitCommercant) {this.produitCommercant = produitCommercant;}

    public void setDateCommand(Date dateCommand) {this.dateCommand = dateCommand;}

    public void setDateLivraison(Date dateLivraison) {this.dateLivraison = dateLivraison;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}

    public void setEtat(String etat) {this.etat = etat;}



///////////////////////// Mapper /////////////////////////////////

    public CommandeClient mapCommandeClient(ResultSet rs) throws SQLException {
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setIdCommande(rs.getLong("id_Commande"));
        commandeClient.setClient(rs.getLong("Client"));
        commandeClient.setProduitIdProduit(rs.getLong("produit_id_produit"));
        commandeClient.setProduitCommercant(rs.getLong("produit_commercant"));
        commandeClient.setDateCommand(rs.getDate("date_Command"));
        commandeClient.setDateLivraison(rs.getDate("date_livraison"));
        commandeClient.setQuantity(rs.getInt("Quantity"));
        commandeClient.setPrixTotal(rs.getDouble("prix_total"));
        commandeClient.setEtat(rs.getString("etat"));
        return commandeClient;
    }

///////////////////////////////////////////////////////////////////

}