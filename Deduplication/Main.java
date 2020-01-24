/* Name: Sarah Coufal
 * File: Main.java
 * Desc:
 *
 * The main driver program for Assignment 8.
 *
 * This class constructs a Data object, deduplicates the arraylist using a double
 * hash table, and prints statistics of deduplication.
 */

import java.io.*;
import java.util.*;

public class Main  {

    public static final int FILE = 0;
    
    public static void main(String[] args) throws FileNotFoundException {

	try {	    
	    //file name from command line args
	    String fileName = args[FILE];

	    //construct arraylist in data object
	    Data data = new Data(fileName);

	    //deduplicate
	    ArrayList<Person> people = data.hashDoubleDeduplication();

	    //print stats
	    System.out.println("Records given:" + data.size());
	    System.out.println("Attributes checked:" +
			       "sex, race, dob, age, ht_feet, ht_inch, weight, haircolr, eyecolor");
	    System.out.println("Duplicates found:" + data.getDuplicateCount());
	}
	catch(ArrayIndexOutOfBoundsException e) {
	    System.out.println("Please enter a file name");
	}
	catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}
    }
}
