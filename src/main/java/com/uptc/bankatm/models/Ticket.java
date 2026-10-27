/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.models;

/**
 * A ticket purchased by someone for an event.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class Ticket {

    private String ticketId;
    private String eventId;
    private String eventName;
    private String buyerAccountNumber;
    private double pricePaid;
    private String purchaseDate;

    /**
     * Creates a new Ticket.
     *
     * @param ticketId unique id for this ticket
     * @param eventId the event id
     * @param eventName the event name
     * @param buyerAccountNumber account of the buyer
     * @param pricePaid how much was paid
     * @param purchaseDate when it was purchased
     */
    public Ticket(String ticketId, String eventId, String eventName, String buyerAccountNumber, double pricePaid, String purchaseDate) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.buyerAccountNumber = buyerAccountNumber;
        this.pricePaid = pricePaid;
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets the ticket id.
     *
     * @return the ticket id
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Sets the ticket id.
     *
     * @param ticketId new id
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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
     * @param eventId new event id
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
     * Gets the buyer account number.
     *
     * @return the account number
     */
    public String getBuyerAccountNumber() {
        return buyerAccountNumber;
    }

    /**
     * Sets the buyer account number.
     *
     * @param buyerAccountNumber new account
     */
    public void setBuyerAccountNumber(String buyerAccountNumber) {
        this.buyerAccountNumber = buyerAccountNumber;
    }

    /**
     * Gets the price paid.
     *
     * @return the price
     */
    public double getPricePaid() {
        return pricePaid;
    }

    /**
     * Sets the price paid.
     *
     * @param pricePaid new price
     */
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    /**
     * Gets the purchase date.
     *
     * @return the date
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the purchase date.
     *
     * @param purchaseDate new date
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", event=" + eventName + ", buyer=" + buyerAccountNumber + ", paid=" + pricePaid + ", date=" + purchaseDate + '}';
    }

}
