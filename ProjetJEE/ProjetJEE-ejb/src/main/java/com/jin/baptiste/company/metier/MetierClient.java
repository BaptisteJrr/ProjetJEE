/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import java.util.List;
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
    public void creerClient(String nom, String prenom, String email, String adresse) throws FormatInvalideException {
        //Verifier le formal du Mail @
        if(!email.contains("@")) throw new FormatInvalideException();
        //Verification du Mail soit unique
        if (this.clientFacade.findbyEmail(email) == null){
            Client c = new Client();
            c.setNom(nom);
            c.setEmail(email);
            c.setPrenom(prenom);
            c.setAdresse(adresse);
            this.clientFacade.create(c);
        }
    }

    @Override
    public Client getClient(long idClient) {
        Client clt = this.clientFacade.find(idClient);
        return clt;
    }  


    @Override
    public boolean authentification(String email) {
        List<Client> findAllClient = this.clientFacade.findAll();
        boolean auth = false;
        for(Client c : findAllClient){       
            if (c.getEmail().equals(email))
                auth = true;
        }
        return auth;
    }

    @Override
    public void ajouterPanier(Panier panier, long idClient) {
        Client clt = this.clientFacade.find(idClient);
        clt.getListePanier().add(panier);
    }
    
    //Error a traiter
    @Override
    public Client getClientparMail(String email) {
        Client clt = this.clientFacade.findbyEmail(email);
        return clt;        
    }
}
