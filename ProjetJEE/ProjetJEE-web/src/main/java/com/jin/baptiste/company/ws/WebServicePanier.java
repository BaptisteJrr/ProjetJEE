/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.TypeProduitEnum;
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
    public ProduitExport getProduit(@WebParam(name = "idProduit") String idProduit) {
        Long idProduitL = Long.parseLong(idProduit);
        return ejbRef.getProduit(idProduitL);
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
    public PanierExport getPanier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        return ejbRef.getPanier(idPanierL);
    }

    @WebMethod(operationName = "payerPanier")
    @Oneway
    public void payerPanier(@WebParam(name = "idPanier") Long idPanier) {
        ejbRef.payerPanier(idPanier);
    }

    @WebMethod(operationName = "ajouterProduitToAClient")
    @Oneway
    public void ajouterProduitToAClient(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "mail") String mail) {
        Long idProduitL = Long.parseLong(idProduit);
        ejbRef.ajouterProduitToAClient(idProduitL, mail);
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
