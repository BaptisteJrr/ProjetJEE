/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.Exception;

/**
 *
 * @author LeNonGrillePain
 */
public class ProduitQuantiteNegativeException extends Exception{

    public ProduitQuantiteNegativeException() {
        super("Quantité à ajouter négative");
    }
    
}
