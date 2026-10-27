/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.exceptions;

/**
 * Thrown when there are no tickets left for an event.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class NoTicketsAvailableException extends Exception {

    /**
     * Creates a new NoTicketsAvailableException.
     *
     * @param message the detail message
     */
    public NoTicketsAvailableException(String message) {
        super(message);
    }
}
