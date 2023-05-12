package com.nttdata.banco.services.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.nttdata.banco.models.entities.Accounts;

@Repository
public interface iAccountsService {

    public Accounts findOne(UUID id);
    
    public Accounts findByEmail(String email);

    public boolean checkIfEmailExists(String email);

    public Accounts save(Accounts account);
    
}
