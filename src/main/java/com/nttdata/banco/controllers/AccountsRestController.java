package com.nttdata.banco.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.banco.models.entities.Accounts;
import com.nttdata.banco.services.interfaces.iAccountsService;

@RestController
@RequestMapping("/api/accounts")
public class AccountsRestController {

    @Autowired
    private iAccountsService accountsService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestBody Accounts account) {
        Map<String, Object> response = new HashMap<>(null);
        Accounts accountCreated = null;
        try {
            if (accountsService.checkIfEmailExists(account.getEmail())) {
                response.put("mensaje", "El correo ya existe");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            accountCreated = accountsService.save(account);
            response.put("mensaje", "Cuenta creada con éxito");
            response.put("account", accountCreated);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("message", "Error creating account");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
