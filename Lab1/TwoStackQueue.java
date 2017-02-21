
public class TwoStackQueue<E> {
	private Stack<E> head;
	private Stack<E> tail;
public TwoStackQueue(){
		
	 head = new Stack<E>();
     tail = new Stack<E>();
		
	}


	
	public void enqueue(E newData){
		Node<E> newNode=new Node<E>(newData, null);
		head.push(newData);
        
	}
	
	public Node<E> dequeue(){
		if (tail.isEmpty()) {
            while (!head.isEmpty()) {
               tail.push(head.pop().getData());
            }
        }
        return tail.pop();
    }
		
		

	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		Node<E> current = tail.pop();
		while(current != null){
			System.out.println(current.getData());
			current=current.getNext();
		}
		
	}

}
