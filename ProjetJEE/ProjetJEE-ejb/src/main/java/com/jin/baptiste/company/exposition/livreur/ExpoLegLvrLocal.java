/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.livreur;

import entities.Panier;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegLvrLocal {
    public void livrer(ArrayList<Panier> listePanier );
    public ArrayList<Panier> listerPanier();
    
    
    
}
