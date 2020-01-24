/* Name: Sarah Coufal
 * File: Name.java
 * Desc:
 *
 * This class creates Name objects, which store information regarding each name
 * from the files, such as rank, yearly number, year, and name total (which is 
 * calculated in the program).
 * 
 */

import java.util.*;
import java.io.*;

public class Name {
    
    private String name;
    //private ArrayList<Integer> rank;
    //private ArrayList<Integer> number;
    //private ArrayList<Integer> year;
    private ArrayList<Stats> stats;
    private int nameTotal;

    /** Creates a Name with information regarding name, rank, number, year, and 
     * name total
     * @param name The name of baby
     * @param rank List of ranks from all files
     * @param number List of yearly numbers from all files
     * @param year List of years from all files
     * @param nameTotal total number of babies with given name in all files
     * @return An integer total of babies of all names and years for one gender
     */
    public Name(String name, ArrayList<Stats> stats, int nameTotal) {
	this.name = name;
	this.stats = stats;
	this.nameTotal = nameTotal;
    }

    //getters
    public String getName() {
	return name;
    }
    public ArrayList<Stats> getStats() {
	return stats;
    }
    /*
    public ArrayList<Integer> getRank() {
	return rank;
    }

    public ArrayList<Integer> getNumber() {
	return number;
    }

    public ArrayList<Integer> getYear() {
	return year;
    }
    */
    public int getNameTotal() {
	return nameTotal;
    }

    //setters
    public void setName(String name) {
	this.name = name;
    }
    public void setStats(ArrayList<Stats> stats) {
	this.stats = stats;
    }
    /*
    public void setRank(ArrayList<Integer> rank) {
	this.rank = rank;
    }

    public void setNumber(ArrayList<Integer> number) {
	this.number = number;
    }

    public void setYear(ArrayList<Integer> year) {
	this.year = year;
    }
    */
    public void setNameTotal(int nameTotal) {
	this.nameTotal = nameTotal;
    }	
}
