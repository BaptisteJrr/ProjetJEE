/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;


import com.jin.baptiste.company.entities.Compte;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierCompteLocal {
    
    public void creerCompte (double solde, String email);
    
    public Compte getComptebyidCompte(long idCompte);
    
    public Compte getComptebyidClient(long idClient);
    
    public void crediter(long idCompte, double somme);
    // throws CompteSoldeNegaException
    public void debiter(long idCompte, double somme);
    
}
