/* Name: Sarah Coufal
 * File: Person.java
 * Desc:
 *
 * This class creates a comparable object that stores relevant information from a 
 * single row of the CSV file.
 *
 */

public class Person implements Comparable {
    
    private String sex; //sex of the person
    private String race; //race of the person
    private int dob; //date of birth of the person
    private int age; //age of the person
    private String height; //height in feet and inches of the person
    private int weight; //weight of the person
    private String hairColor; //hair color of the person
    private String eyeColor; //eye color of the person

    /** Creates a Person object with relevant information
     * @param sex The sex of the person
     * @param race The race of the person
     * @param dob The date of birth of the person
     * @param age The age of the person
     * @param height The height of the person
     * @param weight The weight of the person
     * @param hairColor The hair color of the person
     * @param eyeColor The eye color of the person
     */
    public Person(String sex, String race, int dob, int age, String height, int weight,
		  String hairColor,String eyeColor) {
	this.sex = sex;
	this.race = race;
	this.dob = dob;
	this.age = age;
	this.height = height;
	this.weight = weight;
	this.hairColor = hairColor;
	this.eyeColor = eyeColor;
    }
    
    //getters
    public String getSex() {
	return sex;
    }
    
    public String getRace() {
	return race;
    }
    
    public int getDob() {
	return dob;
    }
    
    public int getAge() {
	return age;
    }

    public String getHeight() {
	return height;
    }

    public int getWeight() {
	return weight;
    }

    public String getHairColor() {
	return hairColor;
    }
    
    public String getEyeColor() {
	return eyeColor;
    }

    /** Makes Person objecs comparable based on their toString, therefore all of their
     * instance variables.
     * @param o the Person object to be compared to
     * @return An integer value that represents whether a Person is less
     * than, greater than, or equal to another Person
     */
    public int compareTo(Object o) {	
	Person other = (Person) o;
	int compare = toString().compareTo(other.toString());
	return compare;
    }
    
    /** Override of equals method to define equality as the objects' toStrings being
     * the same
     * @param o the Person object to be compared to
     * @return true if the objects are the same, false otherwise
     */
    public boolean equals(Object o) {
	Person other = (Person) o;
	return toString().equals(other.toString());
    }
    
        
    /** toString method for Person
     * @return String representation of Person object
     */
    public String toString() {
	return sex + ", " + race + ", " + dob + ", " + age + ", " + height + ", "
	    + weight + ", " + hairColor + ", " + eyeColor + "\n";
    }

        
    /** Override of hashCode method so that hashcode is determined by toString
     * @return integer value to be used for hashcode in hashmap
     */
    public int hashCode() {
	return toString().hashCode();
    }
    
}
