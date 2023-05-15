package com.nttdata.banco.services.implementations;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.banco.models.dao.iPhonesDAO;
import com.nttdata.banco.models.entities.Phones;
import com.nttdata.banco.services.interfaces.iPhonesService;

public class PhonesService implements iPhonesService{

    @Autowired
    private iPhonesDAO phonesDAO;

    @Override
    public Phones findOne(UUID id) {
        return phonesDAO.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Phones save(Phones phone) {
        return phonesDAO.save(phone);
    }
    
}
