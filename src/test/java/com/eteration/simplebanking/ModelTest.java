package com.eteration.simplebanking;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

	@Test
	public void testDepositTransaction() throws InsufficientBalanceException {
		Account account = new Account("Kerem Karaca", "17892");
		account.post(new DepositTransaction(100.0));
		assertEquals(100.0, account.getBalance(), 0.01);
	}

	@Test
	public void testWithdrawalTransaction() throws InsufficientBalanceException {
		Account account = new Account("Kerem Karaca", "17892");
		account.post(new DepositTransaction(100.0));
		account.post(new WithdrawalTransaction(60.0));
		assertEquals(40.0, account.getBalance(), 0.01);
	}

	@Test
	public void testIllegalWithdrawalTransaction() throws InsufficientBalanceException {
		Account account = new Account("Kerem Karaca", "17892");
		account.post(new DepositTransaction(100.0));
		assertThrows(InsufficientBalanceException.class, () -> {
			account.post(new WithdrawalTransaction(500.0));
		});
	}
}
