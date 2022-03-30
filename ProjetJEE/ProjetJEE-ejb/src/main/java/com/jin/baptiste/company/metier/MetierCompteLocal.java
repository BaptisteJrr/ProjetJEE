/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierCompteLocal {
    
    public void creerCompte (double solde, long idClient);
    
    public void getComptebyidCompte(long idCompte);
    
    public void getComptebyidClient(long idClient);
    
    public void crediter(long idCompte, double somme);
    
    public void debiter(long idCompte, double somme);
    
}
