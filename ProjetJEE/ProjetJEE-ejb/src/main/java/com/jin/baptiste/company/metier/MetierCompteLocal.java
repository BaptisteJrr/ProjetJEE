/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.utilities.Position;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierCompteLocal {
    
    public void creerCompte (double solde, long idClient);
    
    public Position getCompte (long idCompte);
    
    public void crediter(long idCompte, double somme);
    
    public void debiter(long idCompte, double somme);
    
}
