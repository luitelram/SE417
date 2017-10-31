package edu.iastate.cs417.lab2.part2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.iastate.cs417.lab2.part1.Counter;

/**
 * Test case for lab3 to test counter class
 * @author Ram Luitel
 *
 */
public class Lab3NonParameterizedTest {

	private Counter counter = new Counter();
	
	/*
	 * IllegalArgumentException test
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArgument() {
		int result = counter.countOs("\t");
	}

	/*
	 * NullpointerEception test
	 */
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		int result = counter.countOs(null);
	}
	

}