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
public class ProduitStockInsuffisantException extends Exception{

    public ProduitStockInsuffisantException() {
        super("Stock non suffisant");
    }

    public ProduitStockInsuffisantException(String message) {
        super(message);
    }
    
    
}
