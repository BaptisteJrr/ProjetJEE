/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import com.jin.baptiste.company.exposition.ExporLegCommerceLocal;
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
@WebService(serviceName = "WebServiceServiceCommercial")
public class WebServiceServiceCommercial {

    @EJB
    private ExporLegCommerceLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") String idProduit) {
        Long idProduitL = Long.parseLong(idProduit);
        return ejbRef.getProduit(idProduitL);
    }

    @WebMethod(operationName = "stockerProduit")
    @Oneway
    public void stockerProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "nombre") String n) {
        int nI = Integer.parseInt(n);
        Long idProduitL = Long.parseLong(idProduit);
        ejbRef.stockerProduit(idProduitL, nI);
    }

    @WebMethod(operationName = "modifierProduit")
    @Oneway
    public void modifierProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "nom") String nom, @WebParam(name = "description") String description, @WebParam(name = "prixHT") String prixHT, @WebParam(name = "type") String type) {
        Long idProduitL = Long.parseLong(idProduit);
        Double prixHTD = Double.parseDouble(prixHT);
        TypeProduitEnum typeT = TypeProduitEnum.valueOf(type);
        ejbRef.modifierProduit(idProduitL, nom, description, prixHTD, typeT);
    }

    @WebMethod(operationName = "vendreProduit")
    @Oneway
    public void vendreProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "quantite") String quantite) {
        Long idProduitL = Long.parseLong(idProduit);
        int quantiteI = Integer.parseInt(quantite);
        ejbRef.vendreProduit(idProduitL, quantiteI);
    }

    @WebMethod(operationName = "supprimerProduit")
    @Oneway
    public void supprimerProduit(@WebParam(name = "idProduit") String idProduit) {
        Long idProduitL = Long.parseLong(idProduit);
        ejbRef.supprimerProduit(idProduitL);
    }

    @WebMethod(operationName = "getListProduit")
    public List<ProduitExport> getListProduit() {
        return ejbRef.getListProduit();
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
    
}
