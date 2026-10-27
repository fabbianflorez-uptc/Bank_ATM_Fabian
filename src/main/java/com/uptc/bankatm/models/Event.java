/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.models;

/**
 * Represents an event that can sell tickets.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class Event {

    private String eventId;
    private String eventName;
    private String eventDate;
    private double ticketPrice;
    private int availableTickets;

    /**
     * Creates a new Event.
     *
     * @param eventId unique id for the event
     * @param eventName name of the event
     * @param eventDate date of the event
     * @param ticketPrice price per ticket
     * @param availableTickets number of tickets available
     */
    public Event(String eventId, String eventName, String eventDate, double ticketPrice, int availableTickets) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.ticketPrice = ticketPrice;
        this.availableTickets = availableTickets;
    }

    /**
     * Gets the event id.
     *
     * @return the event id
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the event id.
     *
     * @param eventId new id
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets the event name.
     *
     * @return the name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the event name.
     *
     * @param eventName new name
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets the event date.
     *
     * @return the date
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Sets the event date.
     *
     * @param eventDate new date
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets the ticket price.
     *
     * @return the price
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ticket price.
     *
     * @param ticketPrice new price
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Gets the number of available tickets.
     *
     * @return available tickets
     */
    public int getAvailableTickets() {
        return availableTickets;
    }

    /**
     * Sets the available tickets.
     *
     * @param availableTickets new amount
     */
    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    /**
     * Decreases the available tickets by one when someone buys one.
     */
    public void decreaseTickets() {
        this.availableTickets--;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + eventId + ", name=" + eventName + ", date=" + eventDate + ", price=" + ticketPrice + ", tickets=" + availableTickets + '}';
    }

}
