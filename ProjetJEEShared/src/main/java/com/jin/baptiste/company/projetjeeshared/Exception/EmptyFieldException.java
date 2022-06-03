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
public class EmptyFieldException extends Exception {

    /**
     * Creates a new instance of <code>EmptyFieldException</code> without detail
     * message.
     */
    public EmptyFieldException() {
    }

    /**
     * Constructs an instance of <code>EmptyFieldException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyFieldException(String msg) {
        super(msg);
    }
}
