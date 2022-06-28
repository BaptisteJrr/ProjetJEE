/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.util.Date;
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
    
    /**
     *
     * @param solde
     * @param mail
     */
    @Override
    public void creerCompte(Double solde, String mail) {
        try {
            this.metierCompte.creerCompte(solde, mail);
        } catch (EmptyFieldException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FormatInvalideException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientInconnuException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientCompteAlreadyLinkedException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @param somme
     */
    @Override
    public void crediter(Long id, Double somme) {
        try {
            this.metierCompte.crediter(id, somme);
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSommeNegaException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @param somme
     */
    @Override
    public void debiter(Long id, Double somme){
        try {
            this.metierCompte.debiter(id, somme);
        } catch (CompteSoldeNegaException ex) {
            System.out.println("Le solde n'est pas suffisant.");;
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSommeNegaException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idCompte
     * @return
     */
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

    /**
     *
     * @param mail
     * @return
     */
    @Override
    public Position getCompteByMail(String mail) {
        Compte cpt = null;
        try {
            cpt = this.metierCompte.getComptebyMail(mail);
        } catch (FormatInvalideException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cpt == null){
            return null;
        }
        Position p = new Position(cpt.getSolde(), new Date(), cpt.getId());
        return p;
    }
}
