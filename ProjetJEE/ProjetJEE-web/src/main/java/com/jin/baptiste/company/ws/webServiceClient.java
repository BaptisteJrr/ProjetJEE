/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.metier.MetierClientLocal;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LeNonGrillePain
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
    public Client getClient(@WebParam(name = "idClient") Long idClient) {
        return ejbRef.getClient(idClient);
    }
    
}
