/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Wang
 */
public class ProduitExport implements Serializable{
    private Long id;
    private String nom;
    private String type;
    private double prixHT;
    private String description;
    private int stock;
    //private List<Long> listIdPaniers;

    /**
     *
     * @param id
     * @param nom
     * @param type
     * @param prixHT
     * @param description
     * @param stock
     */
    public ProduitExport(Long id, String nom, String type, double prixHT, String description, int stock) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prixHT = prixHT;
        this.description = description;
        this.stock = stock;
       // this.listIdPaniers = listIdPaniers;
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
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public double getPrixHT() {
        return prixHT;
    }

    /**
     *
     * @param prixHT
     */
    public void setPrixHT(double prixHT) {
        this.prixHT = prixHT;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
}
