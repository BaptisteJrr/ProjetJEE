/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.livreur;

import entities.Panier;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegLvr implements ExpoLegLvrLocal {

    @Override
    public void livrer(ArrayList<Panier> listePanier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Panier> listerPanier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
