/* Name: Sarah Coufal
 * File: Place.java
 * Desc:
 *
 * This class creates a Place object, which stores the zipcode, town, and state
 * of a given Place. Subclasses of Place include additional information.
 *
 */

public class Place implements Comparable {
    
    private String zip; //The 5-digit zip code
    private String town; //The town name
    private String state; //The state abbreviation
    
    /** Creates a Place with the given zip, town name, and
     * state
     * @param zip The 5-digit zip code
     * @param town The town name
     * @param state The state abbreviation
     */
    public Place(String zip, String town, String state) {
	this.zip = zip;
	this.town = town;
	this.state = state;
    }

    //getter methods
    public String getZip() {
	return zip;
    }

    public String getTown() {
	return town;
    }

    public String getState() {
	return state;
    }

    //setter methods
    public void setZip(String zip) {
	this.zip = zip;
    }

    public void setTown(String town) {
	this.town = town;
    }

    public void setState(String state) {
	this.state = state;
    }

     /** toString method for Place
     * @return town and state of Place
     */
    public String toString() {
	return town + ", " + state;
    }

    /** Makes Place objects comparable based on their zipcodes
     * @param o The Place object to be compared to
     * @return An integer value that represents whether a Place is
     * less than, greater than, or equal to another Place
     */
    public int compareTo(Object o) {
	Place other = (Place) o;
	int compare = getZip().compareTo(other.getZip());
	return compare;
    }
}
