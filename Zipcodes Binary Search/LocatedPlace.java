/* Name: Sarah Coufal
 * File: LocatedPlace.java
 * Desc:
 *
 * Subclass of Place, contains all Place information as well as location 
 * information (latitude and longitude).
 *
 */

public class LocatedPlace extends Place {
    
    private double latitude; //The latitude coordinate
    private double longitude; //The longitude coordinate

    /** Creates a LocatedPlace with the given zip, town name,
     * state, latitude, and longitude
     * @param zip The 5-digit zip code
     * @param town The town name
     * @param state The state abbreviation
     * @param latitude  The latitude coordinate
     * @param longitude The longitude coordinate
     */ 
    public LocatedPlace(String zip, String town, String state, double latitude,
			double longitude) {
	super(zip, town, state);
	this.latitude = latitude;
	this.longitude = longitude;
    }

    //getter methods
    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    //setter methods
    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }
    
    /** toString method for LocatedPlace
     * @return town, state, latitude, and longitude of Place
     */
    public String toString() {
	return super.toString() + " " + latitude + " " + longitude;
    } 
}
