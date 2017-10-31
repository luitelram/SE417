package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent;

import edu.iastate.cs417.lab2.util.FileUtil;
import junit.framework.AssertionFailedError;

/**
 * 
 * @author Ram Luitel
 * Credit: Given template for SE417
 * Test class that test different input protfolio inputs
 *
 */
public class TestPortfolioManager {

	public static final double tolerance = .0005;
	Map<String,Double> currentPrices = new HashMap<String, Double>();
	StockServiceSessionFactory factory = null; 
	Portfolio holdings = null; 

	@Before
	public void setup(){
		currentPrices = PMTestUtil.loadPrices("current-prices.txt");
		holdings = PMTestUtil.loadPortfolio("portfolio-3.txt");
		MockSessionFactory mockFactory = new MockSessionFactory();
		mockFactory.setCurrentPrices(currentPrices);
		//just a reminder that the production code expects an unadorned factory. 
		factory = mockFactory;		
	}
	

	@Test
	public void test() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+ result);
		assertEquals(92120.24, result, tolerance );
	}
	
	//TODO: add other tests to determine if the portfolio manager returns 
	//           correct values and handles sessions correctly with various size
	//           portfolios. 
	
	
	/**
	 * Test Portfolio10
	 */
	@Test
	public void testPortfolio10() {
		PortfolioManager pm = new PortfolioManager();
		holdings = PMTestUtil.loadPortfolio("portfolio-10.txt");
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
		assertEquals(871883.00, result, tolerance );
	}
	
	/**
	 * Test Portfolio > 15
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPortfolio20() {
		PortfolioManager pm = new PortfolioManager();
		holdings = PMTestUtil.loadPortfolio("portfolio-99.txt");
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
	}

	
	/**
	 * Test Portfolio0
	 * No file exixt.
	 */
	@Test(expected = NullPointerException.class)
	public void testPortfolio0() {
		PortfolioManager pm = new PortfolioManager();
		holdings = PMTestUtil.loadPortfolio("portfolio-0.txt");    // There is such file called portfolio-0.txt
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
		assertEquals(0, result, tolerance );
	}


}
