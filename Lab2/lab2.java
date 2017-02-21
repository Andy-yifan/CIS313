import java.util.Scanner;

public class lab2 {
	public static void main(String[] args) throws Exception {

		// Loop over the Scanner
		// Split each line into the task and the corresponding number (if one
		// exists)
		// Depending on what the input task was, preform the corresponding
		// function
		// (ie) insert, delete, find, inoder, preorder, or postorder
		// Hint: Use a switch-case statement

		// Don't forget to close your Scanner!
		Scanner scan = new Scanner(System.in);
		BST bst = new BST<>();

		String num = scan.nextLine();
		String str = null;
		int n = 0;
		for (int i = 0; i < Integer.parseInt(num); ++i) {
			String sum = scan.nextLine();
			String[] line = sum.split(" ");

			if (line.length > 1) {

				str = line[0];
				n = Integer.parseInt(line[1]);
			} else {
				str = line[0];
			}

			switch (str) {
			case "insert":
				bst.insert(n);
				break;

			case "delete":
				bst.delete(n);
				break;

			default:
				bst.traverse(str, bst.getRoot());
				System.out.println("");
				break;

			}

		}

		scan.close();

	}
}
