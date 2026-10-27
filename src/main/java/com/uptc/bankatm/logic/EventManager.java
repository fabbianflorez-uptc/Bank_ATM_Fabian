/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;

import com.uptc.bankatm.exceptions.EventNotFoundException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.NoTicketsAvailableException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.models.Event;
import com.uptc.bankatm.models.Ticket;
import java.util.ArrayList;

/**
 * Manages events and ticket sales.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class EventManager {

    private ArrayList<Event> events;
    private ArrayList<Ticket> soldTickets;
    private int ticketCounter;

    /**
     * Creates a new EventManager with empty lists.
     */
    public EventManager() {
        this.events = new ArrayList<>();
        this.soldTickets = new ArrayList<>();
        this.ticketCounter = 0;
    }

    /**
     * Adds a new event to the list.
     *
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Finds an event by its id.
     *
     * @param eventId the id to look for
     * @return the event if found
     * @throws EventNotFoundException if no event has that id
     */
    public Event findEvent(String eventId) throws EventNotFoundException {
        for (Event e : events) {
            if (e.getEventId().equals(eventId)) {
                return e;
            }
        }
        throw new EventNotFoundException("No se encontro el evento con id: " + eventId);
    }

    /**
     * Buys a ticket for an event using a bank account.
     * Uses BankAccount.withdraw() to charge the price.
     *
     * @param eventId the event to buy for
     * @param account the account to charge
     * @param key password of the account
     * @return the ticket that was purchased
     * @throws EventNotFoundException if event doesnt exist
     * @throws NoTicketsAvailableException if no tickets left
     * @throws IncorrectKeyException if wrong password
     * @throws InsufficientBalanceException if not enough balance
     */
    public Ticket buyTicket(String eventId, BankAccount account, String key)
            throws EventNotFoundException, NoTicketsAvailableException,
            IncorrectKeyException, InsufficientBalanceException {

        // buscamos primero para no cobrar algo que no existe :D
        Event event = findEvent(eventId);

        if (event.getAvailableTickets() <= 0) {
            throw new NoTicketsAvailableException("No hay boletas disponibles para: " + event.getEventName());
        }

        // withdraw ya revisa saldo y clave entonces lo usamos de una
        account.withdraw(event.getTicketPrice(), key);

        // si llego aca es porque pago bien, ahora si hacemos la boleta
        ticketCounter++;
        String ticketId = "TK-" + String.format("%04d", ticketCounter);
        Ticket ticket = new Ticket(ticketId, event.getEventId(), event.getEventName(),
                account.getAccountNumber(), event.getTicketPrice(), java.time.LocalDate.now().toString());

        event.decreaseTickets();
        soldTickets.add(ticket);

        return ticket;
    }

    /**
     * Gets all events. Returns a copy of the list.
     *
     * @return copy of events list
     */
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Gets all sold tickets. Returns a copy of the list.
     *
     * @return copy of sold tickets list
     */
    public ArrayList<Ticket> getSoldTickets() {
        return new ArrayList<>(soldTickets);
    }

    /**
     * Gets the total number of sold tickets.
     *
     * @return sold tickets count
     */
    public int getTotalSoldTickets() {
        return soldTickets.size();
    }

    /**
     * Prints all available events to the console.
     */
    public void printEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        System.out.println("=== Eventos disponibles ===");
        for (Event e : events) {
            System.out.println(e);
        }
    }

    /**
     * Prints all sold tickets to the console.
     */
    public void printSoldTickets() {
        if (soldTickets.isEmpty()) {
            System.out.println("No se han vendido boletas.");
            return;
        }
        System.out.println("=== Boletas vendidas ===");
        for (Ticket t : soldTickets) {
            System.out.println(t);
        }
    }

    /**
     * Returns how many tickets are left for a given event.
     *
     * @param eventId the event to check
     * @return number of available tickets
     * @throws EventNotFoundException if the event doesnt exist
     */
    public int getAvailableTicketsForEvent(String eventId) throws EventNotFoundException {
        Event event = findEvent(eventId);
        return event.getAvailableTickets();
    }

}
