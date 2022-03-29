/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class MetierClient implements MetierClientLocal {

    @EJB
    private ClientFacadeLocal clientFacade;
    

    @Override
    public void creerClient(String nom, String prenom, String email) {
        this.clientFacade.creerClient(nom,prenom,email);
    }

    @Override
    public Client getClient(Long idClient) {
        return this.clientFacade.find(idClient);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
