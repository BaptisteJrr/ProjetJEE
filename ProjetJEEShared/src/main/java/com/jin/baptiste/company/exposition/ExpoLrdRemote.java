/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import javax.ejb.Remote;

/**
 *
 * @author LeNonGrillePain
 */
@Remote
public interface ExpoLrdRemote {
    
    public void creerCompte(Double solde, String mail);
    public void crediter(Long id, Double somme);
    public void debiter(Long id, Double somme);
    public Position getCompte(Long idCompte);
    //Get Client??
    // by mail ou by idClient??
    public Position getCompteByMail(String mail);
    
}
