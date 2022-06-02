/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeecltlrd;

import com.jin.baptiste.company.exposition.ExpoLrdRemote;
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
        banque.creerCompte(solde, "abc");
//        Position cpt = banque.getCompte(2L);
//        if(cpt != null){
//            System.out.println(cpt.getDate() + " " + cpt.getIdCompte() + " " + cpt.getSolde());
//        }
        
//        String testMail = "wj19930703@mail.com";
//        Position cltExp = banque.getCompteByMail("wj19930703@mail.com");
//        if(cltExp != null){
//            System.out.println(cltExp.getSolde() + " " + cltExp.getIdCompte() + " " + cltExp.getDate());
//        }
        
    }
}
