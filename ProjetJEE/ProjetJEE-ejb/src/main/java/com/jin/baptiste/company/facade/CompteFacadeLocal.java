/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Compte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface CompteFacadeLocal {

    /**
     *
     * @param compte
     */
    void create(Compte compte);

    /**
     *
     * @param compte
     */
    void edit(Compte compte);

    /**
     *
     * @param compte
     */
    void remove(Compte compte);

    /**
     *
     * @param id
     * @return
     */
    Compte find(Object id);

    /**
     *
     * @return
     */
    List<Compte> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Compte> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();        
}
