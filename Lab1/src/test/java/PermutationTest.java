
/**
 * Test class for Permutation class 
 * @author Ram Luitel
 */
import static org.junit.Assert.*;
import org.junit.Test;

import ram.lab1.Permutation;

public class PermutationTest {

	Permutation fac1 = new Permutation();

	/*
	 * One passes whenever the fault is not reached In this test case factorial
	 * of 0 will not reach the error in source code and the result correct
	 * because it return 1 by executing first if condition
	 */
	@Test
	public void testFactorialOfZero() {
		assertEquals(1, fac1.factorial(0));
	}

	/*
	 * One passes only if the fault is reached, but does not infect In this test
	 * case factorial of 1 is 1. This condition does reached the error in source
	 * code but the out come is still correct
	 */
	@Test
	public void testFactorialOfOne() {
		assertEquals(1, fac1.factorial(1));
	}

	/*
	 * One passes only if the fault is reached, infects, but does not propagate
	 * In this test case I test the Permutation method that call the factorial
	 * method which has error. I am finding the permutation of P(1,0) which is
	 * 1. The code execution does reach the error code and it is infected but
	 * does not propagate
	 */
	@Test
	public void testPermutation0fOnebyZero() {
		assertEquals(1, fac1.permutation(1, 0));
		
		// when the error is reach the flag reachError should be true;
		assertEquals(true, fac1.getReachError());

	}

	/*
	 * One passes only if the fault is reached, infects, and propagates – i.e.,
	 * you detect a failure in the output In this test case I test the
	 * Permutation method that call the factorial method which has error. I am
	 * calculating the Permutation of P(8,5) = 6720, but since it the error is
	 * reached, infected, and propagates the result is just 6
	 */
	@Test
	public void testPermutationofEigntByFive() {

		assertEquals(6, fac1.permutation(8, 5));    // should be 6720
		
		// when the error is reach the flag reachError should be true;
		assertEquals(true, fac1.getReachError());

	}

	/*
	 * One passes only if the fault is not reached and the result is correct In
	 * this condition the permutation of p(0,0) =1 which is correct output.
	 * 
	 */
	@Test
	public void testPermutationOfZeroByZero() {
		assertEquals(1, fac1.permutation(0, 0));
	}

}
