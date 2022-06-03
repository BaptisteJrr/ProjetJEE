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
public class PanierEmptyException extends Exception {

    /**
     * Creates a new instance of <code>PanierEmptyException</code> without
     * detail message.
     */
    public PanierEmptyException() {
    }

    /**
     * Constructs an instance of <code>PanierEmptyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PanierEmptyException(String msg) {
        super(msg);
    }
}
