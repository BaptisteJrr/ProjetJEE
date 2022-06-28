/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Wang
 */
public class CompteExport implements Serializable{
    private Long id;
    private double solde;
    private long idClient;   
    private Collection<Long> listeIdPanier;

    /**
     *
     * @param id
     * @param solde
     * @param idClient
     * @param listeIdPanier
     */
    public CompteExport(Long id, double solde, long idClient, Collection<Long> listeIdPanier) {
        this.id = id;
        this.solde = solde;
        this.idClient = idClient;
        this.listeIdPanier = listeIdPanier;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getSolde() {
        return solde;
    }

    /**
     *
     * @param solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

    /**
     *
     * @return
     */
    public long getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient
     */
    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return
     */
    public Collection<Long> getListeIdPanier() {
        return listeIdPanier;
    }

    /**
     *
     * @param listeIdPanier
     */
    public void setListeIdPanier(Collection<Long> listeIdPanier) {
        this.listeIdPanier = listeIdPanier;
    }
    
    
}
