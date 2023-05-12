package com.nttdata.banco.models.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nttdata.banco.models.entities.Accounts;

public interface iAccountsDAO extends JpaRepository<Accounts, UUID> {
    @Query("SELECT a FROM Accounts a WHERE a.email = ?1")
    Accounts findByEmail(String email);

    
}
