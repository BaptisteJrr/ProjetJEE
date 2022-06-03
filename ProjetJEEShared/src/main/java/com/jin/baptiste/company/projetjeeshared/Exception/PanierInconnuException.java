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
public class PanierInconnuException extends Exception {

    /**
     * Creates a new instance of <code>PanierInconnuException</code> without
     * detail message.
     */
    public PanierInconnuException() {
    }

    /**
     * Constructs an instance of <code>PanierInconnuException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PanierInconnuException(String msg) {
        super(msg);
    }
}
