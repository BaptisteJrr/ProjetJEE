/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.exposition.ExpoLegPanierLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Wang
 */
@WebService(serviceName = "WebServiceExpoLegPanPro")
public class WebServiceExpoLegPanPro {

    @EJB
    private ExpoLegPanierLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") Long idProduit) {
        return ejbRef.getProduit(idProduit);
    }

    @WebMethod(operationName = "getPanier")
    public PanierExport getPanier(@WebParam(name = "idPanier") Long idPanier) {
        return ejbRef.getPanier(idPanier);
    }

    @WebMethod(operationName = "payerPanier")
    @Oneway
    public void payerPanier(@WebParam(name = "idPanier") Long idPanier) {
        ejbRef.payerPanier(idPanier);
    }

    @WebMethod(operationName = "livrerPaier")
    @Oneway
    public void livrerPaier(@WebParam(name = "idPanier") Long idPanier) {
        ejbRef.livrerPaier(idPanier);
    }

    @WebMethod(operationName = "ajouterProduit")
    @Oneway
    public void ajouterProduit(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idPanier") Long idPanier) {
        ejbRef.ajouterProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "retirerProduit")
    @Oneway
    public void retirerProduit(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idPanier") Long idPanier) {
        ejbRef.retirerProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "retirerAllProduit")
    @Oneway
    public void retirerAllProduit(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idPanier") Long idPanier) {
        ejbRef.retirerAllProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "supprimerPanier")
    @Oneway
    public void supprimerPanier(@WebParam(name = "idPanier") Long idPanier) {
        ejbRef.supprimerPanier(idPanier);
    }
    
}
