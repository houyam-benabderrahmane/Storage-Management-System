package com.tabd.app.controller;


import com.tabd.app.entity.*;
import com.tabd.app.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/validerFournisseur/{fournisseurId}")
    public ResponseEntity<String> validerFournisseur(@PathVariable int fournisseurId) {
        String result = adminRepository.validerFournisseur(fournisseurId);

        if (result.startsWith("Invalid")) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/delete-fournisseur/{fournisseurId}")
    public String deleteFournisseur(@PathVariable long fournisseurId) {
        return adminRepository.deleteFournisseur(fournisseurId);
    }

    @GetMapping("/FournisseurNonValide")
    public List<Fournisseur> getAllFournisseursNonValid() {
        return adminRepository.getAllFournisseursNonValid();
    }

    @PostMapping("/validerDemandeFournisseur/{demandeId}/{etatChoisi}")
    public String validerDemandeFournisseur(
            @PathVariable int demandeId,
            @PathVariable String etatChoisi) {
        return adminRepository.validerDemandeFournisseur(demandeId, etatChoisi);
    }

    @GetMapping("/demandesFournisseur")
    public ResponseEntity<List<DemandeFournisseur>> getDemandesFournisseur() {
        List<DemandeFournisseur> demandesFournisseur = adminRepository.getDemandesFournisseur();
        return ResponseEntity.ok(demandesFournisseur);
    }

    @PostMapping("/ajouterCategorie/{newCategory}")
    public ResponseEntity<String> ajouterCategorie(@PathVariable String newCategory) {
        String result = adminRepository.ajouterCategorie(newCategory);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/produitsFournis")
    public List<ProduitFourni> getProduitFourniList() {
        return adminRepository.getProduitFourni();
    }

    @DeleteMapping("/retirerProduit/{produitId}")
    public ResponseEntity<String> retirerProduit(@PathVariable int produitId) {
        String result = adminRepository.retirerProduit(produitId);
        return ResponseEntity.ok(result);
    }
    
}