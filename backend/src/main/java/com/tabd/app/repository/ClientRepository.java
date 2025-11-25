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
public class ClientRepository {

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;




///////////////////////// Mappers /////////////////////////////////





///////////////////////////////////////////////////////////////////


    public Client authenticate(String email, String password) {
        String sql = "SELECT * FROM Client WHERE Email = ?";
        Client client = new Client();
        try {
            return template.queryForObject(sql, new Object[]{email},
                    (rs, rowNum) -> client.mapClient(rs));
        } catch (EmptyResultDataAccessException e) {
            // No user found with the given email
            return null;
        }
    }



/*    public Client authenticate(String email, String password) {

    }    */

    // jsut for jdbc testing
    public List<Client> getAll() {
        String query = "SELECT id_client, email, nom, password, prenom, role FROM Client";
        return namedParameterJdbcTemplate.query(query, (rs, rowNum) ->
                new Client(
                        rs.getLong("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                )
        );
    }

    // procedure ProduitCommercantSort ----->
    public List<ProduitCommercant> getProduitCommercantSorted(String sortOption) {
        String sql = "CALL AfficherProduitComercant(?)";

        return template.query(sql, new Object[]{sortOption},
                (rs, rowNum) -> {
                    ProduitCommercant produitCommercant = new ProduitCommercant();
                    produitCommercant.setIdProduit(rs.getLong("id_Produit"));
                    produitCommercant.setLibelle(rs.getString("Libelle"));
                    produitCommercant.setPrix(rs.getDouble("Prix"));
                    produitCommercant.setCategorie(rs.getString("Categorie"));
                    produitCommercant.setDescription(rs.getString("Description"));
                    produitCommercant.setStock(rs.getInt("stock"));
                    produitCommercant.setCommercant(rs.getLong("Commercant"));
                    return produitCommercant;
                });
    }

    // procedure PlacerCommandeClient ----->
    public String placeOrder(int clientID, int productID, int merchantID, int quantity) {
        String sql = "CALL PlacerCommandeClient(?, ?, ?, ?)";
        return template.queryForObject(
                    sql, 
                    new Object[]{clientID, productID, merchantID, quantity}, 
                    (rs, rowNum) -> rs.getString("Result"));
    }



    // procedure AnnuleClient ----->
    public String annuleClient(int commandID) {
        String sql = "CALL AnnuleComandeClient(?)";
        return template.queryForObject(sql, new Object[]{commandID}, String.class);
    }


    // procedure ProduitPlusVendue ----->
    public List<ProduitCommercant> callProduitPlusVendue() {
        List<ProduitCommercant> produitCommercants = new ArrayList<>();

        try (Connection connection = template.getDataSource().getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call produitPlusVendue()}")) {

            // Execute the stored procedure
            boolean hasResults = callableStatement.execute();

            // Process the result set
            while (hasResults) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    while (resultSet.next()) {
                        ProduitCommercant produitCommercant = new ProduitCommercant();
                        produitCommercant.setIdProduit(resultSet.getLong("id_Produit"));
                        produitCommercant.setLibelle(resultSet.getString("Libelle"));
                        produitCommercant.setPrix(resultSet.getDouble("Prix"));
                        produitCommercant.setCategorie(resultSet.getString("categorie"));
                        produitCommercant.setDescription(resultSet.getString("Description"));
                        produitCommercant.setStock(resultSet.getInt("stock"));
                        produitCommercant.setCommercant(resultSet.getLong("Commercant"));
                        // Set other attributes as needed

                        produitCommercants.add(produitCommercant);
                    }
                }
                // Check for more result sets
                hasResults = callableStatement.getMoreResults();
            }

        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return produitCommercants;
    }

    // procedure CalculerTotalClient ----->
    public List<Map<String, Object>> calculateTotalAmountForClient(int clientID) {
        String sql = "CALL CalculerTotalClient(?)";

        return template.queryForList(sql, clientID);
    }

    // get Cammande_Client for this Client ----->
	public List<CommandeClient> getCommandeClientByClientId(Long clientId) {
	    String sql = "SELECT * FROM Commande_Client WHERE Client = ? AND etat NOT IN ('annule')";

	    return template.query(sql, new Object[]{clientId},
	            (rs, rowNum) -> {
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
	            });
	}

	// get Annulation_Client for this Client ----->
    public List<AnnulationClient> getAnnulations(Long clientId) {
        String sql = "SELECT ac.*, cc.* FROM Annulation_Client ac " +
                     "JOIN Commande_Client cc ON ac.Commande = cc.id_Commande " +
                     "WHERE cc.Client = :clientId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("clientId", clientId);
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            AnnulationClient annulationClient = new AnnulationClient();
            // Set properties for AnnulationClient
            annulationClient.setIdCommande(rs.getLong("id_Commande"));
            annulationClient.setClient(rs.getLong("Client"));
            annulationClient.setProduitIdProduit(rs.getLong("produit_id_produit"));
            annulationClient.setProduitCommercant(rs.getLong("produit_commercant"));
            annulationClient.setDateCommand(rs.getDate("date_Command"));
            annulationClient.setDateLivraison(rs.getDate("date_livraison"));
            annulationClient.setQuantity(rs.getInt("Quantity"));
            annulationClient.setPrixTotal(rs.getDouble("prix_total"));
            annulationClient.setEtat(rs.getString("etat"));
            annulationClient.setDateAnnulation(rs.getDate("date_annulation"));
            return annulationClient;
        });
    }



}
