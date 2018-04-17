package dataStructures.redBlackTree;

/*******
 *  RBT Properties:
 *  
 *  1. Every node is either red or black.
 *  2. The root is black.
 *  3. Every leaf (NIL) is colored black.
 *  4. If a node is red, both its children are black(includes NIL nodes).
 *  5. For any node, all simple paths to its descendant leaves has equals 
 *  	number of black nodes.
 * 
 * 
 * ****/

public class RBT {	

	private Node root;
	public static Node NIL; 
	
	// Overriding default constructor
	public RBT() {
		super();
		NIL = new Node();
		this.root = NIL;
		NIL.setRight(this.root);
		NIL.setLeft(this.root);
	}
	
	/*** Red Black Tree functions***/
	public void leftRotate(Node x) {
		
		// store right child in y assuming its not RBT.NIL
		Node y = x.getRight();
		
		// turn y's left subtree into x's right subtree.
		x.setRight(y.getLeft());
		if(y.getLeft() != NIL) 
			y.getLeft().setP(x); 
		
		// setting y's parent same as x's parent.
		y.setP(x.getP());
		
		// check if x is the root, left or right child of its parent.
		// Replace that pointer position to y.
		if(x.getP()==NIL)
			this.root = y;
		else if (x == x.getP().getLeft())
			x.getP().setLeft(y);
		else
			x.getP().setRight(y);
		
		// x as y's left child.
		y.setLeft(x);
		x.setP(y);
	}

	public void rightRotate(Node x) {
		
		// store left child in y assuming it is not RBT.NIL
		Node y = x.getLeft();
		
		// turn y's right subtree into x's left subtree.
		x.setLeft(y.getRight());
		if(y.getRight() != NIL)
			y.getRight().setP(x);
		
		// setting y's parent same as x's parent.
		y.setP(x.getP());
		
		// Check if x is the root, left or right child of its parent.
		// Replace that pointer position to y.
		if(x.getP() == NIL)
			this.root = y;
		else if (x == x.getP().getLeft())
			x.getP().setLeft(y);
		else
			x.getP().setRight(y);
		
		// set y as right of y, and y as parent of x
		y.setRight(x);
		x.setP(y);
	}
	
