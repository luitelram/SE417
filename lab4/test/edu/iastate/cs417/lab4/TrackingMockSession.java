package edu.iastate.cs417.lab4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * 
 * @author Ram Luitel
 * Credit: Given Template and partial implementation 
 *
 */
public class TrackingMockSession implements ValidatingStockServiceSession {

	private StockServiceSession delegate;
	private Map<String, Double> currentPrices = null;
	private long maxSessionRequests;
	private long maxSessionDuration;
	private boolean isLogin;
	private long expireTime;
	private int requestCounter = 0;

	public TrackingMockSession(int maxRequests, long maxDuration, Map<String, Double> prices) {
		maxSessionRequests = maxRequests;
		maxSessionDuration = maxDuration;
		delegate = mock(StockServiceSession.class);
		currentPrices = prices;
		requestCounter = 0;
		isLogin = false;
		expireTime = System.currentTimeMillis() + maxSessionDuration;
		configureMock();
	}

	@Override
	public boolean login(String username, String password) {
		return delegate.login(username, password);
	}

	@Override
	public double getCurrentPrice(Stock stock) throws RequestLimitExceededException {
		return delegate.getCurrentPrice(stock);
	}

	@Override
	public boolean validate() {
		// TODO: this method examines the Mockito history of the mockito object
		// to determine if the way it was called conforms to the protocol
		// described in the lab instructions.
		// this is where you put "verify() and InOrder()" stuff.

		InOrder order = inOrder(delegate);
		order.verify(delegate).login("Tom", "123");
		order.verify(delegate, atLeast(0)).getCurrentPrice(any(Stock.class));
		return true;
	}
	
	private void configureMock() {
		// TODO: this method configures the Mockito object so that it
		// expects login and getCurrentPrice calls and it knows how to
		// return appropriate responses to both.
		// this is where you put "when(...)" stuff.
		
		when(delegate.login(any(String.class), any(String.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws IllegalArgumentException {
				Object[] obj = invocation.getArguments();
				String userName = (String) obj[0];
				String password = (String) obj[1];
				if (userName.equals("Tom") && password.equals("123")) {
					isLogin = true;
					return true;
				} else
					throw new IllegalArgumentException();
			}
		});
		when(delegate.getCurrentPrice(any(Stock.class))).thenAnswer(new Answer<Double>() {
			public Double answer(InvocationOnMock invocation)
					throws IndexOutOfBoundsException, VerificationInOrderFailure, TimeoutException {
				requestCounter++;
				long currentTotalTime  = System.currentTimeMillis();
				if (!isLogin) {
					throw new VerificationInOrderFailure("Varifcation fail because you are not login yet");
				} else if (requestCounter > 14) {
					throw new IndexOutOfBoundsException("Reqested more then 15 requests/session");
				} else if (currentTotalTime < expireTime) {
					Object[] obj = invocation.getArguments();
					Stock stock = (Stock) obj[0];
					return currentPrices.get(stock.getSymbol());
				} else {
					
					throw new TimeoutException("Exceed session duration: 15 second per sesssion)");

				}
			}
		});

	}

}