package dataStructures.customHashMap;
import java.util.ArrayList;
import java.util.List;

public class CustomHashMap {
	
	/*** Constructor of the DataStruct used for hashing***/
	public CustomHashMap() {
		dataStruct = new ArrayList<HashNode>();
		//taking number of buckets as a prime number to minimize collisions
		lenDataStruct = 743;
		// stores the number of unique keys inserted at any given moment.
		currSize = 0;
		
		// initializes all the elements in the arrayList to null.
		for(int i=0;i<lenDataStruct;i++)
			dataStruct.add(null);
		
	}
	
	

	/*** HASH FUNCTION ***/
	/** this function hashes a given key by iterating over all
	 *  characters in the string
	 * 
	 *  getting the SUM of the PRODUCT of
	 *  
	 *  - the index of the character in the string + 1
	 *  - ascii value of that character
	 *  
	 *  MOD => then the remainder of SUM when divided by the length of 
	 *  DataStruct is returned as the hash Value.
	 *  
	 * */
	public int getHash(String key) {
		double calc = 0;
		char c;
		for(int index = 0; index < key.length(); index++) {
			c = key.charAt(index);
			calc += calc + (index+1)*((int)c);
		}
		int x = ((int)calc % lenDataStruct);
		return x;
	}
	
	/*** insert - assuming that the key does not exist, adds the key to the
	 *   position same as its hash value.
	 *   
	 *   Time Complexity : O(1 + n/m)
	 * 
	 * ***/
	public void insert(String key, int wordPos) {
		
		// find the hash of the given key
		int index = getHash(key);
		
		HashNode pointer = dataStruct.get(index);
		while(pointer!=null) {
			if(pointer.getKey().equals(key)) {
				pointer.setValue(wordPos);
				return;
			}
			pointer = pointer.getNext();
		}
		
		// Get the pointer at that index, 
		// make a new Object for the key, wordPos to be added to the list.
		// add the new Object to the index in the DataStruct
		
		pointer = dataStruct.get(index);
		HashNode toAdd = new HashNode(key,wordPos);
		toAdd.setNext(pointer);
		dataStruct.set(index, toAdd);
		
		// increment the size of elements by 1
		currSize++;
	}
	
	/*** This function increases the count of an existing key by 1, also
	 * add the wordPosition to the linked list of word Position
	 * 
	 *  Time Complexity : O(1 + n/m).
	 *  n - number of keys
	 *  m - number of buckets
	 * 
	 * ***/
	public void increase(String key,int wordPos) {
		int index = getHash(key);
		HashNode pointer = dataStruct.get(index);
		while(pointer!=null) {
			if(pointer.getKey().equals(key)) {
				pointer.setValue(wordPos);
				return;
			}
			pointer = pointer.getNext();
		}
	}
	
	/*** This function lists all the keys from the DataStruct
	 * also returns a List of keys to be printed into an output file.
	 * 
	 *  Time Complexity - O(n).
	 *  n - number of keys
	 * 
	 *  ***/
	public List<HashNode> listAllKeys() {
		List<HashNode> allHashers = new ArrayList<HashNode>();
		List<Integer> counter = new ArrayList<Integer>();
		for(int i=0;i<dataStruct.size();i++)
			counter.add(0);
		
		int usedBucketsCount = 0;
		for(int i=0;i<dataStruct.size();i++) {
			HashNode h = dataStruct.get(i);
			if(h!=null) {
				usedBucketsCount++;
				while(h!=null) {
					counter.set(i, counter.get(i) + 1);
					allHashers.add(h);
					System.out.println();
					System.out.print(h.getKey() + "-");
					WordPosition wp = h.getValue().getWp();
					while(wp!=null) {
						System.out.print(wp.getLoc() + ",");
						wp = wp.getNext();
					}
					h = h.getNext();
				}
			}
		}
		
		int max = 0;
		for(Integer i:counter)
			if(max < i.intValue())
				max = i.intValue();

		System.out.println();
		System.out.println("Max Collision : " + max);
		System.out.println("Used Buckets Count:" + usedBucketsCount);
		
		return allHashers;
	}
	
	
	/**  Deletes the object with the given key after finding it
	 * 	
	 * 	 Returns -1 if the given key is not found else returns the value of 
	 * 	 the key else deletes the object and returns the current 
	 *   count of that key
	 * 
	 * 	 Time Complexity - O(1 + n/m) 
	 * 
	 * **/
	public int delete(String key) {
		int index = getHash(key);
		HashNode pointer = dataStruct.get(index);
		HashNode prev = null;
		while(pointer!=null) {
			if(pointer.getKey().equals(key))
				break;
			
			prev = pointer;
			pointer = pointer.getNext();
		}
		
		// if key not found
		if(pointer == null)
			return -1;
		
		// Decrease count of keys
		currSize--;
		
		// check if the found key is at the first position of collision chain.
		if(prev!=null) 
			prev.setNext(pointer.getNext());
		else
			dataStruct.set(index,pointer.getNext());
		
		return pointer.getValue().getCount();	
		
	}
	
	/**  Checks if the given key is present in the DataStruct.
	 *  
	 *   Returns -1 if not, else returns the count of the key
	 *   
	 * 	 Time Complexity - O(1 + n/m)
	 * 
	 * **/	
	public int find(String key) {
		int index = getHash(key);
		HashNode pointer = dataStruct.get(index);
		while(pointer!=null) {
			if(pointer.getKey().equals(key)) 
				return pointer.getValue().getCount();
			pointer = pointer.getNext();
		}
		return -1;
	}
	
	/*** Getters and Setters ***/
	public int getLenDataStruct() {
		return lenDataStruct;
	}

	public int getCurrSize() {
		return currSize;
	}
	
	public boolean isEmpty() {
		return (currSize == 0);
	}	
	
	public int size() {
		return currSize;
	}
	
	
	// Data members	
	private List<HashNode> dataStruct;
	private int lenDataStruct;
	private int currSize;
}	
