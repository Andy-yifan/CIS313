public class MaxHeap<E extends Comparable> {
	private E[] myArray;
	private int maxSize;
	private int length;

	public MaxHeap(int s) {
		// Build the constructor
		setMaxSize(s);
		setLength(0);
		myArray = (E[]) new Comparable[s + 1];
	}

	// helper functions
	public E[] getArray() {
		return myArray;
	}

	public void setArray(E[] newArray) {
		//change maxsize+1
		if (newArray.length > maxSize+1) {
			return;
		}
		myArray = newArray;
		length = newArray.length - 1;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int ms) {
		maxSize = ms;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int l) {
		length = l;
	}

	

	// Other Methods
	
	public void insert(E data) {

		// Insert an element into your heap.

		// When adding a node to your heap, remember that for every node n,
		// the value in n is less than or equal to the values of its children,
		// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the
		// left child.)
		// (Additionally, that child is farthest left possible.)
		length ++;
    	int pos = getLength();
    	int parent  = length / 2;
    	myArray[length] = data;
    	//build maxheap by up bubbling
    	while((pos > 1) && (myArray[parent].compareTo(myArray[pos]) < 0 )){
    		myArray[pos] = myArray[parent];
    		myArray[parent] = data;
    		pos = parent;
    		parent  = pos / 2;
    	}
    	

	}

	public Comparable<E> maximum() {
		// return the minimum value in the heap
        // root is max for maxheap
		return myArray[1];

	}

	public Comparable<E> extractMax() {
		// remove and return the maximum value in the heap
		if(length<1){
			return null;
		}
		Comparable<E> maxval = maximum();
		myArray[1] = myArray[getLength()];
		setLength(getLength() - 1);
        //heapify again to resize
		heapify(1);
		return maxval;

	}

	// method to find left child for this node
		private int leftChild(int pos) {

			return (pos * 2);
		}

		// method to find right child for this node
		private int rightChild(int pos) {

			return (pos * 2 + 1);
		}
	public void heapify(int i) {
		
		int largest = i;
		//find largest,if left child < length, i is not leaf node
		if(leftChild(i)<=length&& (myArray[leftChild(i)].compareTo(myArray[largest]) > 0)){
			largest = leftChild(i);
		}else {
			largest = i;
		}
		if(rightChild(i)<=length&& (myArray[rightChild(i)].compareTo(myArray[largest]) > 0)){
			largest = rightChild(i);
		}
		//if i is not biggest, switch i with the largest
		if(i != largest){
			E temp = myArray[i];
			myArray[i] = myArray[largest];
			myArray[largest] = temp;
			//after switch ,check again
			heapify(largest);
		}
	}



	public void buildHeap(E[] newArr) {
		// used for the extra credit
        // cover the old array, rebuit
		setArray(newArr);
		for(int i = length/2; i > 0; i--){
			heapify(i); 
		}
	}
}
