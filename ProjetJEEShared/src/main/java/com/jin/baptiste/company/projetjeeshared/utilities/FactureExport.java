/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author Baptiste
 */
public class FactureExport {
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private Double prixHT;
    private Double prixTTC;
    private Date date;
    private Map<String, Integer> NbProduit; //nom, quantite
    
    public FactureExport(String nom, String prenom, String mail, String adresse, Double prixHT, Date date, Map<String, Integer> NbProduit) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.prixHT = prixHT;
        this.date = date;
        this.NbProduit = NbProduit;
        this.adresse = adresse;
        this.prixTTC = 1.2 * prixHT;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Integer> getNbProduit() {
        return NbProduit;
    }

    public void setNbProduit(Map<String, Integer> NbProduit) {
        this.NbProduit = NbProduit;
    }
    
    
 
}
