package dataStructures.customHashMap;


/*** This class is designed to store the value of the keys
 *   It stores the count of the occurences and a WordPosition object
 *   
 *   ***/
public class Value {
	
	@Override
	public String toString() {
		return "[count=" + count + ", wordPos=" + wordPos + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WordPosition getWp() {
		return wordPos;
	}

	public void setWp(int wp) {
		this.wordPos = new WordPosition(wp);
	}

	public Value() {
		super();
	}

	public Value(int count, WordPosition wordPos) {
		super();
		this.count = count;
		this.wordPos = wordPos;
	}
	private int count;
	private WordPosition wordPos;
	
}
