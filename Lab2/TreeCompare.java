import java.util.Scanner;

public class TreeCompare {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		BST bst1 = new BST<>();
		BST bst2 = new BST<>();
		String num = scan.nextLine();
		String str = null;
		String str2 = null;
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
				bst1.insert(n);
				break;

			case "delete":
				bst1.delete(n);
				break;

			default:
				bst1.traverse(str, bst1.getRoot());
				System.out.println("");
				break;

			}

		}
		String num2 = scan.nextLine();
		for (int i = 0; i < Integer.parseInt(num2); ++i) {
			String sum = scan.nextLine();
			String[] line = sum.split(" ");

			if (line.length > 1) {

				str2 = line[0];
				n = Integer.parseInt(line[1]);
			} else {
				str2 = line[0];
			}

			switch (str2) {
			case "insert":
				bst2.insert(n);
				break;

			case "delete":
				bst2.delete(n);
				break;

			default:
				bst2.traverse(str2, bst2.getRoot());
				System.out.println("");
				break;
			}

		}
		scan.close();
		if (treeCompare(bst1.getRoot(), bst2.getRoot())) {
			System.out.println("The trees have the same shape.");
		} else {
			System.out.println("The trees do not have the same shape.");
		}

	}

	public static boolean treeCompare(Node root1, Node root2) {
		if(root1 == root2){
			return true;
		}
		if(root1 == null || root2 == null){
			return false;
		}
		return (	(root1.getData() == root2.getData()) &&
					
					(treeCompare(root1.getLeftChild(),root2.getLeftChild())) &&
					(treeCompare(root1.getRightChild(), root2.getRightChild()))
				);

	}

}
