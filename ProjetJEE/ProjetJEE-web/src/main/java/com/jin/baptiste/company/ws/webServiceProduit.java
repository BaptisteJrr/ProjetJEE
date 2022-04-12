/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.metier.MetierProduitLocal;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LeNonGrillePain
 */
@WebService(serviceName = "webServiceProduit")
public class webServiceProduit {

    @EJB
    private MetierProduitLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "modifierProduit")
    @Oneway
    public void modifierProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "nom") String nom, @WebParam(name = "description") String description, @WebParam(name = "prixHT") double prixHT, @WebParam(name = "type") TypeProduitEnum type) {
        ejbRef.modifierProduit(idProduit, nom, description, prixHT, type);
    }

    @WebMethod(operationName = "vendreProduit")
    @Oneway
    public void vendreProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "quantite") int quantite) {
        ejbRef.vendreProduit(idProduit, quantite);
    }

    @WebMethod(operationName = "stockerProduit")
    @Oneway
    public void stockerProduit(@WebParam(name = "idProduit") long idProduit, @WebParam(name = "quantite") int quantite) {
        ejbRef.stockerProduit(idProduit, quantite);
    }

    @WebMethod(operationName = "supprimerProduit")
    @Oneway
    public void supprimerProduit(@WebParam(name = "idProduit") long idProduit) {
        ejbRef.supprimerProduit(idProduit);
    }

    @WebMethod(operationName = "creerProduit")
    @Oneway
    public void creerProduit(@WebParam(name = "nom") String nom, @WebParam(name = "description") String description, @WebParam(name = "prixHT") double prixHT, @WebParam(name = "type") TypeProduitEnum type, @WebParam(name = "stock") int stock) {
        ejbRef.creerProduit(nom, description, prixHT, type, stock);
    }
    
}
