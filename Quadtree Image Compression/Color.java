/* Name: Sarah Coufal and Shaili Regmi
 * File: Color.java
 * Desc:
 *
 * The Color class for Assignment 9.
 *
 * This file creates Color objects that stores the red, green, and blue color
 * components of the pixels of an image.
 *
 */

public class Color {

    // Instance Variables:
    private int red;
    private int green;
    private int blue;

    // Constructor:
    public Color(int red, int green, int blue) {
	this.red = red;
	this.green = green;
	this.blue = blue;
    }

    // Accessor Methods:
    public int getRed() {
	return red;
    }
    public int getGreen() {
	return green;
    }
    public int getBlue() {
	return blue;
    }

    // Setter Methods:
    public void setRed(int red) {
	this.red = red;
    }
    public void setGreen(int green) {
	this.green = green;
    }
    public void setBlue(int blue) {
	this.red = red;
    }

    /** Returns a string representation of the color objects
     * @return the concatenated string with the red, green, and blue components
     */
    public String toString() {
	return red + " " + green + " " + blue;
    }
}
