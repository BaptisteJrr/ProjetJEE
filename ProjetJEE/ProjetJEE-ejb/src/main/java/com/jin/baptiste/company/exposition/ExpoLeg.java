/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.metier.MetierPanierLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
        this.metierClient.creerClient(nom, prenom, mail, adresse);
    }

    @Override
    public ClientExport getClient(Long id) {
        Client clt = this.metierClient.getClient(id);
        List<Long> listeIdPanier = null;
        for(Panier p : clt.getListePanier()){
            listeIdPanier.add(p.getId());
        }
        ClientExport clte = new ClientExport(clt.getId(),clt.getNom(),clt.getPrenom(),clt.getEmail(),clt.getCompte().getId(),clt.getAdresse(),listeIdPanier);
        return clte;
    }

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
        this.metierCompte.debiter(id, somme);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
