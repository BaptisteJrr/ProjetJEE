/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface MetierClientLocal {
    
    /**
     *
     * @param nom
     * @param prenom
     * @param email
     * @param adresse
     * @throws FormatInvalideException
     * @throws EmptyFieldException
     * @throws ClientAlreadyExistException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    public void creerClient(String nom, String prenom, String email, String adresse) throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientInconnuException, ClientCompteAlreadyLinkedException;
    
    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    public Client getClient(long idClient) throws ClientInconnuException;
    
    /**
     *
     * @param email
     * @return
     * @throws FormatInvalideException
     */
    public boolean authentification(String email) throws FormatInvalideException;
    
    //public void ajouterPanier(Panier panier, long idCLient) throws ClientInconnuException;

    /**
     *
     * @param email
     * @return
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     */
    
    public Client getClientparMail(String email) throws FormatInvalideException, ClientInconnuException;
}
