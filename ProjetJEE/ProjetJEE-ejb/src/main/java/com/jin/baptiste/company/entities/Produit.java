/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.entities;

import com.jin.baptiste.company.projetjeeshared.utilities.TypeProduitEnum;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author LeNonGrillePain
 */
@Entity
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private TypeProduitEnum type;
    private double prixHT;
    @ManyToOne
    private Panier panier;
    private String description;
    private int stock;
    @ManyToMany(mappedBy = "listeProduit")
    private List<Panier> listePanier;

    /**
     *
     */
    public Produit() {
    }

    /**
     *
     * @return
     */
    public List<Panier> getListePanier() {
        return listePanier;
    }

    /**
     *
     * @param listePanier
     */
    public void setListePanier(List<Panier> listePanier) {
        this.listePanier = listePanier;
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
    public TypeProduitEnum getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(TypeProduitEnum type) {
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
    public Panier getPanier() {
        return panier;
    }

    /**
     *
     * @param panier
     */
    public void setPanier(Panier panier) {
        this.panier = panier;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", prixHT=" + prixHT + ", panier=" + panier + ", description=" + description + ", stock=" + stock + '}';
    }
    
    
    
}
