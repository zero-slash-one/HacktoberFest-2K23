import java.util.Scanner;

public class $1_BinarySearchTree {
	Node root;

	private class Node {
		int data;
		Node leftChild;
		Node rightChild;

		Node(int data) {
			this.data = data;
			leftChild = rightChild = null;
		}
	}

	$1_BinarySearchTree() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter sequence of numbers to be connverted in BST::");

		// Return an string array from , separated string
		// i.e "1,2,3"---> ["1","2","3"]
		// Parsing function convert this string values into Integer
		String[] string = scanner.nextLine().split(",");
		scanner.close();

		for (int i = 0; i < string.length; i++) {
			insertIterative(Integer.parseInt(string[i]));
		}
	}

	$1_BinarySearchTree(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			insertIterative(arr[i]);
		}
	}

	void insertIterative(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			Node p = root;
			Node r = null; // Tailing pointer of p

			while (p != null) {
				if (p.data == value) {
					return;
				} else if (p.data < value) {
					r = p;
					p = p.rightChild;
				} else {
					r = p;
					p = p.leftChild;
				}
			}

			if (r.data < value) {
				r.rightChild = new Node(value);
			} else {
				r.leftChild = new Node(value);
			}
		}
	}

	void inOrderTraversal() {
		System.out.print("In-order Traversal::");
		inOrder(root);
		System.out.println();
	}

	private void inOrder(Node p) {
		if (p == null) {
			return;
		}
		inOrder(p.leftChild);
		System.out.print(p.data + " ");
		inOrder(p.rightChild);
	}

	Node searchKey(int value) {
		int count = 0; // Count no of comparisons to search the element.

		if (root == null) {
			return null;
		}
		Node res = searchKeyRecursive(root, value, count);
		if (res == null) {
			System.out.println("Element not present");
		}
		return res;
	}

	Node searchKeyRecursive(Node p, int value, int count) {
		if (p == null) {
			return null;
		} else {
			count++;
			if (p.data == value) {
				System.out.println("Element " + value + " found.\t No. of Comparisons required::" + count);
				return p;
			} else if (p.data < value) {
				return searchKeyRecursive(p.rightChild, value, count);
			} else {
				return searchKeyRecursive(p.leftChild, value, count);
			}
		}
	}

	Node deleteNode(int value) {
		Node r = null; // Tailing pointer
		return deleteNodeRecursive(root, value, r);
	}

	private Node deleteNodeRecursive(Node p, int value, Node r) {
		if (p == null) {
			return null;
		}
		if (p.leftChild == null && p.rightChild == null) {
			if (p == root) {
				root = null;
			} else {
				if (r.leftChild == p) {
					r.leftChild = null;
				} else {
					r.rightChild = null;
				}
			}
			return null;
		}

		if (value < p.data) {
			p.leftChild = deleteNodeRecursive(p.leftChild, value, p);
		} else if (value > p.data) {
			p.rightChild = deleteNodeRecursive(p.rightChild, value, p);
		} else {
			if (heightOfTree(p.leftChild) > heightOfTree(p.rightChild)) {
				Node q = inOrderPredecessor(p.leftChild);
				p.data = q.data;
				p.leftChild = deleteNodeRecursive(p.leftChild, q.data, p);
			} else {
				Node q = inOrderSuccessor(p.rightChild);
				p.data = q.data;
				p.rightChild = deleteNodeRecursive(p.rightChild, q.data, p);
			}
		}
		return p;
	}

	int heightOfTree(Node p) {
		if (p == null) {
			return 0;
		} else {
			int height_left = heightOfTree(p.leftChild);
			int height_right = heightOfTree(p.rightChild);
			
			if (height_left < height_right) {
				return height_right + 1;
			} else {
				return height_left + 1;
			}
		}
	}

	Node inOrderPredecessor(Node p) {
		while (p.rightChild != null) {
			p = p.rightChild;
		}
		return p;
	}

	Node inOrderSuccessor(Node p) {
		while (p.leftChild != null) {
			p = p.leftChild;
		}
		return p;
	}

}