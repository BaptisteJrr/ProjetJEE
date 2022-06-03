/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jin.baptiste.company.projetjeeshared.Exception;

/**
 *
 * @author Wang
 */
public class FormatInvalideException extends Exception {

    /**
     * Creates a new instance of <code>FormatInvalideException</code> without
     * detail message.
     */
    public FormatInvalideException() {
    }

    /**
     * Constructs an instance of <code>FormatInvalideException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FormatInvalideException(String msg) {
        super(msg);
    }
}
