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
public class PanierNoAccountLinkedToClientException extends Exception {

    /**
     * Creates a new instance of
     * <code>PanierNoAccountLinkedToClientException</code> without detail
     * message.
     */
    public PanierNoAccountLinkedToClientException() {
    }

    /**
     * Constructs an instance of
     * <code>PanierNoAccountLinkedToClientException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PanierNoAccountLinkedToClientException(String msg) {
        super(msg);
    }
}
