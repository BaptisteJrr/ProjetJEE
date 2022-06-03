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
public class ClientCompteAlreadyLinkedException extends Exception {

    /**
     * Creates a new instance of <code>ClientCompteAlreadyLinkedException</code>
     * without detail message.
     */
    public ClientCompteAlreadyLinkedException() {
    }

    /**
     * Constructs an instance of <code>ClientCompteAlreadyLinkedException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ClientCompteAlreadyLinkedException(String msg) {
        super(msg);
    }
}
