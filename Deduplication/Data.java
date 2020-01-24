/* Name: Sarah Coufal
 * File: Data.java
 * Desc:
 *
 * This class holds the whole data set by parsing each line of the file input to create 
 * Person objects, and inserting each person into an arraylist. It also contains several
 * deduplication methods. 
 * 
 */

import java.io.*;
import java.util.*;

public class Data {

    //constants used in file parsing
    public static final int SEX = 80;
    public static final int RACE = 81;
    public static final int DOB = 82;
    public static final int AGE = 83;
    public static final int HEIGHT_FEET = 84;
    public static final int HEIGHT_INCHES = 85;
    public static final int WEIGHT = 86;
    public static final int HAIR_COLOR = 87;
    public static final int EYE_COLOR = 88;

    //constant used for capacity of hashmap
    public static final int CAP = 1000003;
    
    //constant used for number of columns of csv
    public static final int COLUMN_SIZE = 112;
    
    private ArrayList<Person> data; //arraylist to hold full set of data
    private int duplicateCount; //count of duplicates incremented in deduplication methods

    /** Creates a Data object with given file input
     * @param fileName The name of the CSV file
     */
    public Data(String fileName) throws FileNotFoundException {	
	data = readFile(fileName);	
    }

    //size of arraylist
    public int size() {
	return data.size();
    }
    
    //getters
    public int getDuplicateCount() {
	return duplicateCount;
    }

    public ArrayList<Person> getData() {
	return data;
    }
    
    /** Reads each line of file and inserts Person into arraylist
     * @param fileName The name of the file
     * @return The arraylist of Person objects
     */
    public ArrayList<Person> readFile(String fileName) throws FileNotFoundException {

	ArrayList<Person> data = new ArrayList<>();
	Scanner fileInput = new Scanner(new File(fileName));

	//skip first line
	fileInput.nextLine();

	String line;
	
	//counts number of lines for testing purposes
	int number = 0;
	
	while(fileInput.hasNextLine()) {
	    number++;
	    line = fileInput.nextLine();
	    data.add(parseLine(line));
	}	
	return data;
    }

    /** Parses each line of input and creates new Person object from 
     * file information
     * @param line The line from the file
     * @return Person object to be inserted into arraylist
     */
    public Person parseLine(String line) {

	String[] tokens = line.split(",", -1);

	if(tokens.length != COLUMN_SIZE) {
	    tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	}

	//assign columns to variables
	String sex = tokens[SEX];
	String race = tokens[RACE];
	String dobString = tokens[DOB];
	String ageString = tokens[AGE];
	String heightFeet = tokens[HEIGHT_FEET] + "'";
	String heightInches = tokens[HEIGHT_INCHES] + "\"";
	String weightString = tokens[WEIGHT];
	String hairColor = tokens[HAIR_COLOR];
	String eyeColor = tokens[EYE_COLOR];

	//parse and adjust as needed
	String height = heightFeet + heightInches;
	int dob = Integer.parseInt(dobString);
	int age = Integer.parseInt(ageString);
	int weight = Integer.parseInt(weightString);

	//construct and return Person object
	Person person = new Person(sex, race, dob, age, height, weight, hairColor, eyeColor);
	return person;
    }

    //deduplication methods
    
    /** Compares all objects in arraylist and inserts only nonduplicates into new arraylist
     * @return Arraylist of unique Person objects
     */
    public ArrayList<Person> allPairsDeduplication() {

	duplicateCount = 0;
	ArrayList<Person> personList = new ArrayList<>();
	int doubles;
	
	for(int i = 0; i < data.size(); i++) {
	    doubles = 0;
	    
	    for(int j = i+1; j < data.size(); j++) {
		//compare current element to all following elements
		int compare = data.get(i).compareTo(data.get(j));
		
		if(compare == 0) {
		    //increment doubles if a match is found
		    doubles++;   
		}
	    }
	    //add the arraylist if no doubles are found
	    if(doubles == 0) {
		personList.add(data.get(i));
	    }
	    //incremement duplicateCount if doubles are found
	    else {
		duplicateCount++;
	    }
	}
	return personList;
    }
    
    /** Inserts arraylist into linear hashmap to replace duplicates, then inserts keys of 
     * hashmap into arraylist to create deduplicated arraylist
     * @return Arraylist of unique Person objects
     */
    public ArrayList<Person> hashLinearDeduplication() {

	duplicateCount = 0;
	ArrayList<Person> personList = new ArrayList<>();
	ProbeHashMap<Person, Integer> hashMap = new ProbeHashMap(CAP);
	int numItems;

	for(int i = 0; i < data.size(); i++) {
	    //first time item is entered
	    if(hashMap.get(data.get(i)) == null) {
		hashMap.put(data.get(i), 1);
	    }
	    else {
		//find number of times item is entered
		numItems = hashMap.get(data.get(i));
		//increment value
		numItems++;
		//replace old element in table
		hashMap.put(data.get(i), numItems);
		duplicateCount++;
	    }
	    
	}

	//print hash stats
        System.out.println("Average number of probes: "
			   + hashMap.averageProbes(data.size()));
	System.out.println("Max number of probes: "
			   + hashMap.getMaxProbes());
 	System.out.println("Load factor: " + (double)hashMap.size()/CAP);
	System.out.println();
	
	Iterable<Person> keys = hashMap.keySet();
	Iterator<Person> iterator = keys.iterator();
	
	//put hash table Person objects (keys) into arraylist
	while(iterator.hasNext()) {
	  personList.add(iterator.next());
	}
	return personList;
    }

