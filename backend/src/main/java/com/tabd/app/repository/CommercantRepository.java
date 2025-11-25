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
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;

@Repository
public class CommercantRepository {

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



///////////////////////// Mappers /////////////////////////////////


////////////////////////////////////////////////////////////////////

    
    public Commercant authenticate(String email, String password) {
        String sql = "SELECT * FROM Commercant WHERE email = ?";
        Commercant commercant = new Commercant();
        try {
            return template.queryForObject(sql, new Object[]{email},
                    (rs, rowNum) -> commercant.mapCommercant(rs));
        } catch (EmptyResultDataAccessException e) {
            // No user found with the given email
            return null;
        }
    }

    public List<CommandeClient> getCommandesClientValide(Long idCommercant) {
        String sql = "SELECT * FROM Commande_Client WHERE produit_commercant = ? AND etat IN ('valide')";
        CommandeClient commandeClient = new CommandeClient();
        return template.query(
            sql, 
            new Object[]{idCommercant}, 
            (rs, rowNum) -> commandeClient.mapCommandeClient(rs));
    }

    public List<CommandeClient> getCommandesClientEnAttente(Long idCommercant) {
        String sql = "SELECT * FROM Commande_Client WHERE produit_commercant = ? AND etat IN ('en attente')";
        CommandeClient commandeClient = new CommandeClient();
        return template.query(
            sql, 
            new Object[]{idCommercant}, 
            (rs, rowNum) -> commandeClient.mapCommandeClient(rs));
    }



    public String validerCommandeClient(Long commandeId, String etatChoisi, String livraisonDate) {
        String sql = "CALL ValiderCommandeClient(?, ?, ?)";

        try {
            return template.queryForObject(
                    sql,
                    new Object[]{commandeId, etatChoisi, livraisonDate},
                    String.class
            );
        } catch (Exception e) {
            e.printStackTrace();  // Print the exception details for debugging
            return "An error occurred during validation.";
        }
    }


    public List<ProduitFourni> getAllProduitFourni() {
        String sql = "SELECT * FROM Produit_Fourni";
        ProduitFourni produitFourni = new ProduitFourni();
        return template.query(sql, (rs, rowNum) -> produitFourni.mapProduitFourni(rs));
    }


    public String placerCommandeCommercant(int merchantID, int productID, int quantity) {
        String sql = "CALL PlacerCommandeCommercant(?, ?, ?)";
        
        try {
            return template.queryForObject(sql, new Object[]{merchantID, productID, quantity}, String.class);
        } catch (Exception e) {
            return "Error placing order.";
        }
    }


    public List<CommandeCommercant> getCommandesCommercant(int idCommercant) {
        String sql = "SELECT * FROM Commande_Commercant WHERE commercant = ? AND etat NOT IN ('annule')";
        CommandeCommercant commandeCommercant = new CommandeCommercant();
        return template.query(
                sql,
                new Object[]{idCommercant},
                (rs, rowNum) -> commandeCommercant.mapCommandeCommercant(rs)
        );
    }

    public String annuleCommandeCommercant(int commandeId) {
        String sql = "CALL AnnuleComandCommercant(?)";

        try {
            return template.queryForObject(sql, new Object[]{commandeId}, String.class);
        } catch (Exception e) {
            return "Erreur lors de l'annulation de la commande.";
        }
    }

    public List<ProduitCommercant> getProduitCommercantByCommercantId(long commercantId) {
        String sql = "SELECT * FROM Produit_Commercant WHERE commercant = ?";
        ProduitCommercant produitCommercant = new ProduitCommercant();
        return template.query(
                sql,
                new Object[]{commercantId},
                (rs, rowNum) -> produitCommercant.mapProduitCommercant(rs)
        );
    }

    public List<AnnulationCommercant> getAnnulationCommercantByCommercantId(long commercantId) {
        String sql = "SELECT ac.*, cc.* FROM annulation_commercant ac " +
                     "JOIN commande_commercant cc ON  cc.id_commande = ac.commande " +
                     "WHERE cc.commercant = ?";
        
        AnnulationCommercant annulationCommercant = new AnnulationCommercant();
        
        return template.query(
                sql,
                new Object[]{commercantId},
                (rs, rowNum) -> annulationCommercant.mapAnnulationCommercant(rs));
    }



}
