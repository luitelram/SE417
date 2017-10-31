/**
 * Class that calculate the factorial and permutation 
 * @author Ram Luitel
 */
package ram.lab1;

public class Permutation {

	private boolean reachError = false;
	/**
	 * There is error in this method at line number 25
	 * @param n number to calculate factorial of 
	 * @return factorial of n
	 */
	public int factorial(int n){
		
		int factor = 0;
		if(n==0)
			return 1;
		else{
			
			for(int i = n; i>0; i--){
				if(i >=1)
					reachError = true;     // error reach at this point when number is greater then 1
				factor +=i ;				// Need to be result *= i
			}   
		}
		return factor;
	}
	/**
	 *  Calculate permutation 
	 * @param n distinct object
	 * @param r distinct sample
	 * @return permutation
	 */
	public int permutation(int n, int r){
		return factorial(n)/factorial(n-r);
			
	}
	public boolean getReachError(){
		return reachError;
	}
}
