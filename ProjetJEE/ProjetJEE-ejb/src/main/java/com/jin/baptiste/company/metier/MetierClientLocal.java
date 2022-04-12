/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierClientLocal {
    
    public void creerClient(String nom, String prenom, String email);
    
    public ClientExport getClient(long idClient);
    
    public boolean authentification(String nom, String prenom);
    
    public void ajouterPanier(Panier panier, long idCLient);
}
