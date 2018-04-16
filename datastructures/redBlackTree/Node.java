package datastructures.redBlackTree;

public class Node {
	public Node(int key, Node left, Node right, boolean isRed, Node p) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.isRed = isRed;
		this.p = p;
	}
	
	public Node(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
		this.isRed = false;
		this.p = null;
	}
	
	public Node() {
		this.key = Integer.MAX_VALUE;
		this.left = null;
		this.right = null;
		this.isRed = false;
		this.p = null;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public int getKey() {
		return key;
	}
	
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	public Node getP() {
		return p;
	}

	public void setP(Node p) {
		this.p = p;
	}
	
	private int key;
	private Node left,right,p;
	private boolean isRed;
	
}
