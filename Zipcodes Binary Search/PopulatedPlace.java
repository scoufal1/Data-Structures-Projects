/* Name: Sarah Coufal
 * File: PopulatedPlace.java
 * Desc:
 *
 * Subclass of LocatedPlace, contains all LocatedPlace information as well as population 
 * information.
 *
 */

public class PopulatedPlace extends LocatedPlace {

    private int population; //The population of area
    
    /** Creates a PopulatedPlace with the given zip, town name,
     * state, latitude, longitude, and population
     * @param zip The 5-digit zip code
     * @param town The town name
     * @param state The state abbreviation
     * @param latitude  The latitude coordinate
     * @param longitude The longitude coordinate
     * @param population The population of area
     */ 
    public PopulatedPlace(String zip, String town, String state, double latitude,
			  double longitude, int population) {
	 super(zip, town, state, latitude, longitude);
	 this.population = population;
    }

    //getter method
    public int getPopulation() {
	return population;
    }

    //setter method
    public void setPopulation(int population) {
	this.population = population;
    }
    
    /**toString method for PopulatedPlace
     * @return town, state, latitude, longitude, and population of Place
     */
    public String toString() {
	return super.toString() + " " + population;
    }
}
			      
