/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import entities.Client;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface MetierClientLocal {
    
    public void creerClient(String nom, String renom, String email);
    
    public Client getClient (Long idClient);
    
}
