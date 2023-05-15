package com.nttdata.banco.services.implementations;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.banco.models.dao.iAccountsDAO;
import com.nttdata.banco.services.interfaces.iAccountsService;
import com.nttdata.banco.models.entities.Accounts;

@Service
public class AccountsService implements iAccountsService {
    
    @Autowired
    private iAccountsDAO accountsDAO;

    @Override
    public Accounts findOne(UUID id) {
        return accountsDAO.findById(id).orElse(null);
    }

    @Override
    public Accounts findByEmail(String email) {
        return accountsDAO.findByEmail(email);
    }   

    @Override
    public boolean checkIfEmailExists(String email) {
        return accountsDAO.findByEmail(email) != null;
    }

    @Override
    @Transactional
    public Accounts save(Accounts account) {
        return accountsDAO.save(account);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    
}
