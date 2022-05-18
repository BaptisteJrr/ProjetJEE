/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.metier.MetierCompteLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.CompteExport;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Wang
 */
@WebService(serviceName = "WebServiceCompte")
public class WebServiceCompte {

    @EJB
    private MetierCompteLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerCompte")
    @Oneway
    public void creerCompte(@WebParam(name = "solde") double solde, @WebParam(name = "email") String email) {
        ejbRef.creerCompte(solde, email);
    }

    @WebMethod(operationName = "getComptebyidCompte")
    public CompteExport getComptebyidCompte(@WebParam(name = "idCompte") long idCompte) {
        return ejbRef.getComptebyidCompte(idCompte);
    }

    @WebMethod(operationName = "getComptebyidClient")
    public CompteExport getComptebyidClient(@WebParam(name = "idClient") long idClient) {
        return ejbRef.getComptebyidClient(idClient);
    }

    @WebMethod(operationName = "crediter")
    @Oneway
    public void crediter(@WebParam(name = "idCompte") long idCompte, @WebParam(name = "somme") double somme) {
        ejbRef.crediter(idCompte, somme);
    }

    @WebMethod(operationName = "debiter")
    @Oneway
    public void debiter(@WebParam(name = "idCompte") long idCompte, @WebParam(name = "somme") double somme) {
        ejbRef.debiter(idCompte, somme);
    }
    
}
