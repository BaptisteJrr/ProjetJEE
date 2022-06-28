/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.facade;

import com.jin.baptiste.company.entities.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LeNonGrillePain
 */
@Local
public interface ClientFacadeLocal {

    /**
     *
     * @param client
     */
    void create(Client client);

    /**
     *
     * @param client
     */
    void edit(Client client);

    /**
     *
     * @param client
     */
    void remove(Client client);

    /**
     *
     * @param id
     * @return
     */
    Client find(Object id);

    /**
     *
     * @return
     */
    List<Client> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<Client> findRange(int[] range);

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
    Client findbyEmail(String email);
}
