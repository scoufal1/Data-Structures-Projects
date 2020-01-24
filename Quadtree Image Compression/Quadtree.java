/* Name: Sarah Coufal and Shaili Regmi
 * File: Quadtree.java
 * Desc:
 *
 * The Quadtree class for Assignment 9.
 *
 * This file takes in an image file, creates a 2D array representation of the
 * image and uses a quadtree based decomposition to compress the image or 
 * apply the edge detection filter to it. 
 */

import java.io.*;
import java.util.*;

public class Quadtree {

    // The threshold for edge detection; bigger pixels are colored black
    public static final int EDGE_THRESHOLD = 10000;

    //The RGB value for black
    public static final int BLACK = 0;
    
    // Nested Node class
    private class Node {

	// Instance Variables:
	private Color color; // The average color of all pixels in the node
	private Node[] children; // Stores all four children of the node
	private int c1; // The 1st co-ordinate to traverse height of image
	private int c2; // The 2nd co-ordinate to traverse width of image
	private int height; // The height of the node
	private int width; // The width of the node
	
	// Constructor:
	public Node(int c1, int c2, int height, int width) {
	    this.c1 = c1;
	    this.c2 = c2;
	    this.height = height;
	    this.width = width;
	    children = new Node[4];
	}
	
	/** Checks whether the node is a leaf.
	 * @return true if it is a leaf, else false.
	 */
	private boolean isLeaf() {
	    return children[0] == null && children[1] == null
		&& children[2] == null && children[3] == null;
	}
	    
	// Accessor Methods:
	private Color getColor() {
	    return color;
	}
	private int getC1() {
	    return c1;
	}
	private int getC2() {
	    return c2;
	}
	private Node[] getChildren() {
	    return children;
	}
	private int getHeight() {
	    return height;
	}
	private int getWidth() {
	    return width;
	}
	
	// Setter Method:
	private void setColor(Color color) {
	    this.color = color;
	}
    } // End  of nested Node class

    // Instance Variables of the Quadtree class:
    private int height;
    private int width;
    private Node root;
    private double threshold; // Threshold that determines when division stops
    private Color[][] image; // The original image
    private Color[][] leaves; // A 2-D array to store output image
    private Color[][] edgeDetectImg; // Stores result of edge detected image
    private int leavesNum; // The number of leaves in the quadtree

    public int getLeavesNum() {
	return leavesNum;
    }
    // Constructor that takes in the file name or path and the threshold:
    public Quadtree(String fileName, double threshold)
	throws FileNotFoundException {
	this.threshold = threshold;
	ReadFile imageFile = new ReadFile(fileName);
	height = imageFile.getHeight();
	width = imageFile.getWidth();
	this.image = imageFile.getImage();
	root = divideRec(image, 0, 0, height, width);
	leaves = new Color[height][width];
	edgeDetectImg = new Color[height][width];
    }

    // Accessor Methods:
    public int getHeight() {
	return height;
    }
    public int getWidth() {
	return width;
    }
    public double getThreshold() {
	return threshold;
    }
    public Color[][] getLeaves() {
	traverse();
	return leaves;
    }
    public Color[][] getEdgeDetectImg() {
	edgeDetect(root);
	return edgeDetectImg;
    }
    public Color[][] getImage() {
	return image;
    }

    /** Recursive method that divides a node of the quadtree into its children
     * until the nodes reach a single pixel or the given threshold.
     * @param image The 2-D array that represents part of the image in the node
     * @param c1 The 1st coordinate of the parent node along the height of image
     * @param c2 The 2nd coordinate of the parent node along the width of image
     * @param height The height of the parent node
     * @param width The width of the parent node
     * @return The current parent node
     */
    private Node divideRec(Color[][] image, int c1, int c2, int height,
			   int width) {
	Node node = new Node(c1, c2, height, width);
	Color color;
	double threshold = getThreshold();
	if (height == 1 || width == 1) { // Reached single pixel
	    node.setColor(image[c2][c1]); // Set avg color as the pixel color
	} else {
	    Color avgColor = calculateAverage(image, c1, c2, height, width);
	    double meanSquaredError = calculateError(avgColor, image, c1,
						     c2, height, width);
	    if (meanSquaredError < threshold) {
		node.setColor(avgColor); //Set avg color as threshold is reached
	    } else { // Divide into more children
		
		// Height and Width for new children
		int newHeight = height / 2;
		int newWidth = width / 2;
		
		// Quadrant 1 of the image:   
		node.getChildren()[0] = divideRec(image, c1, c2 + newWidth,
						  newHeight, width - newWidth);
		// Quadrant 2 of the image:  
		node.getChildren()[1] = divideRec(image, c1, c2,
						  newHeight, newWidth);
		// Quadrant 3 of the image:  
		node.getChildren()[2] = divideRec(image, c1 + newHeight, c2,
						  height - newHeight, newWidth);
		// Quadrant 4 of the image:  
		node.getChildren()[3] = divideRec(image, c1 + newHeight,
						  c2 + newWidth,
						  height - newHeight,
						  width - newWidth);
		}
	}
	return node;
    }

