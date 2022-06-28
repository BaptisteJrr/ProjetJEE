/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Facture;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Baptiste
 */
@Local
public interface FactureFacadeLocal {

    /**
     *
     * @param facture
     */
    void create(Facture facture);

    /**
     *
     * @param facture
     */
    void edit(Facture facture);

    /**
     *
     * @param facture
     */
    void remove(Facture facture);

    /**
     *
     * @param id
     * @return
     */
    Facture find(Object id);

    /**
     *
     * @return
     */
    List<Facture> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Facture> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
    
    /**
     *
     * @param email
     * @return
     */
    List<Facture> findbyEmail(String email);
    
}
