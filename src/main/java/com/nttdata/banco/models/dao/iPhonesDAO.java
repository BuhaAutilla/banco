package com.nttdata.banco.models.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nttdata.banco.models.entities.Phones;

public interface iPhonesDAO extends JpaRepository<Phones, UUID>{
    @Query("SELECT p FROM Phones p WHERE p.number = ?1")
    Phones findByNumber(String number);    
}
