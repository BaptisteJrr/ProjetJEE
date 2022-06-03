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
public class PanierAlreadyPayeException extends Exception {

    /**
     * Creates a new instance of <code>PanierAlreadyPayeException</code> without
     * detail message.
     */
    public PanierAlreadyPayeException() {
    }

    /**
     * Constructs an instance of <code>PanierAlreadyPayeException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PanierAlreadyPayeException(String msg) {
        super(msg);
    }
}
