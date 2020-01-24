/* Name: Sarah Coufal
 * File: LookupZip.java
 * Desc:
 *
 * This class contains methods which read zipcodes files, use information from files to
 * create Place objects, and store the Place objects in an ArrayList. The Places can be 
 * looked up by zipcode.
 *
 */

import java.util.*;
import java.io.*;

public class LookupZip {

    public static final int ZIP = 0;
    public static final int TOWN = 1;
    public static final int STATE = 2;
    public static final int POPULATION = 3;
    public static final int LATITUDE = 5;
    public static final int LONGITUDE = 6;
    
    /** Parses one line of input by creating a Place that
     * denotes the information in the given line
     * @param lineNumber The line number of this line
     * @param line One line from the zipcodes file
     * @return A Place that contains the relevant information
     * (zip code, town, state, population) from that line
     */
    public static Place parseLine(int lineNumber, String line) {
	
	//turn line from file into array of strings
	String[] stringArray = line.split(",", -1);
	
	//assign elements of array to corresponding variables
	String zip = stringArray[ZIP];
	String town = stringArray[TOWN];
	String state = stringArray[STATE];
	String populationString = stringArray[POPULATION];
	
	//if population column is empty
	if(populationString.equals("")) {
	    Place place = new Place(zip, town, state);
	    return place;
	}
	
	else {
	    //change population to integer
	    int population = Integer.parseInt(populationString);
	    PopulatedPlace populatedPlace = new PopulatedPlace(zip, town, state, 0, 0, population);
	    return populatedPlace;
	}
    }

    /** Parses one line of input, and updates current element 
     * of ArrayList with location if information exists
     * @param line One line from the zipcodes file
     * @param places ArrayList of Places 
     * @return none
     */
    public static void updatePlace(String line, ArrayList<Place> places) {
	
	//turn line from file into array of strings
	String[] stringArray = line.split(",", -1);
	
	//if location info exists (if latitude column of file is not empty)
	if(!stringArray[LATITUDE].equals("")) {
	    
	    //assign elements of array to corresponding variables
	    String zip = stringArray[ZIP];
	    String latitudeString = stringArray[LATITUDE];
	    String longitudeString = stringArray[LONGITUDE];

	    //change latitude and longitude to doubles
	    double latitude = Double.parseDouble(latitudeString);
	    double longitude = Double.parseDouble(longitudeString);

	    //remove quotation marks around zipcode
	    String newZip = zip.substring(1, zip.length()-1);

	    //use binary search to find zipcode that matches zipcode from input line
	    Place newPlace = new Place(newZip, null, null);
	    Place place = lookupZip(places, newPlace, 0, places.size()-1);

	    //if Place is a PopulatedPlace, set location info
	    if(place instanceof PopulatedPlace) {
		((PopulatedPlace)place).setLatitude(latitude);
		((PopulatedPlace)place).setLongitude(longitude);
	    }
	    
	    //otherwise replace with new LocatedPlace with location info
	    else {
		LocatedPlace locatedPlace = new LocatedPlace(place.getZip(), place.getTown(), place.getState(),
							     latitude, longitude);
		//find index of place to be replaced with LocatedPlace
		int index = places.indexOf(place);
		places.set(index, locatedPlace);
	    }	
	}
    }
    
    /** Reads both zipcodes files, parsing every line
     * @param filename1 The name of the first zipcodes file
     * @param filename2 The name of the second zipcodes file
     * @return The ArrayList of Places representing all the
     * data in the files.
     */
    public static ArrayList<Place> readZipCodes(String filename1, String filename2) 
    	throws FileNotFoundException {
	
	//input uszipcodes file
	Scanner fileInput1 = new Scanner(new File(filename1));
	ArrayList<Place> places = new ArrayList<>();
	fileInput1.nextLine();
	int lineNumber = 0;
	String line;
	
	while(fileInput1.hasNextLine()) {
	    line = fileInput1.nextLine();
	    //parse each line to create place and assign each place to ArrayList
	    places.add(parseLine(lineNumber, line));
	    lineNumber++;
	}
	
	//input ziplocs file
	Scanner fileInput2 = new Scanner(new File(filename2));
	fileInput2.nextLine();
	String line2;

	while(fileInput2.hasNextLine()) {
	    line2 = fileInput2.nextLine();
	    //update Place with location info from second file
	    updatePlace(line2, places);
	}
	return places;
    }
	    
    /** Find a Place with a given zip code
     * @param places ArrayList of Places 
     * @param zip The zip code (as a String) to look up
     * @param low The lowest index of the ArrayList to search
     * @param high The highest index of the ArrayList to search
     * @return A place that matches the given zip code,
     * or null if no such place exists.
     */
    public static Place lookupZip(ArrayList<Place> places, Place place, int low, int high) {

	//no match found
	if(low > high) {
	    return null;
	}	
	else {
	    int mid = (low + high) / 2;
	    int compare = place.compareTo(places.get(mid));

	    //match found
	    if(compare == 0) {
		return places.get(mid);
	    }
	    else if(compare < 0) {
		//recur left of the middle
		return lookupZip(places, place, low, mid-1);
	    }
	    else {
		//recur right of the middle
		return lookupZip(places, place, mid+1, high);
	    }
	}
    }
    
}
