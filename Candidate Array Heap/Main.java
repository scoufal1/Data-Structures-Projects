/* Name: Sarah Coufal
 * File: Main.java
 * Desc:
 *
 * The main driver program for Assignment 7.
 * 
 * This class uses polling files input on the command line and creates an array heap of 
 * candidates, updating it with each file, then prints the tree. It also takes optional
 * command line arguments to remove candidates and print the top n candidates in the most
 * recent tree.
 *	
 */

import java.util.*;
import java.io.*;

public class Main {

    public static final String TOP_N = "-n";
    public static final String REMOVE = "-r";
    public static final String FILE = ".csv";
    
    public static void  main(String[] args) {

	//create heap
        ArrayHeap<Candidate> candidateHeap = new ArrayHeap<>();

	//initialize index of remove flag
	int indexOfRemove = 0;

	//create arraylist of files
	ArrayList<String> files = new ArrayList<>();

	for(int i = 0; i < args.length; i++) {
	    //find index of remove flag
	    if(args[i].equals(REMOVE)) {
		indexOfRemove = i;
	    }
	    //add file arguments to arraylist
	    if(args[i].contains(FILE)) {
		files.add(args[i]);
	    }
	}

	//sort files by polling date
	Collections.sort(files);

	String fileName;
	
	try {
	    //loop length of input files
	    for(int i = 0; i < files.size(); i++) {
		fileName = files.get(i);
		//read file and print updated candidate heap
		ReadFile.readFile(fileName, candidateHeap);
		System.out.println(candidateHeap);
	    }
	}
	catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}

	//create arraylist of candidate to remove
	ArrayList<String> removeCandidates = new ArrayList<>();

	for(int i = indexOfRemove + 1; i < args.length; i++) {
	    //if loop reaches top n flag or files, end loop
	    if(args[i].equals(TOP_N) || args[i].contains(FILE)) {
		break;
	    }
	    removeCandidates.add(args[i]);
	}

	Candidate candidate;

	//remove candidates from heap
	for(int i = 0; i < removeCandidates.size(); i++) {
	    candidate = new Candidate(removeCandidates.get(i), null, 0);
	    candidateHeap.remove(candidate);
	}

	//initialize number of candidates to be listed
	int n = 0;
	
	try {
	    ArrayList<Candidate> topN;
	    for(int i = 0; i < args.length; i++) {

		//print top n candidates
		if(args[i].equals(TOP_N)) {
		    n = Integer.parseInt(args[i+1]);
		    if(n == 1) {
			System.out.println("Top " + n + " Candidate:");
		    }
		    else {
			System.out.println("Top " + n + " Candidates:");
		    }
		    topN = candidateHeap.peekTopN(n);
		    for(int j = 0; j < topN.size(); j++) {
			System.out.println(topN.get(j));
		    }
		}
	    }
	}
	catch(NumberFormatException e) {
	    System.out.println("Please enter an integer after flag to print out the top candidates");
	}
    }
}
