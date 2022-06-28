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
    private ExporLegLivrerLocal ejbRef;

    /**
     *
     * @param idPanier
     */
    @WebMethod(operationName = "livrerPanier")
    @Oneway
    public void livrerPanier(@WebParam(name = "idPanier") String idPanier) {
        Long idPanierL = Long.parseLong(idPanier);
        ejbRef.livrerPanier(idPanierL);
    }

    /**
     *
     * @return
     */
    @WebMethod(operationName = "getListPanierNonLivre")
    public List<PanierExport> getListPanierNonLivre() {
        return ejbRef.getListPanierNonLivre();
    }

    /**
     *
     * @return
     */
    @WebMethod(operationName = "getListPanierLivre")
    public List<PanierExport> getListPanierLivre() {
        return ejbRef.getListPanierLivre();
    }
    
}
