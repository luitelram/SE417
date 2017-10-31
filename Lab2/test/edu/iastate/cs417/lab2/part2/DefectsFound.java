package edu.iastate.cs417.lab2.part2;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.iastate.cs417.lab2.demo.Counter;

/**
 * 
 * non-parameterized junit test class 
 * @author Ram Luitel
 *
 */
public class DefectsFound {
	 private Counter counter;

	 /*
	  * When the input is longer then 10 character and the 
	  * last character is lower case o the out put is wrong
	  * it is supposed to be 1 but actual out put is -1
	  */
	@Test
	public void testWhenCharacterIsMoreThen10() {
		counter = new Counter();
		
		int count = counter.countOs("abcdefghijo");
		System.out.println("count should be 1 but it is "+ count);
		assertTrue("Error, counter is less then 1 ",  count  < 1);
		
	}

}
