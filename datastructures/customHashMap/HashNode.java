package dataStructures.customHashMap;

public class HashNode {
	/**Constructor 
	 * Creates a new value object to store the wordPos.
	 * **/
	public HashNode(String key, int wordPos) {
		super();
		this.key = key;
		this.value = new Value(1,new WordPosition(wordPos));
	}
	
	@Override
	public String toString() {
		return "Hasher [key=" + key + ", value=" + value + "]";
	}
	
	
	/****Getters and setters***/
	public HashNode getNext() {
		return next;
	}
	public void setNext(HashNode next) {
		this.next = next;
	}
	public String getKey() {
		return key;
	}
	public Value getValue() {
		return value;
	}
	
	/** Increments the count of the key by 1
	 * Finds the end of the WordPosition Linked list and adds the current 
	 * wordPos to the end of the list **/
	public void setValue(int wordPos) {
		this.getValue().setCount(this.getValue().getCount()+1);
		WordPosition wp = this.getValue().getWp();
		WordPosition prev = null;
		while(wp!=null) {
			prev = wp;
			wp = wp.getNext();
		}
		prev.setNext(new WordPosition(wordPos));
	}

	
	// data members
	private String key;
	private Value value;
	private HashNode next;
}
