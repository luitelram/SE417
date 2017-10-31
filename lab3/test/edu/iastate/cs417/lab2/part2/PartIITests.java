package edu.iastate.cs417.lab2.part2;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import edu.iastate.cs417.lab2.part1.Counter;
import edu.iastate.cs417.lab2.util.FileUtil;

/**
 * 
 * The parameterized test mechanism for Part II goes in this class. Do not try
 * to get the name of the test set file from the keyboard. Remember, this is an
 * automated test suite; it needs to run without user intervention. Thus, the
 * file name needs to always be known to the test. credit: Lab-2 template
 * 
 * @author Ram Luitel
 *
 */

@RunWith(Parameterized.class)
public class PartIITests {
	private static final String filename = "lab3testdata.txt";

	private static int testId;
	private String input;
	private Integer expectedResult;
	private Counter counter;

	@Before
	public void initialize() {
		counter = new Counter();
	}

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in testData() method

	public PartIITests(String input, String expectedResult) {
		this.input = input;
		this.expectedResult = Integer.valueOf(expectedResult);
	}

	/*
	 * Setup for test input read from file
	 */

	@Parameterized.Parameters
	public static Collection getTestSet() {
		return Arrays.asList(FileUtil.getParametersFromFile(filename, 2));
	}

	/*
	 * Test the number of times 'O' or 'o' appear in word
	 */
	@Test
	public void testCounter() {
		int result = counter.countOs(input);
		System.out.format("test %02d %-14s %2d%n", testId++, input, result);
		assertEquals(Integer.valueOf(expectedResult), counter.countOs(input));
	}
}