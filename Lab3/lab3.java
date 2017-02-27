import java.util.Scanner;

public class lab3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 0;
        int num = Integer.parseInt(scan.nextLine());
		
        String str = "";
		pQueue<Integer> myQueue = new pQueue<Integer>(num);
		for (int i = 0; i < num; i++) {
			if (scan.hasNextLine()) {
				String s = scan.nextLine();
				String line[] = s.split(" ");
				str = line[0];
                //
				if(str.equals("build")){
					 
				}
				else if (line.length > 1) {
                   
					n = Integer.parseInt(line[1]);
				}
				
            switch (str) {
				case "insert":
					myQueue.insert(n);
					break;

				case "print":
					myQueue.print();
					break;

				case "isEmpty":
					if (myQueue.isEmpty()) {
						System.out.println("Empty");
					}
                    else {
						System.out.println("Not Empty");
					}
					break;

				case "maximum":
					System.out.println(myQueue.maximum());
					break;

				case "extractMax":
					System.out.println(myQueue.extractMax());
					break;
				case "build":
                    //leave numbers
					String [] strArr = line[1].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                    // since the myQueue is int type, convent to int array with +1 length.
					Integer[] intArr = new Integer[strArr.length +1]; 
					
					for(int k = 1; k < intArr.length; k++){
						intArr[k] = Integer.parseInt(strArr[k-1]); 
						
					}
					myQueue.build(intArr);
					break; 
                default:
                    System.out.println("wrong input");
                    break;
				}
			}
		}
		scan.close();

	}
}
