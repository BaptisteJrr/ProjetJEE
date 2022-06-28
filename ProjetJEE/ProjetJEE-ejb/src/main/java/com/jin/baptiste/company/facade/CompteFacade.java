/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Compte;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> implements CompteFacadeLocal {

    @EJB
    private ClientFacadeLocal clientFacade;
    
    
    @PersistenceContext(unitName = "com.jin.baptiste.company_ProjetJEE-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public CompteFacade() {
        super(Compte.class);
    }
}
