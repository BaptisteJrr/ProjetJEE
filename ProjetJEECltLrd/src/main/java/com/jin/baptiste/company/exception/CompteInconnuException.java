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
public class CompteInconnuException extends Exception {

    /**
     * Creates a new instance of <code>CompteInconnuException</code> without
     * detail message.
     */
    public CompteInconnuException() {
    }

    /**
     * Constructs an instance of <code>CompteInconnuException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CompteInconnuException(String msg) {
        super(msg);
    }
}
