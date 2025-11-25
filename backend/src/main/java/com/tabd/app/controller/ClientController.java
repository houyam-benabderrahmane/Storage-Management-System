package com.tabd.app.controller;


import com.tabd.app.entity.*;
import com.tabd.app.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/Client")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;


    @GetMapping("/Clients")
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @GetMapping("/produits/{sortOption}")
    public List<ProduitCommercant> getSortedProduits(@PathVariable String sortOption) {
        return clientRepository.getProduitCommercantSorted(sortOption);
    }

    @PostMapping("/placeOrder/{clientID}/{productID}/{merchantID}/{quantity}")
    public String placeOrder(
            @PathVariable int clientID,
            @PathVariable int productID,
            @PathVariable int merchantID,
            @PathVariable int quantity) {
        return clientRepository.placeOrder(clientID, productID, merchantID, quantity);
    }

    @PostMapping("/annuleCommande")
    public String annuleCommande(@RequestParam int commandID) {
        // Call the stored procedure using the repository method
        return clientRepository.annuleClient(commandID);
    }

    @GetMapping("/topSellingProducts")
    public List<ProduitCommercant> getTopSellingProducts() {
        return clientRepository.callProduitPlusVendue();
    }

    @GetMapping("/calculateTotalAmount/{clientID}")
    public List<Map<String, Object>> calculateTotalAmountForClient(@PathVariable int clientID) {
        return clientRepository.calculateTotalAmountForClient(clientID);
    }    

    @GetMapping("/commande/{clientId}")
    public List<CommandeClient> getCommandeClientByClientId(@PathVariable Long clientId) {
        return clientRepository.getCommandeClientByClientId(clientId);
    }
    

    @GetMapping("/getAnnulations/{clientId}")
    public List<AnnulationClient> getAnnulations(@PathVariable Long clientId) {
        return clientRepository.getAnnulations(clientId);
    }
}