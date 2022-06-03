/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class ExpoLrd implements ExpoLrdRemote {

    @EJB
    private MetierClientLocal metierClient;

    @EJB
    private MetierCompteLocal metierCompte;
    
    
    @Override
    public void creerCompte(Double solde, String mail) {
        this.metierCompte.creerCompte(solde, mail);
    }

    @Override
    public void crediter(Long id, Double somme) {
        this.metierCompte.crediter(id, somme);
    }

    @Override
    public void debiter(Long id, Double somme){
        try {
            this.metierCompte.debiter(id, somme);
        } catch (CompteSoldeNegaException ex) {
            System.out.println("Le solde n'est pas suffisant.");;
        }
    }

    @Override
    public Position getCompte(Long idCompte) {
        Compte cpt = null;
        try {
            cpt = this.metierCompte.getComptebyidCompte(idCompte);
        } catch (CompteInconnuException ex) {
            System.out.println("Compte Inconnu.");;
        }
        if(cpt == null){
            return null;
        }
        Position p = new Position(cpt.getSolde(), new Date(), idCompte);
        return p;
        
    }

    @Override
    public Position getCompteByMail(String mail) {
        Compte cpt = this.metierCompte.getComptebyMail(mail);
        if(cpt == null){
            return null;
        }
        Position p = new Position(cpt.getSolde(), new Date(), cpt.getId());
        return p;
    }
}
