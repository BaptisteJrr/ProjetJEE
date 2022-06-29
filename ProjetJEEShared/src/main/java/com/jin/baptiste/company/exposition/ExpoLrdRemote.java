/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exposition;

import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import javax.ejb.Remote;

/**
 *
 * @author LeNonGrillePain
 */
@Remote
public interface ExpoLrdRemote {
    
    /**
     * Permet de créer un compte
     * @param solde
     * @param mail
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    public void creerCompte(Double solde, String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException;

    /**
     * Permet de créditer un compte d'une somme définit
     * @param id
     * @param somme
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     */
    public void crediter(Long id, Double somme) throws CompteInconnuException, CompteSommeNegaException;

    /**
     * Permet de débiter un compte d'une somme définit
     * @param id
     * @param somme
     * @throws CompteSoldeNegaException
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     */
    public void debiter(Long id, Double somme) throws CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException;

    /**
     * Permet de récupérer le compte à partir de son id
     * @param idCompte
     * @return
     * @throws CompteInconnuException
     */
    public Position getCompte(Long idCompte) throws CompteInconnuException;
    //Get Client??
    // by mail ou by idClient??

    /**
     * Permet de récupérer le compte à partir de son mail
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws CompteInconnuException
     */
    public Position getCompteByMail(String mail) throws FormatInvalideException, CompteInconnuException;
    
}
