/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierClientLocal {
    
    public void creerClient(String nom, String prenom, String email);
    
    public Client getClient(Long idClient);
    
    
}
