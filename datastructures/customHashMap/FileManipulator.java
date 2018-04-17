package dataStructures.customHashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileManipulator {
	
	/** This function takes file path input and then reads words from the 
	 * file into the an array by calling another helper function.
	 * Uses Scanner class**/
	public static String[] parseFile() throws FileNotFoundException {
		String inputFilePath = "src/dataStructures/customHashMap/resources/alice_in_wonderland.txt";
		String inputString = "";
		
		File inputFile = new File(inputFilePath);
		Scanner sc_inputFile = new Scanner(inputFile);
		while(sc_inputFile.hasNextLine())
			inputString = inputString + sc_inputFile.nextLine().toLowerCase()	;
		sc_inputFile.close();
		
		String[] arrInputWords = inputString.replaceAll("^[\"\\[\\]:\\?;_!-.,\\s]+", "").split("[\"\\[\\]:\\?;_!-.,\\s]+"); 
		return arrInputWords;
	} 
	
	/** function to print a list into a notepad file. 
	 * Uses FileWriter class 
	 * **/
	public static void printIntoText(List<HashNode> listToPrint) throws IOException {
		String outputFilePath = "src/dataStructures/customHashMap/resources/HashOutput.txt";
		FileWriter writer = new FileWriter(new File(outputFilePath));
		for(HashNode h: listToPrint)
			writer.write(h.getKey() + " " + h.getValue() + System.lineSeparator());
		writer.close();
		System.out.println("Output File Generated.");
	}
}
