/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.miage.company.projetjeesharedcompte.interfremote;

import com.jin.baptiste.miage.company.projetjeesharedcompte.utilities.ClientExport;
import com.jin.baptiste.miage.company.projetjeesharedcompte.utilities.DecouverInterditException;
import com.jin.baptiste.miage.company.projetjeesharedcompte.utilities.Position;
import javax.ejb.Remote;

/**
 *
 * @author Wang
 */
@Remote
public interface ExpoLrdCompteRemote {
    
    public void creerClient(String nom, String prenom, String email);
    
    public ClientExport getClient(Long idClient);
    
    public void creerCompte(Double solde, Long idClient);
    
    public void crediter(Long idCompte, Double somme);
    
    public void debiter(Long idCompte, Double somme) throws DecouverInterditException;
    
    public Position getCompte(long idCompte);
    
}
