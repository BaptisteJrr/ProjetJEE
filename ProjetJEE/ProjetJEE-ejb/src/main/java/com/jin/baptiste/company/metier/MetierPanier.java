/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierPanier implements MetierPanierLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PanierFacadeLocal panierFacade;
    
    
    
    

    @Override
    public void payer(long idPanier) {
        
        Panier p = this.panierFacade.find(idPanier);
        p.setFlagRegle(true);
        this.panierFacade.edit(p);
    }

    @Override
    public void livrer(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        p.setFlagLivre(true);
        this.panierFacade.edit(p);
    }

    @Override
    public void ajouterProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.add(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }

    @Override
    public void retirerProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.remove(pro);
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
    }

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        while(liste.contains(pro)){
            liste.remove(pro);
        }
        p.setListeProduit(liste);
        this.panierFacade.edit(p);
        
    }

    @Override
    public void supprimerPanier(long idPanier) {
        Panier p = this.panierFacade.find(idPanier);
        this.panierFacade.remove(p);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
