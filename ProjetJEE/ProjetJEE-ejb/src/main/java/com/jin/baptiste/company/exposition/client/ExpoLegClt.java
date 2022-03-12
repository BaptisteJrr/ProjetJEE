/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.client;

import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.utilities.Position;
import entities.Client;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLegClt implements ExpoLegCltLocal {

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private MetierClientLocal metierClient;
    
    @Override
    public void creerCLient(String nom, String prenom, String email) {
        this.metierClient.creerClient(nom, prenom, email);
    }

    @Override
    public Client getClient(Long idClient) {
        return this.metierClient.getClient(idClient);
    }

    @Override
    public void creerCompte(Double solde, Long idClient) {
        this.metierCompte.creerCompte(solde, idClient);
    }

    @Override
    public void crediter(Long idCompte, Double somme) {
        this.metierCompte.crediter(idCompte, somme);
    }

    @Override
    public void debiter(Long idCompte, Double somme) {
        this.metierCompte.debiter(idCompte, somme);
    }

    @Override
    public Position getCompte(long idCompte) {
        return this.metierCompte.getCompte(idCompte);
    }
       
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
