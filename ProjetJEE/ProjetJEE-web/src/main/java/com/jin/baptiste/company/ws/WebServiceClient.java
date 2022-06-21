/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.ExpoLegLocal;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientAlreadyExistException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientCompteAlreadyLinkedException;
import com.jin.baptiste.company.projetjeeshared.Exception.ClientInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSoldeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.CompteSommeNegaException;
import com.jin.baptiste.company.projetjeeshared.Exception.EmptyFieldException;
import com.jin.baptiste.company.projetjeeshared.Exception.FormatInvalideException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyLivreException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierAlreadyPayeException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierEmptyException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.PanierNoAccountLinkedToClientException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitInconnuException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitQuantiteNegativeException;
import com.jin.baptiste.company.projetjeeshared.Exception.ProduitStockInsuffisantException;
import com.jin.baptiste.company.projetjeeshared.utilities.ClientExport;
import com.jin.baptiste.company.projetjeeshared.utilities.FactureExport;
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
@WebService(serviceName = "WebServiceClient")
public class WebServiceClient {

    @EJB
    private ExpoLegLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerClient")
    public void creerClient(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "mail") String mail, @WebParam(name = "adresse") String adresse) throws FormatInvalideException, EmptyFieldException, ClientAlreadyExistException, ClientInconnuException, ClientCompteAlreadyLinkedException {
        ejbRef.creerClient(nom, prenom, mail, adresse);
    }

    @WebMethod(operationName = "getClientByMail")
    public ClientExport getClientByMail(@WebParam(name = "mail") String mail) throws FormatInvalideException, ClientInconnuException {
        return ejbRef.getClientByMail(mail);
    }

//    @WebMethod(operationName = "creerCompte")
//    public void creerCompte(@WebParam(name = "solde") String solde, @WebParam(name = "mail") String mail) throws EmptyFieldException, FormatInvalideException, ClientInconnuException, ClientCompteAlreadyLinkedException {
//        Double soldeD = Double.parseDouble(solde);
//        ejbRef.creerCompte(soldeD, mail);
//    }

    @WebMethod(operationName = "getFacture")
    public List<FactureExport> getFacture(@WebParam(name = "email") String email) {
        return ejbRef.getFacture(email);
    }

    @WebMethod(operationName = "getProduit")
    public ProduitExport getProduit(@WebParam(name = "idProduit") String idProduit) throws ProduitInconnuException {
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
    public PanierExport getPanier(@WebParam(name = "idPanier") String idPanier) throws PanierInconnuException {
        Long idPanierL = Long.parseLong(idPanier);
        return ejbRef.getPanier(idPanierL);
    }

    @WebMethod(operationName = "payerPanier")
    public void payerPanier(@WebParam(name = "idPanier") String idPanier) throws PanierInconnuException, PanierEmptyException, CompteSoldeNegaException, CompteInconnuException, CompteSommeNegaException, PanierNoAccountLinkedToClientException, ProduitInconnuException, ProduitQuantiteNegativeException, ProduitStockInsuffisantException {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.payerPanier(idPanierL);
    }

    @WebMethod(operationName = "ajouterProduitToClient")
    public void ajouterProduitToClient(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idClient") String idClient) throws ClientInconnuException, ProduitInconnuException {
        Long idProduitL = Long.parseLong(idProduit);
        Long idClientL = Long.parseLong(idClient);
        ejbRef.ajouterProduitToClient(idProduitL, idClientL);
    }

    @WebMethod(operationName = "retirerProduit")
    public void retirerProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idPanier") String idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        Long idProduitL = Long.parseLong(idProduit);
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.retirerProduit(idProduitL, idPanierL);
    }

    @WebMethod(operationName = "retirerAllProduit")
    public void retirerAllProduit(@WebParam(name = "idProduit") String idProduit, @WebParam(name = "idPanier") String idPanier) throws PanierInconnuException, ProduitInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        Long idProduitL = Long.parseLong(idProduit);
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.retirerAllProduit(idProduitL, idPanierL);
    }

    @WebMethod(operationName = "supprimerPanier")
    public void supprimerPanier(@WebParam(name = "idPanier") String idPanier) throws PanierInconnuException, PanierAlreadyPayeException, PanierAlreadyLivreException {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.supprimerPanier(idPanierL);
    }

    @WebMethod(operationName = "getPanierActif")
    public PanierExport getPanierActif(@WebParam(name = "idClient") String idClient) throws ClientInconnuException {
        Long idClientL = Long.parseLong(idClient);
        return ejbRef.getPanierActif(idClientL);
    }

    @WebMethod(operationName = "getPanierNonLivreByClient")
    public List<PanierExport> getPanierNonLivreByClient(@WebParam(name = "idClient") String idClient) throws ClientInconnuException {
        Long idClientL = Long.parseLong(idClient);
        return ejbRef.getPanierNonLivreByClient(idClientL);
    }

    @WebMethod(operationName = "getListProduit")
    public List<ProduitExport> getListProduit() {
        return ejbRef.getListProduit();
    }
    
}
