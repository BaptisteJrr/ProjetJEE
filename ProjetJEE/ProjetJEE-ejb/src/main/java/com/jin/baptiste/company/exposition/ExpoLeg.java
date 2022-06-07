/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Facture;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.metier.MetierFactureLocal;
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
import com.jin.baptiste.company.projetjeeshared.utilities.FactureExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.util.ArrayList;
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
    private MetierFactureLocal metierFacture;

    @EJB
    private MetierPanierLocal metierPanier;

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private MetierClientLocal metierClient;
    
    
    
    
    
    //les autentifications c'est a ce niveau-la a faire pas dans le metier

    @Override
    public void creerClient(String nom, String prenom, String mail, String adresse) throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException {
            this.metierClient.creerClient(nom, prenom, mail, adresse);
       
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
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException {
            this.metierCompte.creerCompte(solde, mail);

    }

    @Override
    public void crediter(Long id, Double somme) throws CompteInconnuException, CompteSommeNegaException {
            this.metierCompte.crediter(id, somme);   
    }

    @Override
    public void debiter(Long id, Double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException{
        this.metierCompte.debiter(id, somme);

    }

    @Override
    public ClientExport getClientByMail(String mail) throws FormatInvalideException, ClientInconnuException {
        Client clt = null;
        clt = this.metierClient.getClientparMail(mail);
        List<Long> listeIdPanier = new ArrayList<Long>();
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
    public Position getCompte(Long idCompte) throws CompteInconnuException {
        Compte cpt = null;
        cpt = this.metierCompte.getComptebyidCompte(idCompte);
        
        Position p = new Position(cpt.getSolde(), new Date(), idCompte);
        return p;
        
    }

    @Override
    public List<FactureExport> getFacture(String email) {
        List<FactureExport> listeFactureExport = new ArrayList<FactureExport>();
        List<Facture> listeFacture = this.metierFacture.getFactureByClient(email);
        for(Facture f : listeFacture){
            listeFactureExport.add(new FactureExport(f.getNom(), f.getPrenom(), f.getMail(), f.getAdresse(), f.getPrixHT(), f.getDate(), f.getNbProduit()));
        }
        
        return listeFactureExport;
    }
}
