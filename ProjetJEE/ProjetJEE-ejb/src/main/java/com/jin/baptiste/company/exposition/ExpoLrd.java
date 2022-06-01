/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class ExpoLrd implements ExpoLrdRemote {

    @EJB
    private MetierCompteLocal metierCompte;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
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

    @Override
    public Position getCompte(Long idCompte) {
        Compte cpt = this.metierCompte.getComptebyidCompte(idCompte);
        if(cpt == null){
            return null;
        }
        Position p = new Position(cpt.getSolde(), new Date(), idCompte);
        return p;
        
    }
}
