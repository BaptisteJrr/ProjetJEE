/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LeNonGrillePain
 */
public class Position implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private double solde;
    private Date date;
    private Long idCompte;

    public Position(double solde, Date date, Long idCompte) {
        this.solde = solde;
        this.date = date;
        this.idCompte = idCompte;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }


    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
