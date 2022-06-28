/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Wang
 */
public class PanierExport implements Serializable{
    private Long id;
    private boolean flagLivre;
    private boolean flagRegle;
    private Collection<Long> listeIdProduit;

    private long idClient;

    private long idCompte;
    private double prixTTC;
    private Date date;
    private Map<String,Integer> nbProduit;

    /**
     *
     * @param id
     * @param flagLivre
     * @param flagRegle
     * @param listeIdProduit
     * @param prixTTC
     * @param date
     * @param nbProduit
     */
    public PanierExport(Long id, boolean flagLivre, boolean flagRegle, Collection<Long> listeIdProduit, double prixTTC, Date date, Map<String,Integer> nbProduit) {
        this.id = id;
        this.flagLivre = flagLivre;
        this.flagRegle = flagRegle;
        this.listeIdProduit = listeIdProduit;
        this.prixTTC = prixTTC;
        this.date = date;
        this.nbProduit = nbProduit;
    }

    /**
     *
     * @return
     */
    public Map<String, Integer> getNbProduit() {
        return nbProduit;
    }

    /**
     *
     * @param nbProduit
     */
    public void setNbProduit(Map<String, Integer> nbProduit) {
        this.nbProduit = nbProduit;
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
    public boolean isFlagLivre() {
        return flagLivre;
    }

    /**
     *
     * @param flagLivre
     */
    public void setFlagLivre(boolean flagLivre) {
        this.flagLivre = flagLivre;
    }

    /**
     *
     * @return
     */
    public boolean isFlagRegle() {
        return flagRegle;
    }

    /**
     *
     * @param flagRegle
     */
    public void setFlagRegle(boolean flagRegle) {
        this.flagRegle = flagRegle;
    }

    /**
     *
     * @return
     */
    public Collection<Long> getListeIdProduit() {
        return listeIdProduit;
    }

    /**
     *
     * @param listeIdProduit
     */
    public void setListeIdProduit(Collection<Long> listeIdProduit) {
        this.listeIdProduit = listeIdProduit;
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
    public long getIdCompte() {
        return idCompte;
    }

    /**
     *
     * @param idCompte
     */
    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    /**
     *
     * @return
     */
    public double getPrixTTC() {
        return prixTTC;
    }

    /**
     *
     * @param prixTTC
     */
    public void setPrixTTC(double prixTTC) {
        this.prixTTC = prixTTC;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
