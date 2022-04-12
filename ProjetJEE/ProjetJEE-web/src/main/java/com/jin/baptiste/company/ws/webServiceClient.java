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
@WebService(serviceName = "webServiceClient")
public class webServiceClient {

    @EJB
    private MetierClientLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerClient")
    @Oneway
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "email") String email) {
        ejbRef.creerClient(nom, prenom, email);
    }

    @WebMethod(operationName = "getClient")
    public ClientExport getClient(@WebParam(name = "idClient") long idClient) {
        return ejbRef.getClient(idClient);
    }

    @WebMethod(operationName = "authentification")
    public boolean authentification(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return ejbRef.authentification(nom, prenom);
    }

    @WebMethod(operationName = "ajouterPanier")
    @Oneway
    public void ajouterPanier(@WebParam(name = "panier") Panier panier, @WebParam(name = "idCLient") long idCLient) {
        ejbRef.ajouterPanier(panier, idCLient);
    }
    
}
