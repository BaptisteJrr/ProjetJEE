/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author LeNonGrillePain
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    //contraint Unique fait par verification
    private String email;
    private String adresse;
    
    
    // private String codeP;
    // les info bouclent pas
    @OneToOne
    private Compte compte;
    
    @OneToMany(mappedBy = "client")
    private Collection<Panier> listePanier;

    /**
     *
     */
    public Client() {
    }

    /**
     *
     * @return String adresse
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
     * @return String nom
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
     * @return String prenom
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
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return Compte
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
     * @return Collection<Panier> listePanier
     */
    public Collection<Panier> getListePanier() {
        return listePanier;
    }

    /**
     *
     * @param listePanier
     */
    public void setListePanier(Collection<Panier> listePanier) {
        this.listePanier = listePanier;
    }
    
    /**
     *
     * @return Long id
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
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
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", compte=" + compte + ", listePanier=" + listePanier + '}';
    }

    
    
    
}