    /** Calculates the average color of a node
     * @param image The 2D array that represents part of the image in the node
     * @param c1 The 1st coordinate of node along the height of image
     * @param c2 The 2nd coordinate of node along the width of image
     * @param height The height of the node
     * @param width The width of the node
     * @return The average color of all pixels in the node
     */
    private Color calculateAverage(Color[][] image, int c1, int c2,
				   int height, int width) {
	double red = 0;
	double green = 0;
	double blue = 0;
	
	for(int i=c1; i < c1 + height; i++) {
	    for(int j=c2; j < c2 + width; j++) {
		red += image[j][i].getRed();
		green += image[j][i].getGreen();
		blue += image[j][i].getBlue();
	    }
	}
	
	double total = height * width;
	int avgRed = (int)(red / total);
       	int avgGreen = (int)(green / total);
	int avgBlue = (int)(blue / total);
	return new Color(avgRed, avgGreen, avgBlue);	
    }

    /** Calculates the average of the cumulative mean squared error
     * @param avgColor The average color of the node
     * @param image The 2D array repesenting the part of image in the node
     * @param c1 The 1st coordinate of node along the height of image
     * @param c2 The 2nd coordinate of node along the width of image
     * @param height The height of the node
     * @param width The width of the node
     * @return The average of the squared Euclidean distance between the mean
     * color and all original pixel colors in the node
     */
    private double calculateError(Color avgColor, Color[][] image, int c1,
				  int c2, int height, int width) {
	Color color;
	double meanSqr = 0;
       
	for(int i=c1; i < c1 + height; i++) {
	    for(int j=c2; j < c2 + width; j++) {
		color = image[j][i];
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		meanSqr = meanSqr + (Math.pow((red - avgColor.getRed()), 2) +
				   Math.pow((green - avgColor.getGreen()), 2) +
				     Math.pow((blue - avgColor.getBlue()), 2));
	    }
	}
	meanSqr = meanSqr / (Math.pow(height, 2));
	return meanSqr;
    }

    /** Traverses the quadtree to obtain the leaves of the tree that is used to
     * reconstruct the final image
     */
    private void traverse() {
       	traverseRec(root, leaves);
    }

    /** Recursive helper method to traverse the tree in order to obtain the 
     * leaves.
     * @param node The root of the tree or subtree
     * @param leaves The array to represent the pixels in the leaves
     */
    private void traverseRec(Node node, Color[][] leaves) {
	if (!node.isLeaf()) {
	    traverseRec(node.getChildren()[0], leaves);
	    traverseRec(node.getChildren()[1], leaves);
	    traverseRec(node.getChildren()[2], leaves);
	    traverseRec(node.getChildren()[3], leaves);
	} else {
	    leavesNum++;
	    for(int i=node.getC1(); i < node.getC1() + node.getHeight(); i++) {
		for(int j=node.getC2(); j < node.getC2() + node.getWidth()
			; j++) {
		    leaves[j][i] = node.getColor();
		}
	    }
	}	
    }

    /** Outlines the leaves of the quadtree.
     */
    public void drawOutline(Color[][] image) {
	drawOutlineRec(root, image);
    }

