/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.Exception;

/**
 *
 * @author Baptiste
 */
public class ProduitPrixNegativeException extends Exception {

    /**
     * Creates a new instance of <code>ProduitPrixNegativeException</code>
     * without detail message.
     */
    public ProduitPrixNegativeException() {
    }

    /**
     * Constructs an instance of <code>ProduitPrixNegativeException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProduitPrixNegativeException(String msg) {
        super(msg);
    }
}
