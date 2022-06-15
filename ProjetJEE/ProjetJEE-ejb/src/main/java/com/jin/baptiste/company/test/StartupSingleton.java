/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.test;

import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 *
 * @author Baptiste
 */
@Startup
@Singleton
public class StartupSingleton implements StartupSingletonLocal {

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private MetierClientLocal metierClient;
    
    
    
    
    @PostConstruct
    void init() {
        
        try {
            this.metierClient.creerClient("Compte", "Atem", "compteAtem@atem.fr", "Paul Sabatier");
        } catch (FormatInvalideException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyFieldException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientAlreadyExistException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientInconnuException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientCompteAlreadyLinkedException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.metierCompte.creerCompte(0, "compteAtem@atem.fr");
        } catch (EmptyFieldException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FormatInvalideException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientInconnuException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientCompteAlreadyLinkedException ex) {
            Logger.getLogger(StartupSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
