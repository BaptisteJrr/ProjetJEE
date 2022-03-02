/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facades;

import entities.Panier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wang
 */
@Local
public interface PanierFacadeLocal {

    void create(Panier panier);

    void edit(Panier panier);

    void remove(Panier panier);

    Panier find(Object id);

    List<Panier> findAll();

    List<Panier> findRange(int[] range);

    int count();
    
}
