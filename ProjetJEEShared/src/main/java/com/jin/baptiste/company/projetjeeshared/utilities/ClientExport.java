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
 * @author LeNonGrillePain
 */
public class ClientExport implements Serializable{
    
    private Long id;
    private String nom;
    private String prenom;
    private String email; 
    private Long idCompte;
    private String adresse;
    private Collection<Long> listeIdPanier;

    /**
     *
     * @param id
     * @param nom
     * @param prenom
     * @param email
     * @param idCompte
     * @param adresse
     * @param listeIdPanier
     */
    public ClientExport(Long id, String nom, String prenom, String email, Long idCompte, String adresse, Collection<Long> listeIdPanier) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idCompte = idCompte;
        this.adresse = adresse;
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
     * @return
     */
    public Long getIdCompte() {
        return idCompte;
    }

    /**
     *
     * @param idCompte
     */
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
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
    
    
      
    
    
    
    
}
