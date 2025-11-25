package com.tabd.app.repository;

import com.tabd.app.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.dao.EmptyResultDataAccessException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Date;

@Repository
public class FournisseurRepository {

    @Autowired
    private JdbcTemplate template;
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


///////////////////////// Mappers /////////////////////////////////




////////////////////////////////////////////////////////////////////

    public Fournisseur authenticate(String email, String password) {
        String sql = "SELECT * FROM Fournisseur WHERE email = ?";
        Fournisseur fournisseur = new Fournisseur(); 
        try {
            return template.queryForObject(sql,new Object[]{email},
                    (rs, rowNum) -> fournisseur.mapFournisseur(rs));
        } catch (EmptyResultDataAccessException e) {
            return null; // User not found
        }
    }


    public boolean ifValide(long idFournisseur) {
        String sql = "SELECT (compte_valide) FROM Fournisseur WHERE id_fournisseur = ?;";
        try {
            return template.queryForObject(sql, Boolean.class, idFournisseur);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return false;
        }


    }

    public String deleteDemandeFournisseur(int commandeId) {
        String sql = "DELETE FROM Demande_Fournisseur WHERE id_demande = ? AND etat NOT IN ('valide', 'refus') ";
        try {
            int rowsAffected = template.update(sql, commandeId);
            if (rowsAffected == 1) {
                return "Demande deleted successfully.";
            } else {
                return "Demande not found with ID: " + commandeId;
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            return "Error deleting demande with ID: " + commandeId;
        }
    }


    public String creerDemandeFournisseur(
        int fournisseurId, 
        String nomProduit, 
        String description,
        double prix, 
        double prixGros, 
        int quantityGros, 
        String categorie) {
        String sql = "CALL CreerDemandeFournisseur(?, ?, ?, ?, ?, ?, ?)";

        try {
            return template.queryForObject(sql, new Object[]{fournisseurId, nomProduit, description, prix, prixGros, quantityGros, categorie},
                    (rs, rowNum) -> rs.getString("Message"));
        } catch (Exception e) {
            // Handle exceptions, log, or return an error message
            return "Error creating demande fournisseur.";
        }
    }

    public String validerCommandeCommercant(int commandeId, String etatChoisi) {

        String sql = "CALL ValiderCommandeCommercant(?, ?)";
        try {
            // Call the stored procedure
            String result = template.queryForObject(
                    sql,
                    String.class,
                    commandeId,
                    etatChoisi);

            return result;
        } catch (Exception e) {
            return "Error validating CommandeCommercant.";
        }
    }

    public List<ProduitFourni> getProduitsFournisseur(Long idFournisseur) {
        String sql = "SELECT * FROM Produit_Fourni WHERE Fournisseur = ?";
        ProduitFourni produitFourni = new ProduitFourni(); 
        try {
            return template.query(sql, new Object[]{idFournisseur},
                    (rs, rowNum) -> produitFourni.mapProduitFourni(rs));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList(); // No produits found for the fournisseur
        }
    }

    public List<DemandeFournisseur> getDemandesFournisseur(Long idFournisseur) {
        String sql = "SELECT * FROM demande_Fournisseur WHERE Fournisseur = ?;";
        DemandeFournisseur demandeFournisseur = new DemandeFournisseur();
        try {
            return template.query(sql, new Object[]{idFournisseur},
                    (rs, rowNum) -> demandeFournisseur.mapDemandeFournisseur(rs));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList(); 
        }
    }


    public List<CommandeCommercant> getCommandeCommercantByFournisseurId(long fournisseurId) {
        String sql = "SELECT cc.* FROM Commande_Commercant cc " +
                     "JOIN Produit_Fourni pf ON cc.Produit_Fourni = pf.id " +
                     "WHERE pf.Fournisseur = ? AND CC.etat IN ('en attente');";
        CommandeCommercant commandeCommercant = new CommandeCommercant();             
        return template.query(sql,
                 new Object[] {fournisseurId},
                (rs, rowNum) -> commandeCommercant.mapCommandeCommercant(rs));
    }
    
    public List<CommandeCommercant> getCommandeCommercantByFournisseurIdValide(long fournisseurId) {
        String sql = "SELECT cc.* FROM Commande_Commercant cc " +
                     "JOIN Produit_Fourni pf ON cc.Produit_Fourni = pf.id " +
                     "WHERE pf.Fournisseur = ? AND CC.etat IN ('valide');";
        CommandeCommercant commandeCommercant = new CommandeCommercant();             
        return template.query(sql,
                 new Object[] {fournisseurId},
                (rs, rowNum) -> commandeCommercant.mapCommandeCommercant(rs));
    }
    

    public List<String> getCategories() {
        String sql = "SELECT c.nom FROM categorie c;";
        return template.queryForList(sql, String.class);
    }

    public String retirerProduit(int produitId) {
        String sql = "CALL retirerProduit(?)";

        try {
            return template.queryForObject(
                        sql, 
                        new Object[]{produitId}, 
                        String.class);
        } catch (Exception e) {
            return "Error occurred while removing the product.";
        }
    }



}
