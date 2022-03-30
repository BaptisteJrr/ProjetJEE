/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Panier;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class PanierFacade extends AbstractFacade<Panier> implements PanierFacadeLocal {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retirerProduit(long idProduit, long idPanier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
<<<<<<< HEAD
=======

    @Override
    public void retirerAllProduit(long idProduit, long idPanier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void payer(long idPanier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void livrer(long idPanier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> f9c3dbc0d84cf31e1d535ec9ed4ea214e6ca2b2e
    
}
