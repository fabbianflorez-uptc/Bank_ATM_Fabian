/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.exceptions;

/**
 * Thrown when an event is not found.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class EventNotFoundException extends Exception {

    /**
     * Creates a new EventNotFoundException.
     *
     * @param message the detail message
     */
    public EventNotFoundException(String message) {
        super(message);
    }
}
