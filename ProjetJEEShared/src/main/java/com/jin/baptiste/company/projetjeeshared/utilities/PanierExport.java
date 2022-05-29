/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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

    public PanierExport(Long id, boolean flagLivre, boolean flagRegle, Collection<Long> listeIdProduit, double prixTTC, Date date) {
        this.id = id;
        this.flagLivre = flagLivre;
        this.flagRegle = flagRegle;
        this.listeIdProduit = listeIdProduit;
        this.prixTTC = prixTTC;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFlagLivre() {
        return flagLivre;
    }

    public void setFlagLivre(boolean flagLivre) {
        this.flagLivre = flagLivre;
    }

    public boolean isFlagRegle() {
        return flagRegle;
    }

    public void setFlagRegle(boolean flagRegle) {
        this.flagRegle = flagRegle;
    }

    public Collection<Long> getListeIdProduit() {
        return listeIdProduit;
    }

    public void setListeIdProduit(Collection<Long> listeIdProduit) {
        this.listeIdProduit = listeIdProduit;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
