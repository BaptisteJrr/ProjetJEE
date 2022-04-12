/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.metier.MetierPanierLocal;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LeNonGrillePain
 */
@WebService(serviceName = "webServicePanier")
public class webServicePanier {

    @EJB
    private MetierPanierLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "payer")
    @Oneway
    public void payer(@WebParam(name = "idPanier") long idPanier) {
        ejbRef.payer(idPanier);
    }

    @WebMethod(operationName = "livrer")
    @Oneway
    public void livrer(@WebParam(name = "idPanier") long idPanier) {
        ejbRef.livrer(idPanier);
    }

    @WebMethod(operationName = "ajouterProduit")
    @Oneway
    public void ajouterProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "idPanier") long idPanier) {
        ejbRef.ajouterProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "retirerProduit")
    @Oneway
    public void retirerProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "idPanier") long idPanier) {
        ejbRef.retirerProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "retirerAllProduit")
    @Oneway
    public void retirerAllProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "idPanier") long idPanier) {
        ejbRef.retirerAllProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "supprimerPanier")
    @Oneway
    public void supprimerPanier(@WebParam(name = "idPanier") long idPanier) {
        ejbRef.supprimerPanier(idPanier);
    }
    
}
