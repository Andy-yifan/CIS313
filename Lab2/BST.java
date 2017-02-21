
public class BST<E extends Comparable<E>> {
	private Node<E> root;

	public BST() {
		root = null;
	}

	public Node<E> getRoot() {
		return root;
	}

	public void insert(E data) {

		// Find the right spot in the tree for the new node
		// Make sure to check if anything is in the tree
		// Hint: if a node n is null, calling n.getData() will cause an error
		Node<E> parent = null;
		Node<E> val = new Node<E>(data);
		Node<E> node = root;

		if (root == null) {
			root = val;
			return;
		}

		while (node != null) {

			parent = node;
			if (data.compareTo(node.getData()) < 0) {

				node = node.getLeftChild();

			} else if (data.compareTo(node.getData()) > 0) {

				node = node.getRightChild();

			} else {

				return;
			}
		}

		if (data.compareTo(parent.getData()) < 0) {

			parent.setLeftChild(val);
			val.setParent(parent);

		} else {

			parent.setRightChild(val);
			val.setParent(parent);
		}

	}

	public Node<E> find(E data) {

		// Search the tree for a node whose data field is equal to data

		Node<E> currNode = root;
		while (currNode != null && currNode.getData().compareTo(data) != 0) {
			if (data.compareTo(currNode.getData()) < 0) {
				currNode = currNode.getLeftChild();
			} else {
				currNode = currNode.getRightChild();
			}
		}
		return currNode;

	}

	public void delete(E data) throws Exception {
		// Find the right node to be deleted

		// If said node has no children, simply remove it by setting its parent
		// to point to null instead of it.
		// If said node has one child, delete it by making its parent point to
		// its child.
		// If said node has two children, then replace it with its successor,
		// and remove the successor from its previous location in the tree.
		// The successor of a node is the left-most node in the node's right
		// subtree.
		// If the value specified by delete does not exist in the tree, then the
		// structure is left unchanged.

		// Hint: You may want to implement a findMin() method to find the
		// successor when there are two children

		Node<E> findNode = find(data);
		if (findNode == null) {
			throw new Exception("The value does not exist.");
		}
		if (findNode == root) {
			if (findNode.getLeftChild() == null && findNode.getRightChild() == null) {
				root = null;
			} else if (findNode.getLeftChild() == null && findNode.getRightChild() != null) {
				root = findNode.getRightChild();
				findNode.getRightChild().setParent(root);
			} else if (findNode.getLeftChild() != null && findNode.getRightChild() == null) {
				root = findNode.getLeftChild();
				findNode.getLeftChild().setParent(root);
			} else {

				Node<E> sucessorNode = findMin(findNode);
				if (sucessorNode == root.getRightChild()) {
					sucessorNode.setLeftChild(root.getLeftChild());
					sucessorNode.getLeftChild().setParent(sucessorNode);
					sucessorNode.setParent(root.getParent());
					root = sucessorNode;
				} else {
					if (sucessorNode.getParent().getLeftChild() == sucessorNode) {
						sucessorNode.getParent().setLeftChild(null);
					} else if (sucessorNode.getParent().getRightChild() == sucessorNode) {
						sucessorNode.getParent().setRightChild(null);
					}
					sucessorNode.setLeftChild(root.getLeftChild());
					root.getLeftChild().setParent(sucessorNode);
					sucessorNode.setRightChild(root.getRightChild());
					root.getRightChild().setParent(sucessorNode);
					sucessorNode.setParent(root.getParent());
					root = sucessorNode;
				}

			}
        } else {
            Node<E> parent = findNode.getParent();
            if (findNode.getLeftChild() == null && findNode.getRightChild() == null) {
                if (findNode == parent.getLeftChild()) {
                    parent.setLeftChild(null);
                    
                } else {
                    parent.setRightChild(null);
                }
            } else if (findNode.getLeftChild() != null && findNode.getRightChild() == null) {
                if (findNode == parent.getLeftChild()) {
                    parent.setLeftChild(findNode.getLeftChild());
                    findNode.getLeftChild().setParent(parent);
                } else {
                    parent.setRightChild(findNode.getLeftChild());
                    findNode.getLeftChild().setParent(parent);
                }
            } else if (findNode.getLeftChild() == null && findNode.getRightChild() != null) {
                if (findNode == parent.getLeftChild()) {
                    parent.setLeftChild(findNode.getRightChild());
                    findNode.getRightChild().setParent(parent);
                } else {
                    parent.setRightChild(findNode.getRightChild());
                    findNode.getRightChild().setParent(parent);
                }
            } else {
                Node<E> sucessor = findMin(findNode);
                if (findNode == parent.getLeftChild()) {
                    parent.setLeftChild(sucessor);
                } else {
                    parent.setRightChild(sucessor);
                }
                sucessor.setLeftChild(findNode.getLeftChild());
            }
            
        }
        
    }

	public Node<E> findMin(Node<E> node) {
		Node<E> successor = node.getRightChild();

		while (successor.getLeftChild() != null) {

			successor = successor.getLeftChild();
		}
		return successor;
	}

	public void traverse(String order, Node<E> top) {
		if (top != null) {
			switch (order) {

			case "preorder":
				// Your Code here
				System.out.printf("%d ", top.getData());
				traverse(order, top.getLeftChild());
				traverse(order, top.getRightChild());
				break;
			case "inorder":
				// Your Code here
				traverse(order, top.getLeftChild());
				System.out.printf("%d ", top.getData());
				traverse(order, top.getRightChild());
				break;
			case "postorder":
				// Your Code here
				traverse(order, top.getLeftChild());
				traverse(order, top.getRightChild());
				System.out.printf("%d ", top.getData());
				break;
			}

		}
	}
}