    /** Inserts arraylist into double hashmap to replace duplicates, then inserts keys of 
     * hashmap into arraylist to create deduplicated arraylist
     * @return Arraylist of unique Person objects
     */
    public ArrayList<Person> hashDoubleDeduplication() {

	duplicateCount = 0;
	ArrayList<Person> personList = new ArrayList<>();
	DoubleHashMap<Person, Integer> hashMap = new DoubleHashMap(CAP);
	int numItems;

	for(int i = 0; i < data.size(); i++) {

	    //first time item is entered
	    if(hashMap.get(data.get(i)) == null) {
		hashMap.put(data.get(i), 1);
	    }
	    else {
		//find number of times item is entered
		numItems = hashMap.get(data.get(i));
		//increment value
		numItems++;
		//replace old element in table
		hashMap.put(data.get(i), numItems);
		duplicateCount++;
	    }
	    
	}
       
	//print hash stats
        System.out.println("Average number of probes: "
			     		   + hashMap.averageProbes(data.size()));
	System.out.println("Max number of probes: "
			   + hashMap.getMaxProbes());
 	System.out.println("Load factor: " + (double)hashMap.size()/CAP);
	System.out.println();
	
	Iterable<Person> keys = hashMap.keySet();
	Iterator<Person> iterator = keys.iterator();
	
	//put hash table Person objects (keys) into arraylist
	while(iterator.hasNext()) {
	  personList.add(iterator.next());
	}
	return personList;
    }

    /** Sorts arraylist using quicksort, then removes duplicates by comparing adjacent objects
     * @return Arraylist of unique Person objects
     */
    public ArrayList<Person> quicksortDeduplication() {

	duplicateCount = 0;
	ArrayList<Person> personList = new ArrayList<>();

	//copy arraylist
	for(int i = 0; i < data.size(); i++) {
	    personList.add(data.get(i));
	}

	//sort
	quickSort(personList);

	//deduplicate beginning of arraylist
	int i = 0;
	for(int j = 0; j < personList.size(); j++) {

	    int compare = personList.get(i).compareTo(personList.get(j));
	    if(compare != 0) {
		i++;
	        personList.set(i, personList.get(j));
	    }
	}
	//remove duplicates from end of arraylist
	for(int z = personList.size()-1; z > i; z--) {
	    personList.remove(personList.size()-1);
	    duplicateCount++;
	}
	return personList;
    }

    /** Sorts arraylist using Collections.sort, then removes duplicates by comparing adjacent 
     * objects
     * @return Arraylist of unique Person objects
     */
    public ArrayList<Person> builtinSortDeduplication() {
	
	duplicateCount = 0;
	
	ArrayList<Person> personList = new ArrayList<>();
	for(int i = 0; i < data.size(); i++) {
	    personList.add(data.get(i));
	}

	//sort
	Collections.sort(personList);

	//deduplicate beginning of arraylist
	int i = 0;
	for(int j = 0; j < personList.size(); j++) {

	    int compare = personList.get(i).compareTo(personList.get(j));
	    if(compare != 0) {
		i++;
	        personList.set(i, personList.get(j));
	    }
	}
	//remove duplicates from end of arraylist
	for(int z = personList.size()-1; z > i; z--) {
	    personList.remove(personList.size()-1);
	    duplicateCount++;
	}
	return personList;
    }

    /** Sorts arraylist by randomly selecting a pivot and recursively sorting 
     * objects greater and less than pivot
     * @param Arraylist of Person objects
     */
    public void quickSort(ArrayList<Person> list) {
	if(list.size() < 2) {
	    return;
	}

	//generate random index for pivot
	Random r = new Random();
	int random = r.nextInt(list.size());
	Person p = list.get(random);

	//less than
	ArrayList<Person> L = new ArrayList<>();
	//equal to
	ArrayList<Person> E = new ArrayList<>();
	//greater than
	ArrayList<Person> G = new ArrayList<>();

	//partition
	while(!list.isEmpty()) {
	    Person y = list.remove(0);
	    int compare = y.compareTo(p);
	    if(compare < 0) {
		L.add(y);
	    }
	    else if(compare == 0) {
		E.add(y);
	    }
	    else {
		G.add(y);
	    }  
	}

	//recur on two arraylists
	quickSort(L);
	quickSort(G);

	//combine arraylists
	for(int i = 0; i < L.size(); i++) {
	    list.add(L.get(i));
	}
	for(int i = 0; i < E.size(); i++) {
	    list.add(E.get(i));
	}
	for(int i = 0; i < G.size(); i++) {
	    list.add(G.get(i));
	}
    }
}
