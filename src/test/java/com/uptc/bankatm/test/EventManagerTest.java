/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.uptc.bankatm.test;

import com.uptc.bankatm.exceptions.EventNotFoundException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.NoTicketsAvailableException;
import com.uptc.bankatm.logic.EventManager;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.models.Event;
import com.uptc.bankatm.models.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for EventManager.
 *
 * @author Fabian Leonardo Florez - 202614060
 */
public class EventManagerTest {

    EventManager manager;
    BankAccount account;

    @BeforeEach
    public void setUp() {
        manager = new EventManager();
        account = new BankAccount("001", "1234", 500000);

        manager.addEvent(new Event("E01", "Concierto Rock", "2026-11-15", 50000, 3));
        manager.addEvent(new Event("E02", "Partido Futbol", "2026-11-20", 30000, 5));
        manager.addEvent(new Event("E03", "Teatro", "2026-12-01", 25000, 0));
    }

    @Test
    public void testEventsAdded() {
        assertEquals(3, manager.getEvents().size());
    }

    @Test
    public void testNoSoldTicketsAtStart() {
        assertEquals(0, manager.getTotalSoldTickets());
    }

    @Test
    public void testFindExistingEvent() throws EventNotFoundException {
        Event e = manager.findEvent("E01");
        assertEquals("Concierto Rock", e.getEventName());
    }

    @Test
    public void testFindNonExistingEvent() {
        assertThrows(EventNotFoundException.class,
                () -> manager.findEvent("E99"));
    }

    @Test
    public void testBuyTicketNoAvailable() {
        assertThrows(NoTicketsAvailableException.class,
                () -> manager.buyTicket("E03", account, "1234"));
    }

    @Test
    public void testBuyTicketWrongKey() {
        assertThrows(IncorrectKeyException.class,
                () -> manager.buyTicket("E01", account, "0000"));
    }

    @Test
    public void testBuyTicketInsufficientBalance() {
        BankAccount poorAccount = new BankAccount("002", "5678", 100);
        assertThrows(InsufficientBalanceException.class,
                () -> manager.buyTicket("E01", poorAccount, "5678"));
    }

    @Test
    public void testBuyTicketEventNotFound() {
        assertThrows(EventNotFoundException.class,
                () -> manager.buyTicket("E99", account, "1234"));
    }

    @Test
    public void testSuccessfulPurchase() throws Exception {
        double saldoAntes = account.getBalance();
        Ticket t = manager.buyTicket("E01", account, "1234");

        assertNotNull(t);
        assertEquals(saldoAntes - 50000, account.getBalance());
        assertEquals(2, manager.getAvailableTicketsForEvent("E01"));
        assertEquals(1, manager.getTotalSoldTickets());
    }

    @Test
    public void testTicketData() throws Exception {
        Ticket t = manager.buyTicket("E02", account, "1234");

        assertEquals("Partido Futbol", t.getEventName());
        assertEquals("001", t.getBuyerAccountNumber());
        assertEquals(30000, t.getPricePaid());
    }

    @Test
    public void testBuyAllTicketsThenFail() throws Exception {
        manager.buyTicket("E01", account, "1234");
        manager.buyTicket("E01", account, "1234");
        manager.buyTicket("E01", account, "1234");

        assertThrows(NoTicketsAvailableException.class,
                () -> manager.buyTicket("E01", account, "1234"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"E99", "XXX", "abc", "123", ""})
    public void testFindInvalidEvents(String id) {
        assertThrows(EventNotFoundException.class,
                () -> manager.findEvent(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0000", "wrong", "abcd", "12345", ""})
    public void testBuyWithWrongKeys(String wrongKey) {
        assertThrows(IncorrectKeyException.class,
                () -> manager.buyTicket("E01", account, wrongKey));
    }

    @ParameterizedTest
    @CsvSource({
        "50000, true",
        "49999, false",
        "50001, true",
        "1, false"
    })
    public void testBuyWithDifferentBalances(double balance, boolean shouldWork) {
        BankAccount testAcc = new BankAccount("099", "1234", balance);

        if (shouldWork) {
            assertDoesNotThrow(() -> manager.buyTicket("E01", testAcc, "1234"));
        } else {
            assertThrows(InsufficientBalanceException.class,
                    () -> manager.buyTicket("E01", testAcc, "1234"));
        }
    }

    @Test
    public void testGetSoldTicketsReturnsCopy() throws Exception {
        manager.buyTicket("E01", account, "1234");
        var lista = manager.getSoldTickets();
        lista.clear();
        assertEquals(1, manager.getTotalSoldTickets());
    }

    @Test
    public void testGetEventsReturnsCopy() {
        var lista = manager.getEvents();
        lista.clear();
        assertEquals(3, manager.getEvents().size());
    }

    @Test
    public void testFailedPurchaseDoesNotChangeBalance() {
        BankAccount acc = new BankAccount("003", "1234", 100);
        double saldoAntes = acc.getBalance();

        assertThrows(InsufficientBalanceException.class,
                () -> manager.buyTicket("E01", acc, "1234"));

        assertEquals(saldoAntes, acc.getBalance());
    }

    @Test
    public void testFailedPurchaseDoesNotReduceTickets() throws EventNotFoundException {
        int ticketsAntes = manager.getAvailableTicketsForEvent("E01");

        assertThrows(IncorrectKeyException.class,
                () -> manager.buyTicket("E01", account, "wrongkey"));

        assertEquals(ticketsAntes, manager.getAvailableTicketsForEvent("E01"));
    }

}
