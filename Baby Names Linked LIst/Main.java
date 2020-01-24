/* Name: Sarah Coufal
 * File: Main.java
 * Desc:
 *
 * The main driver program for Assignment 3.
 *
 * This program creates two doubly linked lists, (one of female Name
 * objects and one of male Name objects), reads name files and 
 * names input by the user (via args), searches for relevant information
 * related to name (rank, number) and displays this information for
 * each year, along with the percentage of babies per year with given
 * name, and total rank, number and percentage overall in all years
 * given.
 * 
 */

import java.util.*;
import java.io.*;


public class Main {

    public static final String MALE_FLAG = "-m";
    public static final String FEMALE_FLAG = "-f";
    
    public static void main(String[] args) throws FileNotFoundException {

	//create linked lists
	NameDLL maleList = new NameDLL();
	NameDLL femaleList = new NameDLL();

	//create arraylists for name files and totals
	ArrayList<String> nameFiles = new ArrayList<>();
	ArrayList<Stats> totalMaleArray = new ArrayList<>();
	ArrayList<Stats> totalFemaleArray = new ArrayList<>();

	//add name files to array of name files
	for(int j=0; j<args.length; j++) {
	    //skip flags and names
	    if(args[j].equals(MALE_FLAG) || args[j].equals(FEMALE_FLAG)) {
		j++;
	    }
	    else {
		nameFiles.add(args[j]);
	    }
	}

	//add names to list or update existing names
	for(int k=0; k<nameFiles.size(); k++) {
	    LookupName.readFile(nameFiles.get(k), maleList, totalMaleArray, "male");
	    LookupName.readFile(nameFiles.get(k), femaleList, totalFemaleArray, "female");
	}
	
	LookupName.calcInfo(args, maleList, femaleList, totalMaleArray, totalFemaleArray);
	
    }
}

