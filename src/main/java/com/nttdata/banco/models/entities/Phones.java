package com.nttdata.banco.models.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phones implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;
    
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    @Column(name = "area_code", nullable = false)
    private int areaCode;

    @Column(name = "country_code", nullable = false)
    private int countryCode;

    @ManyToOne
    private Accounts account;

    public Phones() {
    }

    public Phones(UUID id, String number, int areaCode, int countryCode) {
        this.id = id;
        this.number = number;
        this.areaCode = areaCode;
        this.countryCode = countryCode;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Phones id(UUID id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public Accounts getAccount() {
        return this.account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    private static final long serialVersionUID = 1L;

}
