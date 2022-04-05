/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.entities.Produit;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class PanierFacade extends AbstractFacade<Panier> implements PanierFacadeLocal {

    @EJB
    private ProduitFacadeLocal produitFacade;

    @PersistenceContext(unitName = "com.jin.baptiste.company_ProjetJEE-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PanierFacade() {
        super(Panier.class);
    }

    @Override
    public void ajouterProduit(long idProduit, long idPanier) {
        Panier p = this.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.add(pro);
        p.setListeProduit(liste);
        this.edit(p);
        
    }

    @Override
    public void retirerProduit(long idProduit, long idPanier) {
        Panier p = this.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        liste.remove(pro);
        p.setListeProduit(liste);
        this.edit(p);
    }

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) {
        Panier p = this.find(idPanier);
        Produit pro = this.produitFacade.find(idProduit);
        Collection<Produit> liste = p.getListeProduit();
        while(liste.contains(pro)){
            liste.remove(pro);
        }
        p.setListeProduit(liste);
        this.edit(p);
        
    }

    @Override
    public void payer(long idPanier) {
        Panier p = this.find(idPanier);
        p.setFlagRegle(true);
        this.edit(p);
    }

    @Override
    public void livrer(long idPanier) {
        Panier p = this.find(idPanier);
        p.setFlagLivre(true);
        this.edit(p);
    }
}
