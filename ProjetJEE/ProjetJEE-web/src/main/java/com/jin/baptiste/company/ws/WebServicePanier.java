/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.ExpoLegPanierLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyLivreException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierEmptyException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNoAccountLinkedToClientException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import com.jin.baptiste.company.projetjeeshared.utilities.ProduitExport;
import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Baptiste
 */
@WebService(serviceName = "WebServicePanier")
public class WebServicePanier {

    @EJB
    private ExpoLegPanierLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") Long idProduit) throws ProduitInconnuException {
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
    public PanierExport getPanier(@WebParam(name = "idPanier") Long idPanier) throws PanierInconnuException {
        return ejbRef.getPanier(idPanier);
    }

    @WebMethod(operationName = "payerPanier")
    public void payerPanier(@WebParam(name = "idPanier") Long idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException {
        ejbRef.payerPanier(idPanier);
    }

    @WebMethod(operationName = "ajouterProduitToClient")
    public void ajouterProduitToClient(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idClient") Long idClient) throws ClientInconnuException, ProduitInconnuException {
        ejbRef.ajouterProduitToClient(idProduit, idClient);
    }

    @WebMethod(operationName = "retirerProduit")
    public void retirerProduit(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idPanier") Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        ejbRef.retirerProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "retirerAllProduit")
    public void retirerAllProduit(@WebParam(name = "idProduit") Long idProduit, @WebParam(name = "idPanier") Long idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        ejbRef.retirerAllProduit(idProduit, idPanier);
    }

    @WebMethod(operationName = "supprimerPanier")
    public void supprimerPanier(@WebParam(name = "idPanier") Long idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        ejbRef.supprimerPanier(idPanier);
    }

    @WebMethod(operationName = "getPanierActif")
    public PanierExport getPanierActif(@WebParam(name = "idClient") Long idClient) throws ClientInconnuException {
        return ejbRef.getPanierActif(idClient);
    }

    @WebMethod(operationName = "getPanierNonLivreByClient")
    public List<PanierExport> getPanierNonLivreByClient(@WebParam(name = "idClient") Long idClient) throws ClientInconnuException {
        return ejbRef.getPanierNonLivreByClient(idClient);
    }
    
}
