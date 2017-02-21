import java.util.Scanner;

public class lab0 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numProblems = scanner.nextInt();
		for (int i = 0; i < numProblems; ++i) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int g = gcd(a, b);
			int l = lcm(a, b);
			System.out.println(g + " " + l);
		}
	}

	public static int gcd(int a, int b) {
		// Find the greatest common divisor of a and b
		// Hint: Use Euclids Algorithm
		if (a > b)
			if (a % b == 0)
				return b;
			else
				return gcd(b, a % b);
		else if (b % a == 0)
			return a;
		else
			return gcd(a, b % a);

	}

	public static int lcm(int a, int b) {
		// Find the least common multiple of a and b
		// Hint: Use the gcd of a and b
        // since lcm(a,b) * gcd(a,b) == |a*b|
		if (a >= 0 && b >= 0) {
		    long gcd = lcm(-a, -b);
		    return (int) ((a * b) / gcd);
		  }
		  a = -a;
		  b = -b;
		  if (b == 0)
		    return a;
		  else
		    return lcm(-b, -(a%b)); 
	}
}
