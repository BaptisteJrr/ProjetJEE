/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Produit;
import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class ProduitFacade extends AbstractFacade<Produit> implements ProduitFacadeLocal {

    @PersistenceContext(unitName = "com.jin.baptiste.company_ProjetJEE-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    public void creerProduit(String nom, TypeProduitEnum type, double prixHT, String description, int stock) {
        Produit p = new Produit();
        p.setDescription(description);
        p.setNom(nom);
        p.setPrixHT(prixHT);
        p.setType(type);
        p.setStock(stock);
        this.create(p);
    }

    @Override
    public void vendreProduit(long idProduit, int quantite) {
        Produit p = this.find(idProduit);
        if(p.getStock() - quantite < 0){
            try {
                throw new ProduitStockInsuffisantException();
            } catch (ProduitStockInsuffisantException ex) {
                Logger.getLogger(ProduitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            p.setStock(p.getStock() - quantite);
        }
    }

    @Override
    public void stockerProduit(long idProduit, int quantite) {
        Produit p = this.find(idProduit);
        if(quantite < 0){
            try {
                throw new ProduitQuantiteNegativeException();
            } catch (ProduitQuantiteNegativeException ex) {
                Logger.getLogger(ProduitFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            p.setStock(p.getStock() + quantite);
        }
    }
    
}
