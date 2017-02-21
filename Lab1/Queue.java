
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		head = null;
		tail = null;
		
	}
	
	public void enqueue(E newData){
		//not sure
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		Node<E> newNode=new Node<E>(newData, null);
		if(this.isEmpty())
		{
			this.head = newNode;
			this.tail = newNode;
        } else{
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        
	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		if(this.isEmpty()){
			return null;
		}else{
			Node<E> temp = this.head;
			head = head.getNext();
			return  temp;
		}
		

	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		return head==null;
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		Node<E> current = this.head;
		while(current != null&& this.tail!= null){
			System.out.println(current.getData());
			current=current.getNext();
		}
		
	}

	}

