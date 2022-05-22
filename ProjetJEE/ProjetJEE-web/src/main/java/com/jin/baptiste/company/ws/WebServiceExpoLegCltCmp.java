/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.exposition.ExpoLegLocal;
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
@WebService(serviceName = "WebServiceExpoLegCltCmp")
public class WebServiceExpoLegCltCmp {

    @EJB
    private ExpoLegLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerClient")
    @Oneway
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "mail") String mail, @WebParam(name = "adresse") String adresse) {
        ejbRef.creerClient(nom, prenom, mail, adresse);
    }

    @WebMethod(operationName = "getClient")
    public ClientExport getClient(@WebParam(name = "id") Long id) {
        return ejbRef.getClient(id);
    }

    @WebMethod(operationName = "creerCompte")
    @Oneway
    public void creerCompte(@WebParam(name = "solde") Double solde, @WebParam(name = "mail") String mail) {
        ejbRef.creerCompte(solde, mail);
    }

    @WebMethod(operationName = "crediter")
    @Oneway
    public void crediter(@WebParam(name = "id") Long id, @WebParam(name = "somme") Double somme) {
        ejbRef.crediter(id, somme);
    }

    @WebMethod(operationName = "debiter")
    @Oneway
    public void debiter(@WebParam(name = "id") Long id, @WebParam(name = "somme") Double somme) {
        ejbRef.debiter(id, somme);
    }
    
}
