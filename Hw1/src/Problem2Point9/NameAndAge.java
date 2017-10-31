/**
 * 
 */
package Problem2Point9;

/**
 * @author Ram Luitel
 *
 */
public class NameAndAge {
	
	private String name;
	private int age;
	
	public NameAndAge(String name, int age){
		
		if(name.equals("") || name == null || (age <=0 ||age >=120)){
			System.out.println("Invalid input");
			return;
		}
		 this.name = name;
	     this.age= age;
				
	}
	public String getName(){
		if( name.length()>20)
			name = name.substring(0, 20);
		return name;
	}
	
	public int getAge(){
		
		return age;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NameAndAge me = new NameAndAge("Ramgvhvghg hgh gjgfdgfdgfjhgjhv",2);
		System.out.println(me.getAge());
		System.out.println(me.getName());

	}

}
