package dataStructures.customHashMap;

/*** This class is designed to store the WordPosition of any key
 * 	this works like a singly linked list apart from storing the WordPosition 
 *  as the data in each object.
 *   
 *   ***/
public class WordPosition {
	
	public WordPosition() {
		super();
		this.position = -1;
		this.next = null;
	}
	
	public int getLoc() {
		return position;
	}
	public void setLoc(int loc) {
		this.position = loc;
	}
	public WordPosition getNext() {
		return next;
	}
	public void setNext(WordPosition next) {
		this.next = next;
	}
	public WordPosition(int loc) {
		super();
		this.position = loc;
		this.next = null;
	}
	@Override
	public String toString() {
		return "[loc=" + position + ", next=" + next + "]";
	}
	private int position;
	private WordPosition next;
}
