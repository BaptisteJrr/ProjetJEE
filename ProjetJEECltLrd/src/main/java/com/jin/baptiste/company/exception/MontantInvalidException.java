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
public class MontantInvalidException extends Exception {

    /**
     * Creates a new instance of <code>MontantInvalidException</code> without
     * detail message.
     */
    public MontantInvalidException() {
    }

    /**
     * Constructs an instance of <code>MontantInvalidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MontantInvalidException(String msg) {
        super(msg);
    }
}
