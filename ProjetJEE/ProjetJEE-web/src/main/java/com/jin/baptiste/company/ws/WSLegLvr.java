/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.livreur.ExpoLegLvrLocal;
import entities.Panier;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Baptiste
 */
@WebService(serviceName = "WSLegLvr")
public class WSLegLvr {

    @EJB
    private ExpoLegLvrLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "livrer")
    @Oneway
    public void livrer(@WebParam(name = "listePanier") ArrayList<Panier> listePanier) {
        ejbRef.livrer(listePanier);
    }

    @WebMethod(operationName = "listerPanier")
    public ArrayList<Panier> listerPanier() {
        return ejbRef.listerPanier();
    }
    
}
