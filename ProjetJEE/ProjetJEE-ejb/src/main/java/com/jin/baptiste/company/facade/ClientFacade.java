/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

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
    public ClientFacade() {
        super(Client.class);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public Client findbyEmail(String email) {
        List<Client> listeClt = findAll();
        for (Client c : listeClt){
            if(c.getEmail().contains(email)){
                return c;
            }
        }
        return null;
    }

    
}
