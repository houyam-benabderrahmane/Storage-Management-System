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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/Fournisseur")
public class FournisseurController {

	@Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping("/categorie")
    public List<String> getCategories() {
        return fournisseurRepository.getCategories();
    }

    @GetMapping("/isvalid/{idFournisseur}")
    public boolean ifValide(@PathVariable long idFournisseur) {
        return fournisseurRepository.ifValide(idFournisseur);
    }


    @GetMapping("/delete-demande/{commandeId}")
    public String deleteDemandeFournisseur(@PathVariable int commandeId) {
        return fournisseurRepository.deleteDemandeFournisseur(commandeId);
    }

    @PostMapping("/creer-demande/{fournisseurId}/{nomProduit}/{description}/{prix}/{prixGros}/{quantityGros}/{categorie}")
    public ResponseEntity<String> creerDemandeFournisseur(
            @PathVariable int fournisseurId,
            @PathVariable String nomProduit,
            @PathVariable String description,
            @PathVariable double prix,
            @PathVariable double prixGros,
            @PathVariable int quantityGros,
            @PathVariable String categorie) {

        String resultMessage = fournisseurRepository.creerDemandeFournisseur(fournisseurId, nomProduit, description, prix, prixGros, quantityGros, categorie);

        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/valider-commande/{commandeId}/{etatChoisi}")
    public ResponseEntity<String> validerCommandeCommercant(
            @PathVariable int commandeId,
            @PathVariable String etatChoisi) {

        String resultMessage = fournisseurRepository.validerCommandeCommercant(commandeId, etatChoisi);

        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/produits/{idFournisseur}")
    public List<ProduitFourni> getProduitsFournisseur(@PathVariable Long idFournisseur) {
        return fournisseurRepository.getProduitsFournisseur(idFournisseur);
    }


    @GetMapping("/{fournisseurId}/demandes")
    public List<DemandeFournisseur> getDemandesFournisseur(@PathVariable Long fournisseurId) {
        return fournisseurRepository.getDemandesFournisseur(fournisseurId);
    }


    @GetMapping("/commandes/{fournisseurId}")
    public List<CommandeCommercant> getCommandeCommercantByFournisseurId(@PathVariable long fournisseurId) {
        return fournisseurRepository.getCommandeCommercantByFournisseurId(fournisseurId);
    }

    @GetMapping("/commandesValide/{fournisseurId}")
    public List<CommandeCommercant> getCommandeCommercantByFournisseurIdValide(@PathVariable long fournisseurId) {
        return fournisseurRepository.getCommandeCommercantByFournisseurIdValide(fournisseurId);
    }

    @DeleteMapping("/retirerProduit/{produitId}")
    public ResponseEntity<String> retirerProduit(@PathVariable int produitId) {
        String result = fournisseurRepository.retirerProduit(produitId);
        return ResponseEntity.ok(result);
    }


}