/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Facture;
import com.jin.baptiste.company.entities.Panier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Baptiste
 */
@Local
public interface MetierFactureLocal {
    
    /**
     *
     * @param p
     */
    public void CreerFacture(Panier p);

    /**
     *
     * @param mail
     * @return
     */
    public List<Facture> getFactureByClient(String mail);
}
