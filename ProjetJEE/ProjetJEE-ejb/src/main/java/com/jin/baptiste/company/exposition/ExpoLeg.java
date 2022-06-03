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
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wang
 */
@Stateless
public class ExpoLeg implements ExpoLegLocal {

    @EJB
    private MetierPanierLocal metierPanier;

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private MetierClientLocal metierClient;
    
    //les autentifications c'est a ce niveau-la a faire pas dans le metier

    @Override
    public void creerClient(String nom, String prenom, String mail, String adresse) {
        try {
            this.metierClient.creerClient(nom, prenom, mail, adresse);
        } catch (FormatInvalideException ex) {
            System.out.println("Le Format est Invalide.");
        } catch (EmptyFieldException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientAlreadyExistException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public ClientExport getClient(Long id) {
//        Client clt = this.metierClient.getClient(id);
//        List<Long> listeIdPanier = null;
//        for(Panier p : clt.getListePanier()){
//            listeIdPanier.add(p.getId());
//        }
//        ClientExport clte = new ClientExport(clt.getId(),clt.getNom(),clt.getPrenom(),clt.getEmail(),clt.getCompte().getId(),clt.getAdresse(),listeIdPanier);
//        return clte;
//    }

    @Override
    public void creerCompte(Double solde, String mail) {
        try {
            this.metierCompte.creerCompte(solde, mail);
        } catch (EmptyFieldException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FormatInvalideException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientInconnuException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientCompteAlreadyLinkedException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crediter(Long id, Double somme) {
        try {
            this.metierCompte.crediter(id, somme);
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSommeNegaException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void debiter(Long id, Double somme){
        try {
            this.metierCompte.debiter(id, somme);
        } catch (CompteSoldeNegaException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteInconnuException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompteSommeNegaException ex) {
            Logger.getLogger(ExpoLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ClientExport getClientByMail(String mail) {
        Client clt = null;
        try {
            clt = this.metierClient.getClientparMail(mail);
        } catch (FormatInvalideException ex) {
            System.out.println("Le Format est Invalide.");
        } catch (ClientInconnuException ex) {
            System.out.println("Client Inconnu.");;
        }
        List<Long> listeIdPanier = null;
        for(Panier p : clt.getListePanier()){
            listeIdPanier.add(p.getId());
        }
        Long idCompte = null;
        try{
            idCompte = clt.getCompte().getId();
        }catch(Exception e){
            
        }
        ClientExport clte = new ClientExport(clt.getId(),clt.getNom(),clt.getPrenom(),clt.getEmail(),idCompte,clt.getAdresse(),listeIdPanier);
        return clte;
    }    

    @Override
    public Position getCompte(Long idCompte) {
        Compte cpt = null;
        try {
            cpt = this.metierCompte.getComptebyidCompte(idCompte);
        } catch (CompteInconnuException ex) {
            System.out.println("Compte Inconnu.");
        }
        
        Position p = new Position(cpt.getSolde(), new Date(), idCompte);
        return p;
        
    }
}
