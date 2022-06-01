/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import com.jin.baptiste.company.exposition.ExpoLrdRemote;
import com.jin.baptiste.company.projetjeeshared.utilities.CompteExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author LeNonGrillePain
 */
public class Main {
    public static void main(String[] args) throws NamingException{
        // 1 : lookup objet
        Context ctx = new InitialContext();
        ExpoLrdRemote banque = (ExpoLrdRemote) ctx.lookup("com.jin.baptiste.company.exposition.ExpoLrdRemote");
        
        
        // 2 : metier 
        Double solde = 1000.0;
        //banque.creerCompte(solde, "baptiste.jarry@test.fr");
        Position cpt = banque.getCompte(51L);
        if(cpt != null){
            System.out.println(cpt.getDate() + " " + cpt.getIdCompte() + " " + cpt.getSolde());
        }
        
    }
}
