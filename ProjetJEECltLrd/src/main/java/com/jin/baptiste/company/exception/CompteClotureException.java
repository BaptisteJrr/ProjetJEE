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
public class CompteClotureException extends Exception {

    /**
     * Creates a new instance of <code>CompteClotureException</code> without
     * detail message.
     */
    public CompteClotureException() {
    }

    /**
     * Constructs an instance of <code>CompteClotureException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CompteClotureException(String msg) {
        super(msg);
    }
}
