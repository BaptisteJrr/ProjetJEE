/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.ExporLegCommerceLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitPrixNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
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
@WebService(serviceName = "WebServiceCommercial")
public class WebServiceCommercial {

    @EJB
    private ExporLegCommerceLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") String idProduit) throws ProduitInconnuException {
        Long idProduitL = Long.parseLong(idProduit);
        return ejbRef.getProduit(idProduitL);
    }

    @WebMethod(operationName = "stockerProduit")
    public void stockerProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "quantite") String n) throws ProduitInconnuException, ProduitQuantiteNegativeException {
        Long idProduitL = Long.parseLong(idProduit);
        int nI = Integer.parseInt(n);
        ejbRef.stockerProduit(idProduitL, nI);
    }

    @WebMethod(operationName = "modifierProduit")
    public void modifierProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "nom") String nom, @WebParam(name = "description") String description, @WebParam(name = "prixHT") String prixHT, @WebParam(name = "type") String type) throws ProduitInconnuException {
        Double prixHTD = Double.parseDouble(prixHT);
        Long idProduitL = Long.parseLong(idProduit);
        TypeProduitEnum typeT = TypeProduitEnum.valueOf(type);
        ejbRef.modifierProduit(idProduitL, nom, description, prixHTD, typeT);
    }

    @WebMethod(operationName = "vendreProduit")
    public void vendreProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "quantite") String quantite) throws ProduitInconnuException, ProduitStockInsuffisantException, ProduitQuantiteNegativeException {
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

    @WebMethod(operationName = "creerProduit")
    public void creerProduit(@WebParam(name = "nom") String nom, @WebParam(name = "description") String description, @WebParam(name = "prixHT") String prixHT, @WebParam(name = "type") String type, @WebParam(name = "stock") String stock) throws EmptyFieldException, ProduitQuantiteNegativeException, ProduitPrixNegativeException {
        Double prixHTD = Double.parseDouble(prixHT);
        TypeProduitEnum typeT = TypeProduitEnum.valueOf(type);
        int stockI = Integer.parseInt(stock);            
        ejbRef.creerProduit(nom, description, prixHTD, typeT, stockI);
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

    @WebMethod(operationName = "getAllType")
    public List<TypeProduitEnum> getAllType() {
        return ejbRef.getAllType();
    }
    
}
