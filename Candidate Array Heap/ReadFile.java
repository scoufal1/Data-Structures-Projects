/* Name: Sarah Coufal
 * File: ReadFile.java
 * Desc:
 *
 * This class contains methods to parse each line of the file input, use the 
 * information in the files to create Candidate objects, and store the 
 * Candidates in a binary tree heap.
 * 
 */

import java.util.*;
import java.io.*;

public class ReadFile {

    public static final int LAST_NAME = 0;
    public static final int FULL_NAME = 1;
    public static final int PERCENTAGE = 2;

    /** Reads each line of file and inserts Candidate into binary tree
     * @param fileName The name of the file
     * @param candidateHeap The heap of candidates
     */
    public static void readFile(String fileName, ArrayHeap candidateHeap) throws FileNotFoundException {
	Scanner fileInput = new Scanner(new File(fileName));
	String line;
	Candidate candidate;
	fileInput.nextLine();
	while(fileInput.hasNextLine()) {
	    line = fileInput.nextLine();
	    candidate = parseLine(line);
	    candidateHeap.insert(candidate);
	}
    }

    /** Parses each line of input and creates new candidate object from 
     * file information
     * @param line The line from the file
     * @return Canditate object to be inserted into tree
     */
    public static Candidate parseLine(String line) {

	String[] tokens;
	String lastName;
	String fullName;
	String percentageString;
	double percentage;
	Candidate candidate;

	//create array of strings from input line and assign variable from
	//appropriate index of array
	tokens = line.split(",");
	lastName = tokens[LAST_NAME];
	fullName = tokens[FULL_NAME];
	percentageString = tokens[PERCENTAGE];
	percentage = Double.parseDouble(percentageString);

	candidate = new Candidate(lastName, fullName, percentage);
	return candidate;
    }
}
