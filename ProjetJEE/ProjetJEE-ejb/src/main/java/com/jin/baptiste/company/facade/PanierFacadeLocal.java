/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Panier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface PanierFacadeLocal {

    /**
     *
     * @param panier
     */
    void create(Panier panier);

    /**
     *
     * @param panier
     */
    void edit(Panier panier);

    /**
     *
     * @param panier
     */
    void remove(Panier panier);

    /**
     *
     * @param id
     * @return
     */
    Panier find(Object id);

    /**
     *
     * @return
     */
    List<Panier> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Panier> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
    
}
