/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Produit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface ProduitFacadeLocal {

    /**
     *
     * @param produit
     */
    void create(Produit produit);

    /**
     *
     * @param produit
     */
    void edit(Produit produit);

    /**
     *
     * @param produit
     */
    void remove(Produit produit);

    /**
     *
     * @param id
     * @return
     */
    Produit find(Object id);

    /**
     *
     * @return
     */
    List<Produit> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Produit> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
    
}
