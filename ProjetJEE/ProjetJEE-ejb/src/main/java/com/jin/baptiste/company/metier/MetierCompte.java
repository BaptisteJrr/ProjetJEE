/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.metier;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.entities.Compte;
import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.CompteFacadeLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author LeNonGrillePain
 */
@Stateless
public class MetierCompte implements MetierCompteLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private CompteFacadeLocal compteFacade;
    
    /**
     * creation d'un compte appele dans la methode creerClient
     * @param solde
     * @param email
     * @throws EmptyFieldException
     * @throws FormatInvalideException
     * @throws ClientInconnuException
     * @throws ClientCompteAlreadyLinkedException
     */
    @Override
    public void creerCompte(double solde, String email) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException{
        //Verification email existe dans la BD  

        if(email == null || email.equals("")){
            throw new EmptyFieldException();
        }
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches())throw new FormatInvalideException();
        
        Client clt = this.clientFacade.findbyEmail(email);
        if( clt == null){
            throw new ClientInconnuException();
        }else{
            if(clt.getCompte() != null){
                throw new ClientCompteAlreadyLinkedException();
            }
            else{
                Compte cpt = new Compte();      
                cpt.setSolde(solde);
                cpt.setClient(clt);
                clt.setCompte(cpt);
                Collection<Panier> listePanier = clt.getListePanier();
                for(Panier p : listePanier){
                    if(p.getCompte() == null && !p.isFlagLivre() && !p.isFlagRegle()){
                        p.setCompte(cpt);
                    }
                }
                clt.setListePanier(listePanier);
                this.compteFacade.create(cpt);
            }
        }
    }

    /**
     * crediter un compte
     * @param idCompte
     * @param somme
     * @throws CompteInconnuException
     * @throws CompteSommeNegaException
     */
    @Override
    public void crediter(long idCompte, double somme) throws CompteInconnuException, CompteSommeNegaException {
        Compte cpt = this.compteFacade.find(idCompte);
        if(cpt == null){
            throw new CompteInconnuException();
        }else{
            if(somme > 0){
                cpt.setSolde(cpt.getSolde() + somme);
                this.compteFacade.edit(cpt);
            }   
            else{
                throw new CompteSommeNegaException();
            }
        }
        
    }

    /**
     * debiter un compte
     * @param idCompte
     * @param somme
     * @throws CompteInconnuException
     * @throws CompteSoldeNegaException
     * @throws CompteSommeNegaException
     */
    @Override
    public void debiter(long idCompte, double somme) throws CompteInconnuException, CompteSoldeNegaException, CompteSommeNegaException {
        Compte cpt = this.compteFacade.find(idCompte);
        if(cpt == null){
            throw new CompteInconnuException();
        }
        if(somme > 0){
            if(cpt.getSolde() - somme < 0){
                throw new CompteSoldeNegaException();
            }else{
                cpt.setSolde(cpt.getSolde() - somme);
                this.compteFacade.edit(cpt);
            }
            
        }
        else
            throw new CompteSommeNegaException();
        
    }

    /**
     * get compte par idCompte
     * @param idCompte
     * @return
     * @throws CompteInconnuException
     */

    @Override
    public Compte getComptebyidCompte(long idCompte) throws CompteInconnuException {        
        Compte cpt =  this.compteFacade.find(idCompte);
        if(cpt == null){
            throw new CompteInconnuException();
        }

        return cpt;
    }

    /**
     * get compte par mail 
     * @param mail
     * @return
     * @throws FormatInvalideException
     * @throws CompteInconnuException
     */

    @Override
    public Compte getComptebyMail(String mail) throws FormatInvalideException, CompteInconnuException {
        //Verifier le formal du Mail xxx@yyy.zz
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(mail).matches())throw new FormatInvalideException();
        Client clt = this.clientFacade.findbyEmail(mail);
        if(clt != null){
            return clt.getCompte();
        }else{
            throw new CompteInconnuException();
        }
        
    }
}
