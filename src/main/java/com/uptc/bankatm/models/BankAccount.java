/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uptc.bankatm.models;

import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;

/**
 *
 * @author sergiorojas
 */
public class BankAccount {

    private String accountNumber;
    private String passWord;
    private double balance;

    public BankAccount(String accountNumber, String passWord, double balance) {
        this.accountNumber = accountNumber;
        this.passWord = passWord;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "accountNumber=" + accountNumber + ", passWord=" + passWord + ", balance=" + balance + '}';
    }

public void deposit(double amount) {
    if (amount <= 0) {
        throw new InvalidAmountException("El monto debe ser mayor que cero.");
    }
    balance += amount;
}

public void withdraw(double amount, String key)
        throws IncorrectKeyException, InsufficientBalanceException {
    if (!passWord.equals(key)) {
        throw new IncorrectKeyException("La clave ingresada es incorrecta.");
    }
    if (amount <= 0) {
        throw new InvalidAmountException("El monto debe ser mayor que cero.");
    }
    if (amount > balance) {
        throw new InsufficientBalanceException(
                "Saldo insuficiente. Saldo disponible: " + balance);
    }
    balance -= amount;
}

}
