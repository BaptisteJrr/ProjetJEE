/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;


import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierCompteLocal {
    
    /**
     * creation d'un compte appele dans la methode creerClient
     * @param solde
     * @param email
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    public void creerCompte (double solde, String email) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException ;
    // au choix entre les deux

    /**
     * Get Compte par idCompte
     * @param idCompte
     * @return
     * @throws CompteInconnuException
     */
    public Compte getComptebyidCompte(long idCompte) throws CompteInconnuException;
    
//    public Compte getComptebyidClient(long idClient) throws ClientInconnuException, CompteInconnuException;

    /**
     * crediter compte
     * @param idCompte
     * @param somme
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     */
    
    public void crediter(long idCompte, double somme) throws CompteInconnuException, CompteSommeNegaException;
    // throws CompteSoldeNegaException

    /**
     * debiter compte
     * @param idCompte
     * @param somme
     * @throws CompteSoldeNegaException
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     */
    public void debiter(long idCompte, double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException;
    
    //Pour ClientLrd

    /**
     * get compte par mail
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws CompteInconnuException
     */
    public Compte getComptebyMail(String mail)throws FormatInvalideException, CompteInconnuException;
}
