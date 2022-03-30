/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierProduitLocal {
    
    public void modifierProduit(long idProduit, String nom, String description, double prixHT, TypeProduitEnum type);
    
        public void vendreProduit(long idProduit, int quantite);
    
    public void stockerProduit(long idProduit, int quantite);
    
}
