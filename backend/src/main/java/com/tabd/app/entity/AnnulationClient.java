package com.tabd.app.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;

public class AnnulationClient {
    
    private Long idCommande;
    private Long client;
    private Long produitIdProduit;
    private Long produitCommercant;
    private Date dateCommand;
    private Date dateLivraison;
    private int quantity;
    private double prixTotal;
    private String etat;
    private Date dateAnnulation;

    public Long getIdCommande() {return idCommande;}

    public void setIdCommande(Long idCommande) {this.idCommande = idCommande;}

    public Long getClient() {return client;}

    public void setClient(Long client) {this.client = client;}

    public Long getProduitIdProduit() {return produitIdProduit;}

    public void setProduitIdProduit(Long produitIdProduit) {this.produitIdProduit = produitIdProduit;}

    public Long getProduitCommercant() {return produitCommercant;}

    public void setProduitCommercant(Long produitCommercant) {this.produitCommercant = produitCommercant;}

    public Date getDateCommand() {return dateCommand;}

    public void setDateCommand(Date dateCommand) {this.dateCommand = dateCommand;}

    public Date getDateLivraison() {return dateLivraison;}

    public void setDateLivraison(Date dateLivraison) {this.dateLivraison = dateLivraison;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getPrixTotal() {return prixTotal;}

    public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}

    public String getEtat() {return etat;}

    public void setEtat(String etat) {this.etat = etat;}

    public Date getDateAnnulation() {return dateAnnulation;}

    public void setDateAnnulation(Date dateAnnulation) {this.dateAnnulation = dateAnnulation;}
}
