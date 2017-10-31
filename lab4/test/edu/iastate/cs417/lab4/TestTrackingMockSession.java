package edu.iastate.cs417.lab4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.verification.VerificationInOrderFailure;

/**
 * 
 * @author Ram Luitel
 * Credit: and Given template for SE417
 * Test class that test TrackingMockSession
 *
 */
public class TestTrackingMockSession {

	private static final int kSymbolCol = 0;
	private static final int kPriceCol = 1;
	private static final int kQuantCol = 1;

	Map<String, Double> currentPrices = new HashMap<String, Double>();
	StockServiceSessionFactory factory = null;
	Portfolio holdings = null;
	Portfolio holdings99 = null;

	@Before
	public void setup() {
		currentPrices = PMTestUtil.loadPrices("current-prices.txt");
		holdings = PMTestUtil.loadPortfolio("portfolio-3.txt");
		holdings99 = PMTestUtil.loadPortfolio("portfolio-99.txt");
		MockSessionFactory mockFactory = new MockSessionFactory();
		mockFactory.setCurrentPrices(currentPrices);
		// just a reminder that the production code expects an unadorned
		// factory.
		factory = mockFactory;
	}

	@Test(expected = VerificationInOrderFailure.class)
	public void priceFirst() {

		StockServiceSession session = factory.getNewSession();
		session.getCurrentPrice(holdings.getHoldings().get(0));
		session.login("Tom", "123");
		factory.getNewSession();
	}

	// TODO: Add additional tests to confirm that your mock object can detect
	// order errors, authentication errors, too many request errors, etc.

	/**
	 * Test when password is invalid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void InvalidPassword() {

		StockServiceSession session = factory.getNewSession();
		session.login("Tom", "password");
		session.getCurrentPrice(holdings.getHoldings().get(0));
		factory.getNewSession();
	}

	/**
	 * Test when username is invalid
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidUsername() {

		StockServiceSession session = factory.getNewSession();
		session.login("Ram", "123");
		session.getCurrentPrice(holdings99.getHoldings().get(0));
		factory.getNewSession();
	}

	/**
	 * Test when Just login. No call to getCurrency
	 */
	@Test
	public void testJustLogin() {
		StockServiceSession session = factory.getNewSession();
		session.login("Tom", "123");
		factory.getNewSession();
	}

	/**
	 * Test when session is less then 15
	 */
	@Test
	public void testLessThan15() {

		StockServiceSession session = factory.getNewSession();
		session.login("Tom", "123");
		for (int i = 0; i < 5; i++) {
			session.getCurrentPrice(holdings99.getHoldings().get(i));
		}
	}

	/**
	 * Test when multiple login
	 */
	@Test
	public void testMultipleLogin() {

		StockServiceSession session = factory.getNewSession();
		for (int i = 0; i < 4; i++) {
			session.login("Tom", "123");
			session.getCurrentPrice(holdings99.getHoldings().get(i));
		}
		factory.getNewSession();
	}

	/**
	 * Test multiple request 
	 * @throws IndexOutOfBoundsException
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testMultipleRequest() {

		StockServiceSession sessionWithMultipleRequest = factory.getNewSession();
		sessionWithMultipleRequest.login("Tom", "123");
		for (int i = 0; i < 15; i++){
			sessionWithMultipleRequest.getCurrentPrice(holdings.getHoldings().get(0));
		}
		factory.getNewSession();
	}

	/**
	 * Test too long request time.
	 * Note to TA: Wait 15 second to pass this Test 
	 * @throws InterruptedException
	 */
	@Test(expected = TimeoutException.class)
	public void testTooLongRequest() throws InterruptedException {
		
		StockServiceSession longSession = factory.getNewSession();
		longSession.login("Tom", "123");
		longSession.getCurrentPrice(holdings.getHoldings().get(0));
		TimeUnit.SECONDS.sleep(15); 								// Wait 15 second
		longSession.getCurrentPrice(holdings.getHoldings().get(0));
		factory.getNewSession();
	}

}
