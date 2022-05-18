/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.Panier;
import com.jin.baptiste.company.metier.MetierClientLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Wang
 */
@WebService(serviceName = "WebServiceClient")
public class WebServiceClient {

    @EJB
    private MetierClientLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerClient")
    @Oneway
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "email") String email, @WebParam(name = "adresse") String adresse) {
        ejbRef.creerClient(nom, prenom, email, adresse);
    }

    @WebMethod(operationName = "getClient")
    public ClientExport getClient(@WebParam(name = "idClient") long idClient) {
        return ejbRef.getClient(idClient);
    }

    @WebMethod(operationName = "authentification")
    public boolean authentification(@WebParam(name = "email") String email) {
        return ejbRef.authentification(email);
    }

    @WebMethod(operationName = "ajouterPanier")
    @Oneway
    public void ajouterPanier(@WebParam(name = "panier") Panier panier, @WebParam(name = "idCLient") long idCLient) {
        ejbRef.ajouterPanier(panier, idCLient);
    }

    @WebMethod(operationName = "getClientparMail")
    public ClientExport getClientparMail(@WebParam(name = "email") String email) {
        return ejbRef.getClientparMail(email);
    }
    
}