	public void insert(Node z) {
		// Initialize two nodes to find the position of z
		// and also z's interim parent before fixup.
		Node y = NIL;
		Node x = this.root;
		
		// find the position for z, as per binary tree property
		while(x!=NIL) {
			y=x;
			if (z.getKey() < x.getKey())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		
		// set y as parent of z
		z.setP(y);
		
		// check if z is to be the root node, if tree is empty;
		// else make the left or right child of z
		// based on Binary tree property
		if(y==NIL)
			this.root = z;
		else if (z.getKey() < y.getKey())
			y.setLeft(z);
		else 
			y.setRight(z);
		
		// set left and right child of z to point to NIL;
		// change color of z to Red.
		z.setLeft(NIL);
		z.setRight(NIL);
		z.setRed(true);
		
		//call insert fixup to check if any RBT rules are violated
		insertFixup(z);	
	}
	
	/*Function to fixup the RBT  insert if
	 * Node z being red violates the red-black trees properties*/
	private void insertFixup(Node z) {
		
		// do until both z and its parent are red
		while(z.getP().isRed()) {
			// check if z's parent is left or right child or its parent.
			if(z.getP()==z.getP().getP().getLeft()) {
				
				Node y = z.getP().getP().getRight();
				if(y.isRed()) {
					// case 1 : z's uncle is red, and z's parent is a left 
					// child
					// make z's grandparent red
					// make z's parent and uncle black
					z.getP().setRed(false);
					y.setRed(false);
					z.getP().getP().setRed(true);
					z = z.getP().getP();
				}
				else {
					// case 2: z's uncle is not red, and z's parent is a left 
					// child
					if (z == z.getP().getRight()) {
						// if z is right child
						// set z to its parent
						// perform left rotate on z
						z = z.getP();
						leftRotate(z);
					}
					// make z's parent black
					// make z's grand parent red
					// perform right rotate on z's grandparent
					z.getP().setRed(false);
					z.getP().getP().setRed(true);
					rightRotate(z.getP().getP());
				}
			}
			else {
				Node y = z.getP().getP().getLeft();
				if(y.isRed()) {
					// case 3: z's uncle is red, and z's parent is a right 
					// child.
					// change color of z's parent and z's uncle to black
					// change color of z's grandparent to red
					z.getP().setRed(false);
					y.setRed(false);
					z.getP().getP().setRed(true);
					z = z.getP().getP();
				}
				else {
					// case 4: z's uncle is black, and z's parent is a right 
					// child
					if (z == z.getP().getLeft()) {
						// if z is a left child
						// set z to its parent
						// perform right rotate on z
						z = z.getP();
						rightRotate(z);
					}
					// color z's parent black.
					// color z's grand parent red.
					// perform left rotate on z's grand parent
					z.getP().setRed(false);
					z.getP().getP().setRed(true);
					leftRotate(z.getP().getP());
				}
			}
		}
		
		// setting root to black, in case z was the root node.
		this.root.setRed(false);
	}
	
	/**Binary Tree Functions*/
	// Inorder Traversal
	public void sort(Node x) {
		if (x!=NIL) {
			sort(x.getLeft());
			System.out.println(x.getKey());
			sort(x.getRight());
		}
	}
	
	// Just like Binary Search
	public Node iterativeTreeSearch(Node x, int k) {
		while(x!=NIL && k!=x.getKey()) {
			if(k<x.getKey())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		
		return x;
	}
	
	// Get the left most leaf node which is not NIL
	public Node treeMinimum(Node x) {
		while(x.getLeft()!=NIL)
			x = x.getLeft();
		
		return x;
	}
	
	// get the right most leaf node which is not NIL
	public Node treeMaximum(Node x) {
		while(x.getRight()!=NIL)
			x = x.getRight();
		
		return x;
	}
	
	
	// Successor of a given node is either the minimum node on x's right subtree
	// OR 
	// find first ancestor of x who is a left child
	// its parent is the successor of x
	public Node treeSuccessor(Node x) {
		if(x.getRight()!=NIL) 
			return treeMinimum(x.getRight());
		Node y = x.getP();
		while(y!=NIL && x==y.getRight()) {
			x = y;
			y = y.getP();
		}
		return y;
	}
	
	// Predecessor of a given node is either the maxium node on x's left subtree
	// OR
	// find first ancestor of x who is a right child
	// its parent is the successor of x
	public Node treePredecessor(Node x) {
		if(x.getLeft()!=NIL) 
			return treeMaximum(x.getLeft());
		Node y = x.getP();
		while(y!=NIL && x==y.getLeft()) {
			x = y;
			y = y.getP();
		}
		return y;
	}
	
	// count the depth till the end.
	public int maxDepth(Node x) {
		if(x==NIL) 
			return 0;
		else {
			int leftDepth = maxDepth(x.getLeft());
			int rightDepth = maxDepth(x.getRight());
			
			if(leftDepth>rightDepth)
				return (leftDepth + 1);
			else
				return (rightDepth + 1);
		}
	}
	
	
	// getters and setters
	public Node getRoot() {
		return root;
	}
	
	
	public void transplant(Node u, Node v) {
		// replace u node with v
		// case when u is root
		if(u.getP()==NIL) 
			this.root = v;
		// case when u is the left child
		else if(u == u.getP().getLeft())
			u.getP().setLeft(v);
		// case when u is the right child
		else
			u.getP().setRight(v);
		v.setP(u.getP());
	}
	
	
	public void delete(Node z)	{
		
		// y is either the node removed from the tree
		// or moved within the tree
		Node y = z;
		Node x = null;
		boolean yOriginalColor = y.isRed();
		
		if(z.getLeft()== NIL) {
			// case when z only has one child - right
			x = z.getRight();
			transplant(z,z.getRight());
		}else if(z.getRight() == NIL) {
			// case when z only has one child - left
			x = z.getLeft();
			transplant(z,z.getLeft());
		}
		else {
			// setting y to z's successor, replace z with y.
			y = treeMinimum(z.getRight());			
			yOriginalColor = y.isRed();
			x = y.getRight();
			
			//x moves into y's position.
			if(y.getP() == z) 
				x.setP(y);
			else {
				transplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setP(y);
			}
			transplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setP(y);
			y.setRed(z.isRed());
		}
		// if y was black originally then call deleteFixUp
		if(!yOriginalColor)
			deleteFixup(x);
	}
	
	
	private void deleteFixup(Node x)	{
		
		Node w;
		while(x!=this.getRoot() && !x.isRed()) {
			if(x == x.getP().getLeft()) {
				w = x.getP().getRight();
				
				if(w.isRed()) {
					// Case 1 : w is red.
					w.setRed(false);
					x.getP().setRed(true);
					leftRotate(x.getP());
					w = x.getP().getRight();
				}
				if(!w.getLeft().isRed() && !w.getRight().isRed()) {
					// Case 2: w is black, w's both children black
					w.setRed(true);
					x = x.getP();
				}
				else {
					if(!w.getRight().isRed()) {
						// Case 3: w is black, w's left child red, right child black.
						w.getLeft().setRed(false);
						w.setRed(true);
						rightRotate(w);
						w = x.getP().getRight();
					}
					// case 4: w is black, w's right child is red.
					w.setRed(x.getP().isRed());
					x.getP().setRed(false);
					w.getRight().setRed(false);
					leftRotate(x.getP());
					x = this.getRoot();
				}
			}
			else {
				w = x.getP().getLeft();
				if(w.isRed()) {
					// Case 1 : w is red.
					w.setRed(false);
					x.getP().setRed(true);
					rightRotate(x.getP());
					w = x.getP().getLeft();
				}
				if(!w.getRight().isRed() && !w.getLeft().isRed()) {
					// Case 2: w is black, w's both children black
					w.setRed(true);
					x = x.getP();
				}
				else {
					if(!w.getLeft().isRed()) {
						// Case 3: w is black, w's right child red, left child black.
						w.getRight().setRed(false);
						w.setRed(true);
						leftRotate(w);
						w = x.getP().getLeft();
					}
					// case 4: w is black, w's left child is red.
					w.setRed(x.getP().isRed());
					x.getP().setRed(false);
					w.getLeft().setRed(false);
					rightRotate(x.getP());
					x = this.getRoot();
				}
			}
		}
		x.setRed(false);
	}	
	

}
