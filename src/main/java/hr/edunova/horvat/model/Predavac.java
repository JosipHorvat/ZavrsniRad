/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.edunova.horvat.model;

import javax.persistence.Entity;

/**
 *
 * @author Josip
 */
@Entity // ne zaboravi ga dodati u hibernate cfg.
public class Predavac extends Osoba{
    
    private String iban;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    
}
