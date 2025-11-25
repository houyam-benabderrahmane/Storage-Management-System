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
import java.util.Date;


@RestController
@RequestMapping("/commercant")
public class CommercantController {

    @Autowired
    private CommercantRepository commercantRepository;

    @GetMapping("/commandesClientValide/{idCommercant}")
    public List<CommandeClient> getCommandesClientValide(@PathVariable Long idCommercant) {
        return commercantRepository.getCommandesClientValide(idCommercant);
    }

    @GetMapping("/commandesClient/{idCommercant}")
    public List<CommandeClient> getCommandesClientEnAttente(@PathVariable Long idCommercant) {
        return commercantRepository.getCommandesClientEnAttente(idCommercant);
    }

    @PostMapping("/validerCommandeClient/{commandeId}")
    public ResponseEntity<String> validerCommandeClient(
            @PathVariable Long commandeId,
            @RequestParam String etatChoisi,
            @RequestParam String livraisonDate) {
        String result = commercantRepository.validerCommandeClient(commandeId, etatChoisi, livraisonDate);
        return ResponseEntity.ok(result);
    }

	@GetMapping("/produitFourni")
    public ResponseEntity<List<ProduitFourni>> getAllProduitFourni() {
        List<ProduitFourni> produitFourniList = commercantRepository.getAllProduitFourni();
        return new ResponseEntity<>(produitFourniList, HttpStatus.OK);
    }

    @PostMapping("/placer-commande")
    public ResponseEntity<String> placerCommandeCommercant(
            @RequestParam("merchantID") int merchantID,
            @RequestParam("productID") int productID,
            @RequestParam("quantity") int quantity) {
        String result = commercantRepository.placerCommandeCommercant(merchantID, productID, quantity);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/commandesCommercant/{idCommercant}")
    public List<CommandeCommercant> getCommandesCommercant(@PathVariable int idCommercant) {
        return commercantRepository.getCommandesCommercant(idCommercant);
    }

    @GetMapping("/annulations/{commercantId}")
    public List<AnnulationCommercant> getAnnulationCommercantByCommercantId(@PathVariable long commercantId) {
        return commercantRepository.getAnnulationCommercantByCommercantId(commercantId);
    }
    

    @PostMapping("/annule-commande/{commandeId}")
    public String annuleCommandeCommercant(@PathVariable int commandeId) {
        return commercantRepository.annuleCommandeCommercant(commandeId);
    }


     @GetMapping("/produits/{commercantId}")
    public List<ProduitCommercant> getProduitCommercantByCommercantId(@PathVariable long commercantId) {
        return commercantRepository.getProduitCommercantByCommercantId(commercantId);
    }




}