/* Name: Sarah Coufal and Shaili Regmi
 * File: Main.java
 * Desc:
 *
 * The Main driver class for Assignment 9.
 *
 * This program takes command line input to take in an image file,
 * creates a 2D array representation of the image and uses a quadtree based
 * decomposition to compress the image or apply the edge detection filter 
 * based on the user input. It can also draw the quadtree outlines if
 * requested. 
 */

import java.util.*;
import java.io.*;

public class Main {

    // Contants to indicate the various input flags
    public static final String FILE = "-i"; // Input file name or path
    public static final String OUT = "-o"; // Output file name or path
    public static final String COMPRESS = "-c"; // Perform compression
    public static final String EDGE = "-e"; // Perform edge detection
    public static final String OUTLINE = "-t"; // Draw quadtree outline

    // Thresholds to indicate the various compression levels
    public static final double LEVEL1 = 6500;
    public static final double LEVEL2 = 5500;
    public static final double LEVEL3 = 3000;
    public static final double LEVEL4 = 850;
    public static final double LEVEL5 = 170;
    public static final double LEVEL6 = 35;
    public static final double LEVEL7 = 20;
    public static final double LEVEL8 = 1;

    // Threshold of quadtree used for edge detection
    public static final double EDGE_LEVEL = 1000;
    
    public static void main (String[] args) throws FileNotFoundException,
    IOException {
	
	// Variables to store or indicate input data
	String fileName = "";
	String outputName = "";
	boolean edgeDetection = false;
	boolean compress = false;
	boolean outline = false;
	
	for (int i = 0; i < args.length; i++) {
	    if (args[i].equals(FILE)) {
		fileName = args[i+1];
		i++;
	    } else if (args[i].equals(OUT)) {
		outputName = args[i+1];
		i++;
	    } else if (args[i].equals(COMPRESS)) {
		compress = true;
	    } else if (args[i].equals(EDGE)) {
		edgeDetection = true;
	    } else if (args[i].equals(OUTLINE)) {
		outline = true;
	    }
	}
	
	// Checking if the image is a square
	Scanner input = new Scanner(new File(fileName));
        while(!input.hasNextInt()) {
            input.next();
        }
        int width = input.nextInt();
        int height = input.nextInt();
	if (height != width) {
	    System.out.println("Please Enter a square image and try again!");
	    } else {
	    int imgNum = 0; // number of images that are not generated
	    double maxRatio = 0; // maximum compression ratio generated
	    double minRatio = 0; // minimum compression ratio generated
	    boolean possible = true; // tracks whether the image is generated
	    boolean min = true; //tracks the minimum compression ratio generated
	    if (compress == true) {
		double[] levels = {LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5,
				   LEVEL6, LEVEL7, LEVEL8};
		String output = ""; // name for each output file
		int outputNum = 1; //number for output files
		for (int i = 0; i < levels.length; i++) {
		    Quadtree tree = new Quadtree(fileName, levels[i]);

		    output = outputName + "-" + Integer.toString(outputNum) +
			".ppm";
		    Color[][] image = tree.getLeaves();
		    if(tree.getLeavesNum() < 100) {
		        imgNum++;
			possible = false;
		    }
		    else {
			possible = true;
			if (outline == true) {
			    tree.drawOutline(image);
			}
			writeImg(output, image);
			outputNum++;
		    }
		    if (possible == true && min == true) {
			minRatio = tree.compressionRatio();
			min = false;
		    }
		    maxRatio = tree.compressionRatio();
		}
		
		int images = 8 - imgNum;
		if(images < 8) {
		    System.out.println("Compression level only possible between " + minRatio + " and " + maxRatio + ", generating " + images + " compressed images instead of 8");
		}
	    } else if (edgeDetection == true) {
		Quadtree tree = new Quadtree(fileName, EDGE_LEVEL);
		String edgeOutput = outputName + ".ppm";
		Color[][] image = tree.getEdgeDetectImg();
		if (outline == true) {
		    tree.drawOutline(image);
		}
		writeImg(edgeOutput, image);
	    }
	}
    }
    
    /** Writes an image to a file.
     * @param newFile The name for the new file.
     * @param image The 2D array representation of the image to be written
     */
    public static void writeImg(String newFile, Color[][] image)
	throws IOException {
	PrintWriter out = new PrintWriter(newFile);
	out.print("P3 ");
	out.println(image[0].length + " " + image.length + " 255");
	    
	for(int i=0; i < image.length; i++) {
	    for(int j=0; j < image[0].length; j++) {
		out.print(image[i][j].getRed() + " " +
			  image[i][j].getGreen() + " " +
			  image[i][j].getBlue() + " ");
	    }
	    out.println();
	}
	out.close();
    }
}
