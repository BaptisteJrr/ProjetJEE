/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.ws;

import com.jin.baptiste.company.exposition.ExporLegLivrerLocal;
import com.jin.baptiste.company.projetjeeshared.utilities.PanierExport;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Wang
 */
@WebService(serviceName = "WebServiceLivreur")
public class WebServiceLivreur {

    @EJB
    private ExporLegLivrerLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "livrerPaier")
    @Oneway
    public void livrerPaier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.livrerPaier(idPanierL);
    }

    @WebMethod(operationName = "getListPanierNonLivre")
    public List<PanierExport> getListPanierNonLivre() {
        return ejbRef.getListPanierNonLivre();
    }

    @WebMethod(operationName = "getListPanierLivre")
    public List<PanierExport> getListPanierLivre() {
        return ejbRef.getListPanierLivre();
    }
    
}
