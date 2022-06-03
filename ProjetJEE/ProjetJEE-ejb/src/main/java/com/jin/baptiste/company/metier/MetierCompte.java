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
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import java.util.List;
import java.util.regex.Pattern;
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
    public void creerCompte(double solde, String email){
        //Verification email existe dans la BD  

        List<Client> findAllClient = this.clientFacade.findAll();
        boolean auth = false;
        boolean compteFlg = false;
        for(Client c : findAllClient){       
            if (c.getEmail().equals(email)){
                auth = true;
                // Client a pas de Compte
                if (c.getCompte()!= null) {
                    compteFlg = true;
                } 
            }
        }
        if (auth && !compteFlg) {
             Compte cpt = new Compte();      
             cpt.setSolde(solde);
             Client clt = this.clientFacade.findbyEmail(email);
             cpt.setClient(clt);
             clt.setCompte(cpt);
             this.compteFacade.create(cpt); 
        } else {
            if (!auth)
                System.out.println("Client non existe");
            if (compteFlg)
                System.out.println("Client a déjà un compte");
        }
    }

    @Override
    public void crediter(long idCompte, double somme) {
        Compte cpt = this.compteFacade.find(idCompte);
        if(somme > 0){
            cpt.setSolde(cpt.getSolde() + somme);
            this.compteFacade.edit(cpt);
        }   
        else{
            new CompteSommeNegaException();
        }
    }

    @Override
    public void debiter(long idCompte, double somme) {
        Compte cpt = this.compteFacade.find(idCompte);
        if(somme > 0){
            cpt.setSolde(cpt.getSolde() - somme);
            this.compteFacade.edit(cpt);
        }
        else
            new CompteSommeNegaException();
        
        if(cpt.getSolde() < 0 )
            new CompteSoldeNegaException();
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

    @Override
    public Compte getComptebyMail(String mail) throws FormatInvalideException {
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(mail).matches())throw new FormatInvalideException();
        Client clt = this.clientFacade.findbyEmail(mail);
        if(clt != null){
            return clt.getCompte();
        }
        return null;
        
    }
}
