/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class MetierClient implements MetierClientLocal {

    @EJB
    private MetierCompteLocal metierCompte;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    /**
     *
     * @param nom
     * @param prenom
     * @param email
     * @param adresse
     * @throws FormatInvalideException
     * @throws EmptyFieldException
     * @throws ClientAlreadyExistException
     * @throws ClientCompteAlreadyLinkedException
     * @throws ClientInconnuException
     */
    @Override
    public void creerClient(String nom, String prenom, String email, String adresse) throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientCompteAlreadyLinkedException, ClientInconnuException {
        
        
        //Vérifier si une des entrées est vide
        if(nom == null || nom.equals("") || prenom.equals("") || prenom == null || adresse.equals("") || adresse == null || email == null || email.equals("")){
            throw new EmptyFieldException();
        }
        
        //Verifier le formal du Mail xxx@yyy.zz
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches())throw new FormatInvalideException();
        
        //Verification du Mail soit unique
        if (this.clientFacade.findbyEmail(email) == null){
            Client c = new Client();
            c.setNom(nom);
            c.setEmail(email);
            c.setPrenom(prenom);
            c.setAdresse(adresse);
            this.clientFacade.create(c);
            this.metierCompte.creerCompte(0.0, email);
            
        }else{
            throw new ClientAlreadyExistException();
        }
    }

    /**
     *
     * @param idClient
     * @return
     * @throws ClientInconnuException
     */
    @Override
    public Client getClient(long idClient) throws ClientInconnuException {
        Client clt = this.clientFacade.find(idClient);
        if(clt == null){
            throw new ClientInconnuException();
        }
        return clt;
    }  

    /**
     *
     * @param email
     * @return
     * @throws FormatInvalideException
     */
    @Override
    public boolean authentification(String email) throws FormatInvalideException {
        
        //Verifier le formal du Mail xxx@yyy.zz
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches())throw new FormatInvalideException();
        
        Client clt = this.clientFacade.findbyEmail(email);
        if(clt == null){
            return false;
        }else{
            return true;
        }
    }


    /**
     *
     * @param email
     * @return
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     */
    @Override
    public Client getClientparMail(String email) throws FormatInvalideException, ClientInconnuException {
        //Verifier le formal du Mail xxx@yyy.zz
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches())throw new FormatInvalideException();
        Client clt = this.clientFacade.findbyEmail(email);
        if(clt == null){
            throw new ClientInconnuException();
        }
        return clt;        
    }
}
