/* Name: Sarah Coufal                                                           
 * File: ArrayBinaryTree.java                                                  
 * Desc:                                                                        
 *                                                                              
 * This class creates a generic array binary tree, which requires       
 * a comparable object and implements the BinaryTree interface.                 
 *                                                                              
 */

import java.util.*;

public class ArrayBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {

    protected ArrayList<E> nodes = new ArrayList<>(); //array list that tree is based on

    /** Access to the root element of the tree                                  
     * @return the element of the root node                                     
     */
    public E getRootElement() {
	if(isEmpty()) {
	    return null;
	}
	return nodes.get(0);
    }
    
    /** Returns the number of elements in the tree                              
     * @return number of elements in the tree                                   
     */
    public int size() {
	return nodes.size();
    }

    /** Checks whether the tree is empty or not                            
     * @return true if the tree is empty, false otherwise                              
     */
    public boolean isEmpty() {
	return size() == 0;
    }

    /** Adds elements to the tree                                               
     * @param element The element to be inserted                                
     */
    public void insert(E element) {
	nodes.add(size(), element);
    }

    /** Swaps positions of two elements of the tree                                         
     * @param i The index of the first element
     * @param j The index of the second element
     */
    protected void swap(int i, int j) {
	E temp = nodes.get(i);
	nodes.set(i, nodes.get(j));
	nodes.set(j, temp);
    }

    /** Removes an element from the tree
     * @param element The element to be removed
     * @return true if the element is removed from the tree, false otherwise
     */   
    public boolean remove(E element) {
	for(int i = 0; i < size(); i++) {
	    if(nodes.get(i) == element) {
		//swap with last element and remove last element
		swap(i, size()-1);
		nodes.remove(size()-1);
		return true;
	    }
	}
	return false;
    }

    /** Calculates the index of the parent based on child's index
     * @param i The index of the child in the array list
     * @return The index of the parent
     */  
    protected int parent(int i) {
	return (i-1)/2;
    }

    /** Calculates the index of the left child of the parent
     * @param i The index of the parent in the array list
     * @return The index of the left child
     */ 
    protected int left(int i) {
	return (2*i)+1;
    }

    /** Calculates the index of the right child of the parent
     * @param i The index of the parent in the array list
     * @return The index of the right child
     */ 
    protected int right(int i) {
	return (2*i)+2;
    }

    /** Finds the index of a given element if it exists in the tree
     * @param element The element being searched for
     * @return The index of the element in the array list, or -1 if it 
     * is not there
     */ 
    protected int containsIdx(E element) {
	for(int i = 0; i < size(); i++) {
	    if(nodes.get(i).equals(element)) {
		return i;
	    }
	}
	return -1;
    }

    /** Checks whether an element is in the tree or not
     * @param element The element being searched for
     * @return true if the element is in the tree, false otherwise
     */ 
    public boolean contains(E element) {
	int i = containsIdx(element);
	if(i == -1) {
	    return false;
	}
	else {
	    return true;
	}
    }

    /** Textual representation of tree in order of the array list                                
     * @return String representation of tree                                    
     */
    public String toStringBreadthFirst() {
	if(isEmpty()) {
	    return null;
	}
	String s = "(";
	for(int i = 0; i < size()-1; i++) {
	    s += nodes.get(i) + ", ";
	}
	s += nodes.get(size()-1) + ")";
	return s;
    }

    /** Recursive helper method for toStringInOrder                             
     * @param i The current index of the subtree                              
     * @return String representation of tree                                    
     */
    private String toStringInOrderRec(int i) {
	String s = "";
	if(i < size()) {
	    s += toStringInOrderRec(left(i));
	    s += nodes.get(i) + ", ";
	    s += toStringInOrderRec(right(i));
	}
	return s;
    }

    /** Textual representation of tree in order                                 
     * @return String representation of tree                                    
     */
    public String toStringInOrder() {
	if(isEmpty()) {
	    return null;
	}
	String string = "(" + toStringInOrderRec(0);
	int index = string.lastIndexOf(',');
	string = string.substring(0, index);
	string += ")";
	return string;
    }

    /** Recursive helper method for toStringPreOrder                            
     * @param i The current index of the subtree                               
     * @return String representation of tree                                    
     */
    private String toStringPreOrderRec(int i) {
	String s = "";
	if(i < size()) {
	    s += nodes.get(i) + ", ";
	    s += toStringPreOrderRec(left(i));
	    s += toStringPreOrderRec(right(i));
	}
	return s;
    }

    /** Textual representation of tree, starting with the root                  
     * @return String representation of tree                                    
     */
    public String toStringPreOrder() {
	if(isEmpty()) {
	    return null;
	}
	String string = "(" + toStringPreOrderRec(0);
	int index = string.lastIndexOf(',');
	string = string.substring(0, index);
	string += ")";
	return string;
    }

    /** Recursive helper method for toStringPostOrder                           
     * @param i The current index of the subtree                                   
     * @return String representation of tree                                    
     */
    private String toStringPostOrderRec(int i) {
	String s = "";
	if(i < size()) {
	    s += toStringPostOrderRec(left(i));
	    s += toStringPostOrderRec(right(i));
	    s += nodes.get(i) + ", ";
	}
	return s;
    }

    /** Textual representation of tree, ending with the root                    
     * @return String representation of tree                                    
     */
    public String toStringPostOrder() {
	if(isEmpty()) {
	    return null;
	}
	String string = "(" + toStringPostOrderRec(0);
	int index = string.lastIndexOf(',');
	string = string.substring(0, index);
	string += ")";
	return string;
    }

    /** toString method which prints out all three traversals                   
     * @return String representation of tree                                    
     */
    public String toString() {
	String s = "Tree:\n";
	s += toStringPreOrder() + "\n";
	s += toStringInOrder() + "\n";
	s += toStringPostOrder() + "\n";
	return s;
    }
    
}
