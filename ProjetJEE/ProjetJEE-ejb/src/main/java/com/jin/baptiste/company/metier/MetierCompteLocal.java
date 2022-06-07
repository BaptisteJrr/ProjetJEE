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
    
    public void creerCompte (double solde, String email) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException ;
    // au choix entre les deux
    public Compte getComptebyidCompte(long idCompte) throws CompteInconnuException;
    
    public Compte getComptebyidClient(long idClient) throws ClientInconnuException, CompteInconnuException;
    
    public void crediter(long idCompte, double somme) throws CompteInconnuException, CompteSommeNegaException;
    // throws CompteSoldeNegaException
    public void debiter(long idCompte, double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException;
    
    //Pour ClientLrd
    public Compte getComptebyMail(String mail)throws FormatInvalideException, CompteInconnuException;
}
