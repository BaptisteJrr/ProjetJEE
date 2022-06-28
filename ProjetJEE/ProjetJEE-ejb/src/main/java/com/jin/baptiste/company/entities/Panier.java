/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean flagLivre;
    private boolean flagRegle;
    

    @ManyToMany
    private Collection<Produit> listeProduit;
    
    private Map<Produit,Integer> nbProduit = new HashMap<Produit,Integer>();
    @ManyToOne
    private Client client;
    @ManyToOne
    private Compte compte;
    private double prixTTC;
    private Date date;

    /**
     *
     */
    public Panier() {
    }

    /**
     *
     * @return
     */
    public Map<Produit, Integer> getNbProduit() {
        return nbProduit;
    }

    /**
     *
     * @param nbProduit
     */
    public void setNbProduit(Map<Produit, Integer> nbProduit) {
        this.nbProduit = nbProduit;
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
    public Collection<Produit> getListeProduit() {
        return listeProduit;
    }

    /**
     *
     * @param listeProduit
     */
    public void setListeProduit(Collection<Produit> listeProduit) {
        this.listeProduit = listeProduit;
        this.updatePrixTTC();
        
    }
    
    /**
     *
     * @return
     */
    public Double totalHT(){
        Double total = 0.0;
        for(Produit p : this.listeProduit){
            int nb = this.nbProduit.get(p);
            total = total + p.getPrixHT() * nb;
        }
        return total;
    }

    /**
     *
     */
    public void updatePrixTTC(){
        this.setPrixTTC(this.totalHT() * 1.2);
    }

    /**
     *
     * @return
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     *
     * @param compte
     */
    public void setCompte(Compte compte) {
        this.compte = compte;
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
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
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
        return "Panier{" + "id=" + id + ", flagLivre=" + flagLivre + ", flagRegle=" + flagRegle + ", listeProduit=" + listeProduit + ", client=" + client + ", compte=" + compte + ", prixTTC=" + prixTTC + ", date=" + date + '}';
    }
    
    
    
    
}
