/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.uptc.bankatm.test;

import com.uptc.bankatm.exceptions.IncorrectKeyException;
import com.uptc.bankatm.exceptions.InsufficientBalanceException;
import com.uptc.bankatm.exceptions.InvalidAmountException;
import com.uptc.bankatm.models.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author sergiorojas
 */
public class BankAccounTest {

    BankAccount account;

    public BankAccounTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        account = new BankAccount("001", "1234", 500);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void validateUserAccount() {
        assertEquals("001", account.getAccountNumber());
        assertEquals(500.01, account.getBalance(), 0.1);
    }

    @ParameterizedTest(name = "depositar {0} debe lanzar excepción")
    @CsvSource({
        "550, 1050, ",
        "700, 1200",
        "23.45, 523.45"
    })
    public void depositBankAccount(double amount, double result) {
        account.deposit(amount);
        assertEquals(result, account.getBalance());
    }

    @Test
    @DisplayName("Retirar más del saldo lanza InsufficientBalanceException")
    void withdrawMoreThanBalance() {
        System.out.println("* UtilsJUnit3Test: test method 4 - checkExpectedException()");
        assertThrows(InsufficientBalanceException.class,
                () -> account.withdraw(1000, "1234"));
    }

    @Test
    public void withDrawBankAccount() throws IncorrectKeyException, InsufficientBalanceException {
        IncorrectKeyException e = assertThrows(IncorrectKeyException.class,
                () -> account.withdraw(100, "0000"));
        assertNotNull(e);
        account.withdraw(200, "1234");
        assertEquals(300, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 120, 10, 0, -1})
    void withdrawWithWrongKeyThrowsIncorrectKeyException(double amount) {
        assertThrows(IncorrectKeyException.class,
                () -> account.withdraw(amount, "0000"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {100000, 30000000})
    void withdrawMoreThanBalanceThrowsInsufficientBalanceException(double amount) {
        InsufficientBalanceException e = assertThrows(InsufficientBalanceException.class,
                () -> account.withdraw(amount, "1234"));
        assertNotNull(e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1400, -1})
    void withdrawWithInvalidAmountThrowsInvalidAmountException(double amount) {
        assertThrows(InvalidAmountException.class,
                () -> account.withdraw(amount, "1234"));
    }

    @Test
    void depositWithInvalidAmountThrowsInvalidAmountException() {
        assertThrows(InvalidAmountException.class,
                () -> account.deposit(-300));
    }

}
