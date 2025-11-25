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
import java.util.Date;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


///////////////////////// Mappers /////////////////////////////////


///////////////////////////////////////////////////////////////////


    public Admin authenticate(String email, String password) {
        String sql = "SELECT * FROM Admin WHERE Email = ?";
        Admin admin = new Admin();
        try {
            return template.queryForObject(sql, new Object[]{email},
                    (rs, rowNum) -> admin.mapAdmin(rs));
        } catch (EmptyResultDataAccessException e) {
            // No user found with the given email
            return null;
        }
    }

    public List<Fournisseur> getAllFournisseursNonValid() {
        String sql = "SELECT * FROM Fournisseur WHERE compte_valide = false";
        Fournisseur fournisseur = new Fournisseur();
        return template.query(sql, (rs, rowNum) -> fournisseur.mapFournisseur(rs));
    }

    public String validerFournisseur(int fournisseurId) {
        String sql = "CALL ValiderCompteFournisseur(?)";

        try {
            return template.queryForObject(
                        sql, 
                        new Object[]{fournisseurId}, 
                        String.class);
        } catch (Exception e) {
            return "Invalid Fournisseur ID.";
        }
    }

    public String deleteFournisseur(long fournisseurId) {
        String sql = "DELETE FROM Fournisseur WHERE id_fournisseur = ?";

        int rowsAffected = template.update(
                sql,
                new Object[]{fournisseurId},
                new int[]{Types.BIGINT}
        );

        if (rowsAffected > 0) {
            return "Fournisseur deleted successfully";
        } else {
            return "Fournisseur with ID " + fournisseurId + " not found";
        }
    }

    public String validerDemandeFournisseur(int demandeId, String etatChoisi) {
        String sql = "CALL ValiderProduitFournisseur(?, ?)";

        try {
            return template.queryForObject(
                sql, 
                new Object[]{demandeId, etatChoisi}, 
                String.class);
        } catch (Exception e) {
            return "Invalid demandeFournisseur ID.";
        }
    }

    public List<DemandeFournisseur> getDemandesFournisseur() {
        String sql = "SELECT * FROM demande_Fournisseur WHERE etat = 'en attente';";
        DemandeFournisseur demandeFournisseur = new DemandeFournisseur();
        return template.query(sql, (rs, rowNum) -> demandeFournisseur.mapDemandeFournisseur(rs));
    }

    public String ajouterCategorie(String newCategory) {
        String sql = "CALL AjouterCategorie(?)";
        
        try {
            return template.queryForObject(
                    sql,
                    new Object[]{newCategory},
                    String.class);
        } catch (Exception e) {
            return "Error adding category.";
        }
    }

    public List<ProduitFourni> getProduitFourni() {
        String sql = "SELECT * FROM Produit_Fourni";
        ProduitFourni produitFourni = new ProduitFourni();
        return template.query(sql, (rs, rowNum) -> produitFourni.mapProduitFourni(rs));
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
