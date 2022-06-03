/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.miage.company.projetjeecomptable;

import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import javax.naming.NamingException;

/**
 *
 * @author Wang
 */
public class Main {
    public static void main(String[] args) throws NamingException, ClientInconnuException{
        try{
            RMICMPTServiceManager rmiMgr = new RMICMPTServiceManager();
            CMPTACLI cli = new CMPTACLI(rmiMgr.getcomptaRemoteSvc());
            cli.run();
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
