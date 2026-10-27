/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.uptc.bankatm;

import com.uptc.bankatm.exceptions.EventNotFoundException;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.exceptions.NoTicketsAvailableException;
import com.uptc.bankatm.logic.ATM;
import com.uptc.bankatm.logic.EventManager;
import com.uptc.bankatm.models.BankAccount;
import com.uptc.bankatm.models.Event;
import com.uptc.bankatm.models.Ticket;

/**
 *
 * @author sergiorojas
 */
public class BankATM {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("001", "1234", 500000);
        ATM atm = new ATM(account);

        System.out.println("Caso 3: monto negativo");
        tryWithdraw(atm, -50, "1234");

        // eventos y boletas
        System.out.println("\n========================================");
        System.out.println("  GESTION DE EVENTOS Y BOLETAS");
        System.out.println("========================================\n");

        EventManager manager = new EventManager();

        manager.addEvent(new Event("E01", "Concierto Rock en Tunja", "2026-11-15", 50000, 3));
        manager.addEvent(new Event("E02", "Partido Patriotas vs Millos", "2026-11-20", 30000, 5));
        manager.addEvent(new Event("E03", "Obra de Teatro UPTC", "2026-12-01", 15000, 2));

        manager.printEvents();

        System.out.println("\n--- Comprando boleta para el concierto ---");
        tryBuyTicket(manager, "E01", account, "1234");

        System.out.println("\n--- Comprando boleta para el partido ---");
        tryBuyTicket(manager, "E02", account, "1234");

        System.out.println("\n--- Intentando con clave incorrecta ---");
        tryBuyTicket(manager, "E01", account, "0000");

        System.out.println("\n--- Intentando evento que no existe ---");
        tryBuyTicket(manager, "E99", account, "1234");

        System.out.println("");
        manager.printSoldTickets();

        System.out.println("\nSaldo final de la cuenta: " + account.getBalance());
    }

    private static void tryWithdraw(ATM atm, double amount, String key) {
        try {
            atm.withdraw(amount, key);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error de saldo: " + e.getMessage());
        } catch (IncorrectKeyException e) {
            System.out.println("Error de clave: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error de monto: " + e.getMessage());
        } finally {
            System.out.println("Intento procesado.");
        }
    }

    private static void tryBuyTicket(EventManager manager, String eventId,
            BankAccount account, String key) {
        try {
            Ticket t = manager.buyTicket(eventId, account, key);
            System.out.println("Compra exitosa! " + t);
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoTicketsAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IncorrectKeyException e) {
            System.out.println("Error de clave: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error de saldo: " + e.getMessage());
        }
    }
}
