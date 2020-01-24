/* Name: Sarah Coufal
 * File: Main.java
 * Desc:
 *
 * The main driver program for Assignment 5.
 *
 * This program reads one file containing all zipcodes in the US, along 
 * with corresponding town, state, and populations. It reads another file
 * that contains the latitude and longitude for each zipcode. Then it 
 * prompts the user to enter a zipcode and displays the corresponding 
 * town and state, along with latitude, longitude, and population if this 
 * information exists in the files.
 *
 */

import java.util.*;
import java.io.*;

public class Main {

    public static final String QUIT = "00000";
    
    public static void main(String[] args) throws FileNotFoundException {
    
	//Scanner for user's zipcode input
	Scanner in = new Scanner(System.in);
    
	//create ArrayList of places and search for input zipcode
	ArrayList<Place> places = LookupZip.readZipCodes("uszipcodes.csv", "ziplocs.csv");

	Place place;
	String line;
	Place newPlace;
	
	while(true) {
	    System.out.print("zipcode: ");
	    line = in.nextLine();
	    
	    if(line.equals(QUIT)) {
		System.out.println("Good Bye!");
		System.out.println("");
		System.exit(0);
	    }

	    //create a place with input zipcode
	    newPlace = new Place(line, null, null);

	    //use binary search to find place matching input zipcode
	    place = LookupZip.lookupZip(places, newPlace, 0, places.size()-1);

	    //if zipcode does not exist
	    if(place == null) {
		System.out.println("No such zipcode");
		System.out.println("");
	    }
	    
	    else {
		//print info of corresponding zipcode entered by user
		System.out.println(place);
		System.out.println("");
	    }
	}
    }
}
