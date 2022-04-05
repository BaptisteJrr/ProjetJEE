/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.CompteFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class MetierCompte implements MetierCompteLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private CompteFacadeLocal compteFacade;
    
  
    @Override
    public void creerCompte(double solde, long idClient) {
        this.compteFacade.creerCompte(solde,idClient);
    }

    @Override
    public void crediter(long idCompte, double somme) {
        this.compteFacade.crediter(idCompte,somme);
    }

    @Override
    public void debiter(long idCompte, double somme) {
        this.compteFacade.debiter(idCompte,somme);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Compte getComptebyidCompte(long idCompte) {
        Compte cpt =  this.compteFacade.find(idCompte);
        return cpt;
    }

    @Override
    public Compte getComptebyidClient(long idClient) {
        Client clt = this.clientFacade.find(idClient);
        Compte cpt = this.compteFacade.find(clt.getCompte());
        return cpt;
    }
}
