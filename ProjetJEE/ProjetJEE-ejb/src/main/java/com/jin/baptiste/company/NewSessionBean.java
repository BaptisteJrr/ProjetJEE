/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company;

import entities.NewEntity;
import entities.NewEntityFacadeLocal;
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
    private NewEntityFacadeLocal newEntityFacade;

    
    
    public void businessMethod() {
        NewEntity e = new NewEntity();
        e.setNom("TOTO");
        this.newEntityFacade.create(e);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
