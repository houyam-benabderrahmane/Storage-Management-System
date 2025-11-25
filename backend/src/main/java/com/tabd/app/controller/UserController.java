package com.tabd.app.controller;


import com.tabd.app.entity.*;
import com.tabd.app.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/")
public class UserController {
	

    @Autowired
    private UserRepository userRepository;
	@Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommercantRepository commercantRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    @PostMapping("/authenticate/{email}/{password}")
    public ResponseEntity<Map<String, Object>> authenticateUser(
            @PathVariable String email,
            @PathVariable String password) {

    	/*	
        String email = credentials.get("email");
        String password = credentials.get("password");
        */

        // Try to authenticate as Admin
        Admin admin = adminRepository.authenticate(email, password);
        if (admin != null) {
            return ResponseEntity.ok(Map.of("role", admin.getRole(), "id", admin.getIdAdmin()));
        }

        // Try to authenticate as Client
        Client client = clientRepository.authenticate(email, password);
        if (client != null) {
            return ResponseEntity.ok(Map.of("role", client.getRole(), "id", client.getIdClient()));
        }

        // Try to authenticate as Commercant
        Commercant commercant = commercantRepository.authenticate(email, password);
        if (commercant != null) {
            return ResponseEntity.ok(Map.of("role", commercant.getRole(), "id", commercant.getIdCommercant()));
        }

        // Try to authenticate as Fournisseur
        Fournisseur fournisseur = fournisseurRepository.authenticate(email, password);
        if (fournisseur != null) {
            return ResponseEntity.ok(Map.of("role", fournisseur.getRole(), "id", fournisseur.getIdFournisseur()));
        }

        // User not found or wrong password
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Wrong email or password"));
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User request) {
        String result = userRepository.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getLastName(),
                request.getRole(),
                request.getAdresse(),
                request.getnReg()
        );

        return ResponseEntity.ok(result);
    }
}