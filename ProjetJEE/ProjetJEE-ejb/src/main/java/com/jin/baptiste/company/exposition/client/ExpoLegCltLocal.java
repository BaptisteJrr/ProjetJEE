/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.client;

import com.jin.baptiste.company.utilities.DecouverInterditException;
import com.jin.baptiste.company.utilities.Position;
import entities.Client;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegCltLocal {
    
    public void creerCLient(String nom, String prenom, String email);
    
    public Client getClient(Long idClient);
    
    public void creerCompte(Double solde, Long idClient);
    
    public void crediter(Long idCompte, Double somme);
    
    public void debiter(Long idCompte, Double somme) throws DecouverInterditException;
    
    public Position getCompte(long idCompte);
    
}
