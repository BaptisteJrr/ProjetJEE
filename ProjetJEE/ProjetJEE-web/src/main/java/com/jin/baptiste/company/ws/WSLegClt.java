/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.client.ExpoLegCltLocal;
import com.jin.baptiste.miage.company.projetjeesharedcompte.utilities.DecouverInterditException;
import com.jin.baptiste.miage.company.projetjeesharedcompte.utilities.Position;
import entities.Client;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Wang
 */
@WebService(serviceName = "WSLegClt")
public class WSLegClt {

    @EJB
    private ExpoLegCltLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerCLient")
    @Oneway
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "email") String email) {
        ejbRef.creerClient(nom, prenom, email);
    }

    @WebMethod(operationName = "getClient")
    public Client getClient(@WebParam(name = "idClient") String idClient) {
        Long idc = Long.parseLong(idClient);
        return ejbRef.getClient(idc);
    }

    @WebMethod(operationName = "creerCompte")
    @Oneway
    public void creerCompte(@WebParam(name = "solde") String solde, @WebParam(name = "idClient") String idClient) {
        Long idc = Long.parseLong(idClient);
        Double sol = Double.parseDouble(solde);
        ejbRef.creerCompte(sol, idc);
    }

    @WebMethod(operationName = "crediter")
    @Oneway
    public void crediter(@WebParam(name = "idCompte") String idCompte, @WebParam(name = "somme") String somme) {
        Long idcpt = Long.parseLong(idCompte);
        Double som = Double.parseDouble(somme);
        ejbRef.crediter(idcpt, som);
    }

    @WebMethod(operationName = "debiter")
    public void debiter(@WebParam(name = "idCompte") String idCompte, @WebParam(name = "somme") String somme) throws DecouverInterditException {
        Long idcpt = Long.parseLong(idCompte);
        Double som = Double.parseDouble(somme);
        ejbRef.debiter(idcpt, som);
    }

    @WebMethod(operationName = "getCompte")
    public Position getCompte(@WebParam(name = "idCompte") String idCompte) {
        Long idcpt = Long.parseLong(idCompte);
        return ejbRef.getCompte(idcpt);
    }
    
}
