/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company;

import com.jin.baptiste.company.entities.Client;
import com.jin.baptiste.company.facade.ClientFacadeLocal;
import com.jin.baptiste.company.facade.CompteFacadeLocal;
import com.jin.baptiste.company.facade.PanierFacadeLocal;
import com.jin.baptiste.company.facade.ProduitFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean {

    @EJB
    private ProduitFacadeLocal produitFacade;

    @EJB
    private PanierFacadeLocal panierFacade;

    @EJB
    private CompteFacadeLocal compteFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    

    public void businessMethod() {
        Client c = new Client();
        c.setNom("Jarry");
        c.setPrenom("Baptiste");
        this.clientFacade.create(c);
        
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
