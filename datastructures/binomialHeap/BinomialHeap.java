package datastructures.binomialHeap;

public class BinomialHeap {
	private Node head;
	
	// Constructor
	private BinomialHeap() {
		this.head = null;
	}
	
	// Check if heap is empty
	public boolean isHeapEmpty() {
		return this.head == null;
	}
	
	// Factory method
	public static BinomialHeap makeBinaryHeap() {
		return new BinomialHeap();
	}
	
	// Make the tree with y, the left subtree of tree with node z.
	private static void binomialLink(Node y, Node z) {
		y.p = z;
		y.rightSibling = z.leftChild;
		z.leftChild = y;
		z.degree = z.degree + 1;
	}
	
	// Make another heap with node x and 
	// union existing and new heap.
	public void binomialHeapInsert(Node x) {
		BinomialHeap h = new BinomialHeap();
		h.head = x;
		BinomialHeap hPrime = this.binomialHeapUnion(h);
		this.head = hPrime.head;		
	}
	
	public BinomialHeap binomialHeapUnion(BinomialHeap hPrime) {
		BinomialHeap h = new BinomialHeap(); 
		// merge given two heaps
		// assign it to a new heap
		h.head = binomialHeapMerge(this,hPrime);
		hPrime.head = null;
		// if both heaps were empty then return null
		if(h.head == null)
			return h;
		
		// finds the heaps with same degrees
		Node prevX = null;
		Node x = h.head;
		Node nextX = x.rightSibling;
		while(nextX != null) {
			if(x.degree!=nextX.degree||(nextX.rightSibling!=null && nextX.rightSibling.degree==x.degree)) {
				// if degrees dont match then increment pointers
				prevX = x;
				x = nextX;
			}
			else { 
				// if x is a smaller key
			    // then link nextX as a child of x
				if(x.key <= nextX.key) {
					x.rightSibling = nextX.rightSibling;
					binomialLink(nextX, x);
				}
				else {
					// or if x is larger key
					// the based on conditions
					// remove x and make it the child of nextX
					if(prevX == null)
						h.head = nextX;
					else
						prevX.rightSibling = nextX;
					binomialLink(x, nextX);
					x = nextX;
				}
			}
			nextX = x.rightSibling;
		}
		
		return h;
	}
	
	
	// This functions works like the merge in Merge sort
	private static Node binomialHeapMerge(BinomialHeap h1, BinomialHeap h2) {
		
		// checking for null values
		if(h1.head == null)
			return h2.head;
		if(h2.head == null)
			return h1.head;
		
		// initialize pointers
		// initialize the first pointer based on smallest node in both heaps
		Node first = null;
		Node curr = null;
		Node currh1 = h1.head;
		Node currh2 = h2.head;
		if (currh1.degree < currh2.degree) { 
            first = currh1;
            currh1 = currh1.rightSibling;
        }
        else {
        	first = currh2;
            currh2 = currh2.rightSibling;
        }
		
		// loop over remaining values by attaching the smallest degree element
		// one at a time until one of the pointers becomes null
		curr = first;
		while (currh1!=null && currh2!=null) {
	        if (currh1.degree < currh2.degree) { 
	            curr.rightSibling = currh1;
	            currh1 = currh1.rightSibling;
	        }
	        else {
	            curr.rightSibling = currh2;
	            currh2 = currh2.rightSibling;
	        }
	        
	        curr = curr.rightSibling;
	    }
	    
		// attach the remaining of the other list to current list's right
	    if (currh1!=null)
	        curr.rightSibling = currh1;
	    else
	        curr.rightSibling = currh2;

	    //return the head of newly formed list of heaps
	    return first;
	}
	
	// returns the minimum element
	// is one of the root of different degree heaps
	public Node binomialHeapMinimum() {
		Node y = null;
		Node x = this.head;
		int min = Integer.MAX_VALUE;
		while(x!=null) {
			if (x.key < min) {
				min = x.key;
				y = x;
			}
			x = x.rightSibling;
		}
		return y;
	}
	
	// deleting the minimum value node, helper function for extract min
	public Node deleteMin() {
		// if the heap is empty
		// return MAXINT as output
		Node min = new Node(Integer.MAX_VALUE);
		if(this.head == null)
			return min;
		
		
		Node prevMin = null;
		Node curr = this.head;
		Node prev = null;
		
		// find the smallest node
		// keep track of node who points to this minimum node
		while(curr!=null) {
			if(curr.key<min.key) {
				min = curr;
				prevMin = prev;
			}
			prev = curr;
			curr = curr.rightSibling;
		}
		
		// if its the head then move one pointer ahead
		if(prevMin == null)
			this.head = min.rightSibling;
		else {
			// else attach the previous node to the rightSibling of min
			prevMin.rightSibling = min.rightSibling;
		}
		min.rightSibling = null;
		
		//return min node
		return min;
	}
	
	// Code to reverse a linked list.
	private static Node reverseLinkedList(Node head) {
		   
		Node prev = null;
		Node next = null;
		
		// check boundary condition
		if(head ==null)
			
			
			return head;
		
		next = head.rightSibling;
		while(next!=null) {
			// point current node backwards
			head.rightSibling = prev;
			
			// move all three pointers ahead
			prev = head;
			head = next;
			next = next.rightSibling;
		}
		
		// Point last node backwards
		head.rightSibling = prev;
		return head;
	}
	
	
	public Node binomialHeapExtractMin() {
		
		// delete the minimum node from the tree
		// store it in x
		Node x = this.deleteMin();
		
		// make a new heap
		// reverse the child list of x and assign it to the head of new heap.
		BinomialHeap hPrime = new BinomialHeap();
		hPrime.head = reverseLinkedList(x.leftChild);
		
		// perform union on the child tree list of x and the original heap
		BinomialHeap h = this.binomialHeapUnion(hPrime);
		this.head = h.head;
		
		// return the minimum node
		return x;
	}
	
	
	public void binomialHeapDecreaseKey(Node x, int k) {
		// if k is bigger than current key, do nothing
		if(k>x.key) {
			System.out.println("New key is greater than current key");
		}
		else {
			// change the key of given node to k
			x.key = k;
			Node y = x;
			Node z = y.p;
			// bubble up the node to its correct position in the tree
			// by just exchanging the key values of all nodes in
			// y's path to the root
			while(z!=null && y.key < z.key) {
				int temp = z.key;
				z.key = y.key;
				y.key = temp;
				y = z;
				z = y.p;
			}
		}
	}
	
	// This function calls the decrease key function
	// followed by extract min function
	public void binomialHeapDelete(Node x) {
		this.binomialHeapDecreaseKey(x, Integer.MIN_VALUE);
		this.binomialHeapExtractMin();
	}
    
	// This functions prints the heap by calling the print function of 
	// Node class
    public void printHeap() {
    	System.out.println("Binomial heap Representation:");
    	Node x = this.head;
        if (x != null)
            x.print(0);
    }
	
	
}
