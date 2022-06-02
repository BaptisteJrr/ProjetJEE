/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.exception;

/**
 *
 * @author LeNonGrillePain
 */
public class SoldeInsufisantException extends Exception {

    /**
     * Creates a new instance of <code>SoldeInsufisantException</code> without
     * detail message.
     */
    public SoldeInsufisantException() {
    }

    /**
     * Constructs an instance of <code>SoldeInsufisantException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SoldeInsufisantException(String msg) {
        super(msg);
    }
}
