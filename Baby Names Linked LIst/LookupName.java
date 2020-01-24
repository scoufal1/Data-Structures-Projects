/* Name: Sarah Coufal
 * File: LookupName.java
 * Desc:
 *
 * This class contains a method that parses the file to store name information
 * and assigns this information to linked lists. It also contains methods to
 * calculate name totals.
 * 
 */

import java.util.*;
import java.io.*;

public class LookupName {

    //constants
    public static final int RANK = 0;
    public static final int MALE_NAME = 1;
    public static final int MALE_NUMBER = 2;
    public static final int FEMALE_NAME = 3;
    public static final int FEMALE_NUMBER = 4;
    public static final int BEGIN = 8;
    public static final int END = 4;

    public static final String MALE_FLAG = "-m";
    public static final String FEMALE_FLAG = "-f";

    /** Inputs file, parses file and assigns elements of file
     * to Name objects
     * @param filename The name of the input file
     * @param list The linked list of name objects
     * @param totalArray The arraylist of total number of names of one gender for each year
     * @param gender Indication of whether the list entered is male or female names
     * @return none
     */
    //public static void readFile(String filename, NameDLL maleList, NameDLL femaleList, ArrayList<Stats> totalMaleArray, ArrayList<Stats> totalFemaleArray) throws FileNotFoundException {
    public static void readFile(String filename, NameDLL list,  ArrayList<Stats> totalArray, String gender) throws FileNotFoundException {

	//input Scanner for file
	Scanner fileInput = new Scanner(new File(filename));

	//declare string variables
	String line;
	String[] stringArray;
	String nameString = "";
	String numberString = "";
	String rankString; 
	String yearString;

	//declare int variables
	int rank;
	int number;
	int year = 0;
	int total = 0;

	Stats stats;
	Name foundName;
	
 	while(fileInput.hasNextLine()) {
	    line = fileInput.nextLine();

	    //split line into string array
	    stringArray = line.split(",");

	    //assign elements of array to string variables depending on gender
	    if(gender.equals("male")) {
		nameString = stringArray[MALE_NAME];
		numberString = stringArray[MALE_NUMBER];
	    }
	    else if(gender.equals("female")) {
		nameString = stringArray[FEMALE_NAME];
		numberString = stringArray[FEMALE_NUMBER];
	    }
	    rankString = stringArray[RANK];
	    
	    //parse filename to get year
	    yearString = filename.substring(filename.length()-BEGIN, filename.length()-END);

	    //change appropriate strings to ints
	    rank = Integer.parseInt(rankString);
	    number = Integer.parseInt(numberString);
	    year = Integer.parseInt(yearString);

	    //initialize stats object
	    stats = new Stats(rank, number, year);
	    
	    //check to see if name already exists
	    foundName = list.findName(nameString);
	    
	    //if it doesn't exist, add name object to the list
	    if(foundName == null) {
		ArrayList<Stats> statsList = new ArrayList<>();
		statsList.add(stats);
		Name name = new Name(nameString, statsList, 0);
		name.setNameTotal(number);
		list.insertSorted(name);
	    }
	    
	    //if it already exists, update object by adding to stats arraylist
	    else {
		foundName.getStats().add(stats);
		foundName.setNameTotal(foundName.getNameTotal() + number);
	    }

	    //add number to total number
	    total += number;
	}
	//update arraylist of totals
	totalArray.add(new Stats(total, year));
    }

    /** Calculates the grand total of all names in all of the files
     * for one gender
     * @param yearlyTotals The arraylist of yearly totals from each year
     * @return An integer total of babies of all names and years for one gender
     */
    public static int calcGrandTotal(ArrayList<Stats> yearlyTotals) {
	int sum = 0;
	for(int i=0; i<yearlyTotals.size(); i++) {
	    sum += yearlyTotals.get(i).getNumber();
	}
	return sum;
    }

    public static void calcInfo(String[] args, NameDLL maleList, NameDLL femaleList, ArrayList<Stats> totalMaleArray, ArrayList<Stats> totalFemaleArray) {
	
	NameDLL list = new NameDLL();
	ArrayList<Stats> totalArray = new ArrayList<>();
	
	for(int i=0; i<args.length; i++) {

	    if(args[i].equals(MALE_FLAG) || args[i].equals(FEMALE_FLAG)) {

		//element after flag is name
		if(args[i].equals(MALE_FLAG)) {
		    list = maleList;
		    totalArray = totalMaleArray;
		}
		if(args[i].equals(FEMALE_FLAG)) {
		    list = femaleList;
		    totalArray = totalFemaleArray;
		}

	        String nameString = args[i+1];
		
		//search for input name
		Name name = list.findName(nameString);
		
		if(name == null) {
		    System.out.println(nameString + " not in files \n");
		}
		
		else {
		    //calculate totals and percentages
		    int grandTotal = calcGrandTotal(totalArray);		
		    int nameTotal = name.getNameTotal();
		    double totalPercent = (double)nameTotal/grandTotal;

		    double yearlyPercent = 0;
		    int totalRank = list.calcRank(name);

		    String s = "";
		    int position = list.getPosition(name);
		    printInfo(position, name, nameTotal, grandTotal, totalRank, yearlyPercent,
			      totalPercent, totalArray);
		}
	    }	
	}
    }
    /*
    public static void calcInfo(String[] args, String flag, NameDLL list, ArrayList<Stats> totalArray) {

	for(int i=0; i<args.length; i++) {
	    
	    //element after flag is name
	    if(args[i].equals(flag)) {
	        String nameString = args[i+1];
		
		//search for input name
		Name name = list.findName(nameString);
		
		if(name == null) {
		    System.out.println(nameString + " not in files \n");
		}
		
		else {
		    //calculate totals and percentages
		    int grandTotal = calcGrandTotal(totalArray);		
		    int nameTotal = name.getNameTotal();
		    double totalPercent = (double)nameTotal/grandTotal;

		    double yearlyPercent = 0;
		    int totalRank = list.calcRank(name);

		    String s = "";
		    int position = list.getPosition(name);
		    printInfo(position, name, nameTotal, grandTotal, totalRank, yearlyPercent,
			      totalPercent, totalArray);
		}
	    }
	}
    }*/

    public static void printInfo(int position, Name name, int nameTotal, int grandTotal, int totalRank,
				 double yearlyPercent, double totalPercent, ArrayList<Stats> totalArray) {
	String s = "";
	System.out.println(position + "\n");
	for(int z=0; z < name.getStats().size(); z++) {
	    s = name.getStats().get(z).getYear() + "\n" + name.getName()  + ": " +
		name.getStats().get(z);
	    System.out.print(s);
	    int r = z;
	    while(name.getStats().get(z).getYear() != totalArray.get(r).getYear()) {
		r++;
	    }
	    yearlyPercent = (double)name.getStats().get(z).getNumber()/totalArray.get(r).getNumber();
	    System.out.printf("%1.6f\n", yearlyPercent);
	    System.out.print("\n");
	} 
	System.out.print("Total" + "\n" + name.getName() + ": " + totalRank + ", " + nameTotal + ", ");
	System.out.printf("%1.6f\n", totalPercent);
	System.out.print("\n");
    }
}
