/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegPanier implements ExpoLegPanierLocal {

    @EJB
    private MetierProduitLocal metierProduit;

    @EJB
    private MetierPanierLocal metierPanier;

    
    @Override
    public PanierExport getPanier(Long idPanier) {
           return this.metierPanier.getPanier(idPanier);
    }

    @Override
    public void payerPanier(Long idPanier) {
        this.metierPanier.payer(idPanier);
    }


    @Override
    public void ajouterProduit(Long idProduit, Long idPanier) {
       
        /*
         1.autehtification
        --> oui : 1. verifier panier existe 
        
        
        --> non : 
        
        
        */
        this.metierPanier.ajouterProduit(idProduit, idPanier);
    }

    @Override
    public void retirerProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerProduit(idProduit, idPanier);
    }

    @Override
    public void retirerAllProduit(Long idProduit, Long idPanier) {
        this.metierPanier.retirerAllProduit(idProduit, idPanier);
    }

    @Override
    public void supprimerPanier(Long idPanier) {
        this.metierPanier.supprimerPanier(idPanier);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ProduitExport getProduit(Long idProduit) {
        return this.metierProduit.getProduit(idProduit);
    }
}
