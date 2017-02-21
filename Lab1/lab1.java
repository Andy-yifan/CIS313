import java.util.Scanner;

public class lab1 {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		
		// Loop over the scanner's input
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		
		// Close the Scanner		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 0; i < num; i++){
			if(isPalindrome(sc.next())){
			    System.out.println("This is a Palindrome.");
			}else{
			    System.out.println(" Not a Palindrome.");
			}
		}
		sc.close();
	}
	
	public static boolean isPalindrome(String s){
	
		// Create a stack and a Queue of chars that represents the passed in string

		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not	
		Stack<Character> st = new Stack<Character>();
		Queue<Character> qu = new Queue<Character>();
		for(int i = 0; i < s.length();i++){
			qu.enqueue(s.charAt(i));
			st.push(s.charAt(i));
			
		}
		while(!qu.isEmpty()&!st.isEmpty()){
			if(qu.dequeue().getData() != st.pop().getData()){
			return false;
		}else{
			return true;
		}
		}return true;
		
		
		
	}
	
	public static boolean isPalindromeEC(String s){
		Stack<Character> st = new Stack<Character>();
		TwoStackQueue<Character> qu = new TwoStackQueue<Character>();
		for(int i = 0; i < s.length();i++){
			qu.enqueue(s.charAt(i));
			st.push(s.charAt(i));
			
		}
		while(!qu.isEmpty()&!st.isEmpty()){
			if(qu.dequeue().getData() != st.pop().getData()){
			return false;
		}else{
			return true;
		}
		}return true;
	}
}
