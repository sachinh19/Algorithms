package datastructures.binomialHeap;

public class Node {
	
	// Data members of the Node class
	Node p;
	int key;
	int degree;
	Node leftChild;
	Node rightSibling;
	
	// constructor which initializes the key, everything else to null.
	public Node(int key) {
		this.key = key;
		this.p = null;
		this.degree = 0;
		this.leftChild = null;
		this.rightSibling = null;
	}
	
	// code to print a Node and its subtree.
	public void print(int level) {
        Node curr = this;
        while (curr != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            sb.append(curr.key + "->L:" + level + "->D:" + curr.degree);
            System.out.println(sb.toString());
            if (curr.leftChild != null) {
                curr.leftChild.print(level + 1);
            }
            curr = curr.rightSibling;
        }
    }
}
