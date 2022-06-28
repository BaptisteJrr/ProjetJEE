/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Baptiste
 */
@Entity
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private Double prixHT;
    private Double prixTTC;
    private Date date;
    private Map<String, Integer> NbProduit; //nom, quantite

    /**
     *
     */
    public Facture(){
    }
    
    /**
     *
     * @param nom
     * @param prenom
     * @param mail
     * @param adresse
     * @param prixHT
     * @param date
     * @param NbProduit
     */
    public Facture(String nom, String prenom, String mail, String adresse, Double prixHT, Date date, Map<String, Integer> NbProduit) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.prixHT = prixHT;
        this.date = date;
        this.NbProduit = NbProduit;
        this.adresse = adresse;
        this.prixTTC = 1.2 * prixHT;
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return
     */
    public Double getPrixHT() {
        return prixHT;
    }

    /**
     *
     * @param prixHT
     */
    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
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
    public Map<String, Integer> getNbProduit() {
        return NbProduit;
    }

    /**
     *
     * @param NbProduit
     */
    public void setNbProduit(Map<String, Integer> NbProduit) {
        this.NbProduit = NbProduit;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
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
        return "com.jin.baptiste.company.entities.Facture[ id=" + id + " ]";
    }
    
}
