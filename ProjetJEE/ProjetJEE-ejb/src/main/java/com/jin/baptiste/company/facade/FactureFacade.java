/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Facture;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Baptiste
 */
@Stateless
public class FactureFacade extends AbstractFacade<Facture> implements FactureFacadeLocal {

    @PersistenceContext(unitName = "com.jin.baptiste.company_ProjetJEE-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactureFacade() {
        super(Facture.class);
    }

    @Override
    public List<Facture> findbyEmail(String email) {
        List<Facture> listeFacture = new ArrayList<Facture>();
        List<Facture> allFacture = this.findAll();
        for(Facture f : allFacture){
            if(f.getMail().equals(email)){
                listeFacture.add(f);
            }
        }
        
        return listeFacture;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
