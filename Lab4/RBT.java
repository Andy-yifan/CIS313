import java.util.LinkedList;
import java.util.Queue;

public class RBT<E extends Comparable<E>> {
	private Node<E> root;
	private Node<E> nil;

	public RBT() {
		nil = new Node<E>(null);
		nil.setColor('b');
		root = nil;

	}

	public Node<E> getRoot() {
		return root;
	}

	public void insert(E data) {
		// Preform a regular insert
		// Check to make sure the tree remains an RBT tree
		Node<E> z = new Node<E>(data);
		Node<E> y = nil;
		Node<E> x = root;
		while (x != nil) {
			y = x;
			if (z.getData().compareTo(x.getData()) < 0) {
				x = x.getLeftChild();
			} else {
				x = x.getRightChild();
			}
		}
		z.setParent(y);
		if (y == nil) {
			root = z;
		} else {
			if (z.getData().compareTo(y.getData()) < 0) {
				y.setLeftChild(z);
			} else {
				y.setRightChild(z);
			}
		}
		z.setLeftChild(nil);
		z.setRightChild(nil);
		z.setColor('r');
		fixInsert(z);
	}

	private void fixInsert(Node<E> z) {
		// TODO Auto-generated method stub
		while (z.getParent().getColor() == 'r') {
			// parent of z is left of it's parent
			if (z.getParent() == z.getParent().getParent().getLeftChild()) {
				Node<E> y = z.getParent().getParent().getRightChild();
				if (y.getColor() == 'r') {
					z.getParent().setColor('b');
					y.setColor('b');
					z.getParent().getParent().setColor('r');
					z = z.getParent().getParent();
				} else {
					// z is right of parent
					if (z == z.getParent().getRightChild()) {
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor('b');
					z.getParent().getParent().setColor('r');
					rightRotate(z.getParent().getParent());
				}
			} else {
				Node<E> y = z.getParent().getParent().getLeftChild();
				if (y.getColor() == 'r') {
					z.getParent().setColor('b');
					y.setColor('b');
					z.getParent().getParent().setColor('r');
					z = z.getParent().getParent();
				} else {
					if (z == z.getParent().getLeftChild()) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor('b');
					z.getParent().getParent().setColor('r');
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor('b');
	}

	public Node<E> search(E data) {
		Node<E> temp = root;
		while (temp != nil) {
			if (temp.getData().compareTo(data) == 0) {
				return temp;
			} else if (temp.getData().compareTo(data) > 0) {
				temp = temp.getLeftChild();
			} else if (temp.getData().compareTo(data) < 0) {
				temp = temp.getRightChild();
			}
		}
		return null;
	}

	public Node<E> minimum(Node<E> x) {
		if (x.getLeftChild() != nil) {
			return minimum(x.getLeftChild());
		} else {

			return x;
		}
	}

	public void delete(E data) {
		// Preform a regular delete
		// Check to make sure the tree remains an RBT tree
		Node<E> z = search(data);
		Node<E> x = nil;
		Node<E> y = z;
		Character yColor = y.getColor();
		if (z.getLeftChild() == nil) {
			x = z.getRightChild();
			transplant(z, z.getRightChild());
		} else if (z.getRightChild() == nil) {
			x = z.getLeftChild();
			transplant(z, z.getLeftChild());
		} else {
			y = minimum(z.getRightChild());
			yColor = y.getColor();
			x = y.getRightChild();
			if (y.getParent() == z) {
				x.setParent(y);
			} else {
				transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			}
			transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
			y.setColor(z.getColor());
		}
		if (yColor == 'b') {
			fixDelete(x);
		}
	}

	public void transplant(Node<E> u, Node<E> v) {
		if (u.getParent() == nil) {
			root = v;
		} else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} else {
			u.getParent().setRightChild(v);
		}
		if (v != null) {
			v.setParent(u.getParent());
		}

	}

	private void fixDelete(Node<E> x) {
		// TODO Auto-generated method stub
		while (x != root && x.getColor() == 'b') {

			if (x == x.getParent().getLeftChild()) {
				Node<E> brother = x.getParent().getRightChild();
				if (brother.getColor() == 'r') {
					// case 1
					brother.setColor('b');
					x.getParent().setColor('r');
					leftRotate(x.getParent());
					brother = x.getParent().getRightChild();
				}

				if (brother.getLeftChild().getColor() == 'b' && brother.getRightChild().getColor() == 'b') {
					// case 2
					brother.setColor('r');
					x = x.getParent();
				} else {
					if (brother.getRightChild().getColor() == 'b') {
						// case 3
						brother.getLeftChild().setColor('b');
						brother.setColor('r');
						rightRotate(brother);
						brother = x.getParent().getRightChild();

					}
					// case 4
					brother.setColor(x.getParent().getColor());
					x.getParent().setColor('b');
					brother.getRightChild().setColor('b');
					leftRotate(x.getParent());
					x = root;
				}
			} else {
				Node<E> brother = x.getParent().getLeftChild();

				if (brother.getColor() == 'r') {
					brother.setColor('b');
					x.getParent().setColor('r');
					rightRotate(x.getParent());
					brother = x.getParent().getLeftChild();
				}
				if (brother.getRightChild().getColor() == 'b' && (brother.getLeftChild().getColor() == 'b')) {
					brother.setColor('r');
					x = x.getParent();
				} else {
					if (!(brother.getLeftChild().getColor() == 'b')) {
						brother.getRightChild().setColor('b');
						brother.setColor('r');
						leftRotate(brother);
						brother = x.getParent().getLeftChild();
					}

					brother.setColor(x.getParent().getColor());
					x.getParent().setColor('b');
					brother.getLeftChild().setColor('b');
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor('b');

	}

	public void traverse(String order, Node<E> top) {
		// Preform a preorder traversal of the tree
		if (top != null) {
			if (top.getData() != null) {
				System.out.print(top.getData().toString() + " ");
				traverse("preorder", top.getLeftChild());
				traverse("preorder", top.getRightChild());
			}

		}
	}

	public void rightRotate(Node<E> y) {
		Node<E> x = y.getLeftChild();
		y.setLeftChild(x.getRightChild());
		x.getRightChild().setParent(y);
		x.setParent(y.getParent());
		if (y.getParent() == nil || y.getParent() == null) {
			root = x;
		} else if (y == y.getParent().getLeftChild()) {
			y.getParent().setLeftChild(x);
		} else {
			y.getParent().setRightChild(x);
		}
		x.setRightChild(y);
		y.setParent(x);
	}

	public void leftRotate(Node<E> x) {
		Node<E> y = x.getRightChild();
		x.setRightChild(y.getLeftChild());
		y.getLeftChild().setParent(x);
		y.setParent(x.getParent());
		if (x.getParent() == nil || x.getParent() == null) {
			root = y;
		} else if (x == x.getParent().getLeftChild()) {
			x.getParent().setLeftChild(y);
		} else {
			x.getParent().setRightChild(y);
		}
		y.setLeftChild(x);
		x.setParent(y);
	}

	// HINT: You may want to create extra methods such as fixDelete or fixInsert
    //sourse from iteye.com/blog/2259886
	public  boolean isRBT(Node<E> a){
		Node<E> root = a;  
        if(!(root.getColor() == 'b')){  
            return false;  
        }  
        //store the node
        Queue<Node<E>> q = new LinkedList<Node<E>>();  
        // store the leaf
        Queue<Node<? extends E>> qLeaf = new LinkedList<Node<? extends E>>();  
        q.offer(root);  
        while (!q.isEmpty()) {  
            Node<E> temp = q.poll();  
            if(temp.getData() == null){  
                qLeaf.offer((Node<? extends E>)temp);  
            }  
            if (temp.getLeftChild() != null) {  
                q.offer(temp.getLeftChild());  
            }  
            if (temp.getRightChild() != null) {  
                q.offer(temp.getRightChild());  
            }  
        }
        //num for black node
        int num = 0;
        // check if need to reset the number of black node
        boolean isNum = true;  
        // check if is rbt
        while(!qLeaf.isEmpty()){  
            Node<? extends E> temp = qLeaf.poll();  
           //temp leaf node to root's black node num
            if(!(temp.getColor() =='b')){  
                return false;  
            }  
            int number = 0;  
            while(temp != null){  
                if(temp.getParent() != null){  
                    // if two red nodes as parent and child
                    if(!(temp.getColor() == 'b') && !(temp.getParent().getColor() =='b')){  
                        return false;  
                    }  
                }  
                if(temp.getColor() == 'b'){  
                    if(isNum){  
                        num ++;  
                    }  
                    number ++;  
                }  
                temp = temp.getParent();  
            }  
            isNum = false;  
            if(number != num){  
                return false;  
            }  
        }  
        return true;  
	}
}
