/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import com.jin.baptiste.company.exposition.ExpoLegPanierLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import java.util.List; 
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author LeNonGrillePain
 */
@WebService(serviceName = "WebServicePanier")
public class WebServicePanier {

    @EJB
    private ExpoLegPanierLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") Long idProduit) {
        return ejbRef.getProduit(idProduit);
    }

    @WebMethod(operationName = "getProduitByType")
    public List<ProduitExport> getProduitByType(@WebParam(name = "type") TypeProduitEnum type) {
        return ejbRef.getProduitByType(type);
    }

    @WebMethod(operationName = "searchProduitByName")
    public List<ProduitExport> searchProduitByName(@WebParam(name = "nom") String nom) {
        return ejbRef.searchProduitByName(nom);
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

    @WebMethod(operationName = "ajouterProduitToAClient")
    @Oneway
    public void ajouterProduitToClient(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idClient") Long idClient) {
        ejbRef.ajouterProduitToClient(idProduit, idClient);
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
    public void supprimerPanier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.supprimerPanier(idPanierL);
    }

    @WebMethod(operationName = "getPanierActif")
    public PanierExport getPanierActif(@WebParam(name = "idClient") String idClient) {
        Long idClientL = Long.parseLong(idClient);
        return ejbRef.getPanierActif(idClientL);
    }
    
}