    /** Recursive helper method to outline the leaves of the quadtree.
     * @param node The root of the tree or subtree
     * @param leaves The array to represent the pixels in the leaves
     */
    private void drawOutlineRec(Node node, Color[][] leaves) {
	if (!node.isLeaf()) {
	    drawOutlineRec(node.getChildren()[0], leaves);
	    drawOutlineRec(node.getChildren()[1], leaves);
	    drawOutlineRec(node.getChildren()[2], leaves);
	    drawOutlineRec(node.getChildren()[3], leaves);
	} else {                  
	    for(int i=node.getC1(); i < node.getC1() + node.getHeight(); i++) {
		for(int j=node.getC2(); j < node.getC2() + node.getWidth();
		    j++) {
		    if(i == node.getC1() || j == node.getC2() ||
		       i == node.getC1() +
		       node.getWidth() || j == node.getC2() +
		       node.getHeight()) {
			leaves[j][i] = new Color(255, 255, 255);
		    }
		}
	    }
	}	
    }

    /** Calculates the compression ratio of the new image with respect to
     * the original image.
     * @return The number of leaves in the tree divided by the total number of
     * pixels in the original image.
     */
    public double compressionRatio() {
	 double leavesNumber = (double)leavesNum;
	 double compressionRatio = leavesNumber / (getHeight() * getWidth());
	 return compressionRatio;
     }
    
    /** The recursive method for edge detection filter that sets the color 
     * of sufficiently large nodes to black.
     * @param node The root of the tree or subtree
     */
    private void edgeDetect(Node node) {
	if (!node.isLeaf()) {
	    edgeDetect(node.getChildren()[0]);
	    edgeDetect(node.getChildren()[1]);
	    edgeDetect(node.getChildren()[2]);
	    edgeDetect(node.getChildren()[3]);
	} else {                                                                
	    if(node.getHeight() * node.getWidth() > EDGE_THRESHOLD) {
		setBlack(node);
	    }
	    else {
		edgeDetection(node);
	    }
	}
    }

    /** Helper method for edge detection that sets the color of a node as 
     * black.
     * @param node The node which should be set black.
     */
    private void setBlack(Node node) {
	for(int i=node.getC1(); i < node.getC1() + node.getHeight(); i++) {
	    for(int j=node.getC2(); j < node.getC2() + node.getWidth();
		j++) {
		edgeDetectImg[j][i] = new Color(BLACK, BLACK, BLACK);
	    }
	}
    }

    /** The edge detection filter that calculates the color required for
     * edge detection and sets this color in the edge detected image.
     * @param node The node at which filter should be applied.
     */
    private void edgeDetection(Node node) {	
	Color newColor;
	int red;
	int green;
	int blue;

	int left;
	int right;
	int up;
	int down;
	for(int j=node.getC1(); j < node.getC1() + node.getHeight(); j++) {
	    for(int i=node.getC2(); i < node.getC2() + node.getWidth();
		    i++) {
		left = i-1;
		right = i+1;
		up = j-1;
		down = j+1;

		if(left < 0) {
		    left = 0;
		}
		if(up < 0) {
		    up = 0;
		}
		if(right >= image.length) {
		    right = image.length-1;
		}
		if(down >= image[0].length) {
		    down = image[0].length-1;
		}
		
		red = -1*image[left][up].getRed() - image[i][up].getRed() -
		    image[right][up].getRed() - image[left][j].getRed() +
		    8*image[i][j].getRed() - image[right][j].getRed() -
		    image[left][down].getRed() - image[i][down].getRed() -
		    image[right][down].getRed();
		if(red < 0) {
		    red = 0;
		}
		green = -1*image[left][up].getGreen() - image[i][up].getGreen()
		    - image[right][up].getGreen() - image[left][j].getGreen() +
		    8*image[i][j].getGreen() - image[right][j].getGreen() -
		    image[left][down].getGreen() - image[i][down].getGreen() -
		    image[right][down].getGreen();
		if(green < 0) {
		    green = 0;
		}

		blue = -1*image[left][up].getBlue() - image[i][up].getBlue() -
		    image[right][up].getBlue() - image[left][j].getBlue() +
		    8*image[i][j].getBlue() - image[right][j].getBlue() -
		    image[left][down].getBlue() - image[i][down].getBlue() -
		    image[right][down].getBlue();
		if(blue < 0) {
		    blue = 0;
	        }

		newColor = new Color(red, green, blue);
		edgeDetectImg[i][j] = newColor;
	    }
	}
    }
}
			
		    


