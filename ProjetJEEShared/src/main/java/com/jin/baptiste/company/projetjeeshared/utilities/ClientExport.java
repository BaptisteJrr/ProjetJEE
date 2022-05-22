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

    public ClientExport(Long id, String nom, String prenom, String email, Long idCompte, String adresse, Collection<Long> listeIdPanier) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idCompte = idCompte;
        this.adresse = adresse;
        this.listeIdPanier = listeIdPanier;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public Collection<Long> getListeIdPanier() {
        return listeIdPanier;
    }

    public void setListeIdPanier(Collection<Long> listeIdPanier) {
        this.listeIdPanier = listeIdPanier;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
      
    
    
    
    
}
