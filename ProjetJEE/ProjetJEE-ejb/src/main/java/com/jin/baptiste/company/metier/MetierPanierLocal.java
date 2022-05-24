/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Panier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierPanierLocal {
    
        public void payer(long idPanier);
        
        public void livrer(long idPanier);
        
        public void ajouterProduit(long idProduit,long idPanier);
    
        public void retirerProduit(long idProduit, long idPanier);
    
        public void retirerAllProduit(long idProduit, long idPanier);
        
        public void supprimerPanier(long idPanier);
        
        public Panier getPanier(long idPanier);
        
        public List getPanier();    
        public List getPanierNonPaye();       
        public List getPanierPaye();
        public List getPanierNonLivre();
        public List getPanierLivre();
        
        

}
