package com.nttdata.banco.services.interfaces;

// import java.util.List;
import java.util.UUID;

import com.nttdata.banco.models.entities.Phones;

public interface iPhonesService {
    public Phones findOne(UUID id);

    // public List<Phones> findAllByAccountId(UUID id);

    public Phones save(Phones phone);
}
