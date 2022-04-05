/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import com.jin.baptiste.company.entities.TypeProduitEnum;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Wang
 */
public class ProduitExport implements Serializable{
    private Long id;
    private String nom;
    private TypeProduitEnum type;
    private double prixHT;
    private long idPanier;
    private String description;
    private int stock;
    private List<Long> listIdPaniers;

    public ProduitExport(Long id, String nom, TypeProduitEnum type, double prixHT, long idPanier, String description, int stock, List<Long> listIdPaniers) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prixHT = prixHT;
        this.idPanier = idPanier;
        this.description = description;
        this.stock = stock;
        this.listIdPaniers = listIdPaniers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeProduitEnum getType() {
        return type;
    }

    public void setType(TypeProduitEnum type) {
        this.type = type;
    }

    public double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(double prixHT) {
        this.prixHT = prixHT;
    }

    public long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(long idPanier) {
        this.idPanier = idPanier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Long> getListIdPaniers() {
        return listIdPaniers;
    }

    public void setListIdPaniers(List<Long> listIdPaniers) {
        this.listIdPaniers = listIdPaniers;
    }
    
    
}
