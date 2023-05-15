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

import com.nttdata.banco.models.dao.AccountResponseDAO;
import com.nttdata.banco.models.entities.Accounts;
import com.nttdata.banco.models.entities.Phones;
import com.nttdata.banco.services.interfaces.iAccountsService;
import com.nttdata.banco.validators.EmailValidator;
import com.nttdata.banco.validators.PasswordValidator;

@RestController
@RequestMapping("/api/accounts")
public class AccountsRestController {

    @Autowired
    private iAccountsService accountsService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestBody Accounts account) {
        Map<String, Object> response = new HashMap<>();
        Accounts accountCreated = null;
        AccountResponseDAO accountResponse = new AccountResponseDAO();
        try {

            if (account.getName() == null || account.getName().isEmpty() || 
                account.getEmail() == null || account.getEmail().isEmpty() || 
                account.getPassword() == null || account.getPassword().isEmpty()) {                
                response.put("mensaje", "Los campos son obligatorios");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            if (!EmailValidator.isValidEmail(account.getEmail())) {
                response.put("mensaje", "El correo no es válido");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            if (!PasswordValidator.isValidPassword(account.getPassword())) {
                response.put("mensaje", 
                    "La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una Mayuscula, letras minúsculas, y dos numeros)");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            if (accountsService.checkIfEmailExists(account.getEmail())) {
                response.put("mensaje", "El correo ya existe");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

            if (account.getPhones() != null) {
                for (Phones phone : account.getPhones()) {
                    phone.setAccount(account);
                }
            }

            String encryptedPassword = accountsService.encryptPassword(account.getPassword());
            account.setPassword(encryptedPassword);
            accountCreated = accountsService.save(account);
            accountResponse.setId(accountCreated.getId());
            accountResponse.setCreatedAt(accountCreated.getCreatedAt());
            accountResponse.setUpdatedAt(accountCreated.getUpdatedAt());
            accountResponse.setLastLogin(accountCreated.getLastLogin());
            accountResponse.setToken(accountCreated.getToken());
            accountResponse.setIsActive(accountCreated.isActive());

            response.put("mensaje", "Cuenta creada con éxito");
            response.put("account", accountResponse);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("message", "Error creating account");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
