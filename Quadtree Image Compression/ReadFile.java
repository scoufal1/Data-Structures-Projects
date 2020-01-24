/* Name: Sarah Coufal and Shaili Regmi
 * File: ReadFile.java
 * Desc:
 *
 * The ReadFile class for Assignment 9.
 *
 * This file takes a file name or path of an image .ppm file and creates a
 * representation of the pixels in the file using Color objects and a 2-D
 * array that stores the Color objects.
 *
 */

import java.util.*;
import java.io.*;

public class ReadFile {

    // Instance variables:
    private int height;
    private int width;
    private Color[][] image;
    private int max; // The maximum int for color representation

    /** Constructor that reads an input file and converts the pixels into an
     * image representation using 2D array.
     * @param fileName The name or path of the file.
     */
    public ReadFile(String fileName) throws FileNotFoundException {

	Scanner input = new Scanner(new File(fileName));
        while(!input.hasNextInt()) {
            input.next();
        }

        width = input.nextInt();
        height = input.nextInt();
	max = input.nextInt();

        int red;
        int green;
        int blue;
        Color color;
        image = new Color[height][width];

	// The indices for the 2-D array
        int y = 0;
        int x = 0;

        while(input.hasNextInt()) {
            if(x == width) {
                x = 0;
                y++;
            }
            red = input.nextInt();
            green = input.nextInt();
            blue = input.nextInt();
            color = new Color(red, green, blue);
            image[y][x] = color;
	     x++;
        }
    }

    // Accessor Methods:
    public int getHeight() {
	return height;
    }
    public int getWidth() {
	return width;
    }
    public Color[][] getImage() {
	return image;
    }
    public int getMax() {
	return max;
    }
}
   
