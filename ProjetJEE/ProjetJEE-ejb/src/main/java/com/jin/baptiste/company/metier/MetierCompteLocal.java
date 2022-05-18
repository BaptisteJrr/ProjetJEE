/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.projetjeeshared.utilities.CompteExport;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierCompteLocal {
    
    public void creerCompte (double solde, String email);
    
    public CompteExport getComptebyidCompte(long idCompte);
    
    public CompteExport getComptebyidClient(long idClient);
    
    public void crediter(long idCompte, double somme);
    
    public void debiter(long idCompte, double somme);
    
}
