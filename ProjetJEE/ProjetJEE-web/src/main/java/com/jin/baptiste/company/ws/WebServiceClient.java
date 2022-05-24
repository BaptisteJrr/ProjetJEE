/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.ExpoLegLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.Position;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LeNonGrillePain
 */
@WebService(serviceName = "WebServiceClient")
public class WebServiceClient {
    
    @EJB
    private ExpoLegLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerClient")
    @Oneway
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "mail") String mail, @WebParam(name = "adresse") String adresse) {
        ejbRef.creerClient(nom, prenom, mail, adresse);
    }

    @WebMethod(operationName = "getClientByMail")
    public ClientExport getClientByMail(@WebParam(name = "mail") String mail) {
        return ejbRef.getClientByMail(mail);
    }

    @WebMethod(operationName = "creerCompte")
    @Oneway
    public void creerCompte(@WebParam(name = "solde") String solde, @WebParam(name = "mail") String mail) {
        Double soldeD = Double.parseDouble(solde);
        ejbRef.creerCompte(soldeD, mail);
    }

    @WebMethod(operationName = "crediter")
    @Oneway
    public void crediter(@WebParam(name = "id") String id, @WebParam(name = "somme") String somme) {
        Long idL = Long.parseLong(id);
        Double sommeD = Double.parseDouble(somme);
        ejbRef.crediter(idL, sommeD);
    }

    @WebMethod(operationName = "debiter")
    @Oneway
    public void debiter(@WebParam(name = "id") String id, @WebParam(name = "somme") String somme) {
        Long idL = Long.parseLong(id);
        Double sommeD = Double.parseDouble(somme);
        ejbRef.debiter(idL, sommeD);
    }

    @WebMethod(operationName = "getCompte")
    public Position getCompte(@WebParam(name = "idCompte") String idCompte) {
        Long idCompteL = Long.parseLong(idCompte);
        return ejbRef.getCompte(idCompteL);
    }
    
}
