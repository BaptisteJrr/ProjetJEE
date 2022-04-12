/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientNonTrouveException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
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
    public void creerClient(String nom, String prenom, String email) {
        Client c = new Client();
        c.setNom(nom);
        c.setEmail(email);
        c.setPrenom(prenom);
        this.clientFacade.create(c);
    }

    @Override
    public ClientExport getClient(long idClient) {
        Client clt = this.clientFacade.find(idClient);
        List<Long> listeIdPanier = null;
        for(Panier p : clt.getListePanier()){
            listeIdPanier.add(p.getId());
        }
        ClientExport clte = new ClientExport(clt.getId(),clt.getNom(),clt.getPrenom(),clt.getEmail(),clt.getCompte().getId(),listeIdPanier);
        return clte;
    }  

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean authentification(String nom, String prenom) {
        List<Client> findAllClient = this.clientFacade.findAll();
        boolean auth = false;
        for(Client c : findAllClient){       
            if (c.getNom().equals(nom) && c.getPrenom().equals(prenom))
                auth = true;
        }
        return auth;
    }

    @Override
    public void ajouterPanier(Panier panier, long idClient) {
        Client clt = this.clientFacade.find(idClient);
        clt.getListePanier().add(panier);
    }
}
