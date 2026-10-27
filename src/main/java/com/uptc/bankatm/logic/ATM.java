/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.logic;
import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.models.BankAccount;

/**
 *
 * @author sergiorojas
 */
public class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }
    
    
public void withdraw(double amount, String key)
        throws InsufficientBalanceException, IncorrectKeyException {
    account.withdraw(amount, key);
    System.out.println("Retiro exitoso. Nuevo saldo: " + account.getBalance());
}

public void deposit(double amount) {
    account.deposit(amount);
    System.out.println("Depósito exitoso. Nuevo saldo: " + account.getBalance());
}

}
