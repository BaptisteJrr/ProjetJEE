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
        try{
            RMIDABServiceManager rmiMgr = new RMIDABServiceManager();
            DABCLI cli = new DABCLI(rmiMgr.getdabRemoteSvc());
            cli.run();
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
        
    }
}
