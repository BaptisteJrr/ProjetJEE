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
public class PanierNonPayeException extends Exception {

    /**
     * Creates a new instance of <code>PanierNonPayeException</code> without
     * detail message.
     */
    public PanierNonPayeException() {
    }

    /**
     * Constructs an instance of <code>PanierNonPayeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PanierNonPayeException(String msg) {
        super(msg);
    }
}
