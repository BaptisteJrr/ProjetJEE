/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.facades.ClientFacadeLocal;
import entities.Client;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class MetierClient implements MetierClientLocal {

    @EJB
    private ClientFacadeLocal clientFacade;
    
    @Override
    public void creerClient(String nom, String renom, String email) {
        this.clientFacade.creerClient(nom, renom, email);
    }

    @Override
    public Client getClient(Long idClient) {
        return this.clientFacade.find(idClient);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
