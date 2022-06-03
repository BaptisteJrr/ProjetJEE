/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition.leg;

import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface ExpoLegLocal {
    // Action du Client sur le Compte 
    public void creerClient(String nom, String prenom, String mail, String adresse);
//    public ClientExport getClient(Long id);
    public ClientExport getClientByMail(String mail);
    public void creerCompte(Double solde, String mail);
    public void crediter(Long id, Double somme);
    public void debiter(Long id, Double somme);
    public Position getCompte(Long idCompte);
    
    
    
}
