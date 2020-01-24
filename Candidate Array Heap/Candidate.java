/* Name: Sarah Coufal
 * File: Candidate.java
 * Desc:
 *
 * This class creates a comparable object that stores the last name, full name,
 * and polling percentage of a presidential candidate.
 *
 */

public class Candidate implements Comparable<Candidate> {

    private String lastName; //last name of candidate
    private String fullName; //full name of candidate
    private double percentage; //polling percentage of candidate

    /** Creates a Candidate object with given last name, full name, and
     * percentage
     * @param lastName The last name of the candidate
     * @param fullName The full name of the candidate
     * @param percentage The polling percentage of the candidate
     */
    public Candidate(String lastName, String fullName, double percentage) {
	this.lastName = lastName;
	this.fullName = fullName;
	this.percentage = percentage;
    }

    //getters
    public String getLastName() {
	return lastName;
    }
    public String getFullName() {
	return fullName;
    }
    public double getPercentage() {
	return percentage;
    }

    //setters
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    /** Makes Candidate objecs comparable based on their polling percentage
     * @param c the Candidate object to be compared to
     * @return An integer value that represents whether a Candidate is less
     * than, greater than, or equal to another Candidate
     */
    @Override
    public int compareTo(Candidate c) {
	if(getPercentage() < c.getPercentage()) {
	    return 1;
	}
	if(getPercentage() > c.getPercentage()) {
	    return -1;
	}
	else {
	    return 0;
	}
    }

    /** Makes Candidate objecs equal based on their last name
     * @param o the Candidate object to be compared to
     * @return True if the last name is the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
	Candidate other = (Candidate) o;
	return getLastName().equals(other.getLastName());
    }
    
    /** toString method for Candidate
     * @return String representation of Candidate object
     */
    @Override
    public String toString() {
	String s = fullName + ":" + percentage;
	return s;
    }   
}
