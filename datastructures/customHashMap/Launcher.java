package dataStructures.customHashMap;

import java.io.IOException;
import java.util.List;

public class Launcher {
	public static void main(String[] args) throws IOException {
		// Read keys from notepad into an array.
		String[] inputWords = FileManipulator.parseFile();
		
		// Initialize the data structure used for hashing
		CustomHashMap hashData = new CustomHashMap();
		
		// Add keys to the hash data structure, i stores the position of words.
		for(int i=0;i<inputWords.length;i++) {
			// if the key does not already exist
			if(hashData.find(inputWords[i]) == -1) {
			//if(hashData.find(inputWords[i]) ==-1) {
				// insert the key into the dataStruct
				hashData.insert(inputWords[i],i);
			}
			else {
				//else increase the count of the given key, update the position too.
				hashData.increase(inputWords[i],i);
			}
		}
		
		// get all the keys in the data structure in a list, also print them.
		List<HashNode> h = hashData.listAllKeys();
		// Print some parameters of the dataStruct to check its performance.
		System.out.println("Buckets " + hashData.getLenDataStruct());
		System.out.println("Keys " + hashData.getCurrSize());
		
		// Print the output of hashing to a notepad file.
		FileManipulator.printIntoText(h);
		
		// Add keys to the hash data structure, i stores the position of words.
		for(int i=0;i<inputWords.length;i++) {
			// if the key does not already exist
			if(hashData.find(inputWords[i]) ==-1) {
				hashData.delete(inputWords[i]);
			}
		}
		
		
		
	
		
	}
}