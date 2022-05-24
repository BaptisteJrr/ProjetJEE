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
    public List<ProduitExport> getProduitByType(@WebParam(name = "type") String type) {
        TypeProduitEnum typeT = TypeProduitEnum.valueOf(type);
        return ejbRef.getProduitByType(typeT);
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
    public void payerPanier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.payerPanier(idPanierL);
    }

    @WebMethod(operationName = "ajouterProduit")
    @Oneway
    public void ajouterProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        Long idProduitL = Long.parseLong(idProduit);
        ejbRef.ajouterProduit(idProduitL, idPanierL);
    }

    @WebMethod(operationName = "retirerProduit")
    @Oneway
    public void retirerProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idPanier") String idPanier) {
        Long idProduitL = Long.parseLong(idProduit);
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.retirerProduit(idProduitL, idPanierL);
    }

    @WebMethod(operationName = "retirerAllProduit")
    @Oneway
    public void retirerAllProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idPanier") String idPanier) {
        Long idProduitL = Long.parseLong(idProduit);
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.retirerAllProduit(idProduitL, idPanierL);
    }

    @WebMethod(operationName = "supprimerPanier")
    @Oneway
    public void supprimerPanier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.supprimerPanier(idPanierL);
    }
    
}
