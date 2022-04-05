/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierPanier implements MetierPanierLocal {

    @EJB
    private PanierFacadeLocal panierFacade;
    
    

    @Override
    public void payer(long idPanier) {
        
        this.panierFacade.payer(idPanier);
    }

    @Override
    public void livrer(long idPanier) {
        this.panierFacade.livrer(idPanier);
    }

    @Override
    public void ajouterProduit(long idProduit, long idPanier) {
        this.panierFacade.ajouterProduit(idProduit, idPanier);
    }

    @Override
    public void retirerProduit(long idProduit, long idPanier) {
        this.panierFacade.retirerProduit(idProduit, idPanier);
    }

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) {
       this.panierFacade.retirerAllProduit(idProduit, idPanier);
    }

    @Override
    public void supprimerPanier(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        this.panierFacade.remove(p);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
