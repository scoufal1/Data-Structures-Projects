/* Name: Sarah Coufal                                                           
 * File: ArrayHeap.java                                                  
 * Desc:                                                                        
 *                                                                              
 * This class creates a generic array heap, which is a subclass of ArrayBinaryTree
 * and implements the PriorityQueue interface.                 
 *                                                                              
 */

import java.util.*;

public class ArrayHeap<E extends Comparable<E>> extends ArrayBinaryTree<E> implements PriorityQueue<E> {

    /** Adds elements to the heap
     * @param element The element to be inserted
     */
    public void insert(E element) {

	//replace element with most current if it is already in heap
	if(this.contains(element)) {

	    int index = containsIdx(element);
	    nodes.set(index, element);
	    upHeap(index);
	    downHeap(index);
	}
	
        else {
	    //add element to end and upheap if necessary
	    nodes.add(size(), element);
	    upHeap(size()-1);
	}
    }

    /** Swaps child with parent if needed to maintain heap structure
     * @param i The index of the array list to begin upheap
     */
    private void upHeap(int i) {
	E child;
	int compare;
	
	while(i > 0 && i < size()) {
	    //compare child with parent
	    child = nodes.get(i);
	    compare = child.compareTo(nodes.get(parent(i)));

	    if(compare >= 0) {
		break;
	    }
	    
	    //swap if child is less than parent
	    if(compare < 0) {
		swap(i, parent(i));
	    }
	    //continue from parent's index
	    i = parent(i);				  
	}
    }

    /** Swaps parent with smaller child if needed to maintain heap structure
     * @param i The index of the array list to begin downheap
     */
    private void downHeap(int i) {
	int compareChildren;
	int smallerChild;
	int compare;

	//while current index has a left child
	while((left(i)) < size()) {
	    //assign left child's index to smaller child's index
	    smallerChild = left(i);
	    //if it has a right child, compare the right and left children
	    if((right(i)) < size()) {
		compareChildren = nodes.get(left(i)).compareTo(nodes.get(right(i)));
		//if the left child is bigger than the right, right child is the smaller child
		if(compareChildren > 0) {
		    smallerChild = right(i);
		}
	    }
	    //compare parent to smaller child and swap if necessary
	    compare = nodes.get(i).compareTo(nodes.get(smallerChild));
	    if(compare <= 0) {
		break;
	    }
	    if(compare > 0) {
		swap(i, smallerChild);
	    }
	    //continue from smaller child's index
	    i = smallerChild;
	}
    }

    /** Removes an element from the heap
     * @param element The element to be removed
     * @return true if the element is removed from the tree, false otherwise
     */   
    public boolean remove(E element) {
	
	for(int i = 0; i < size(); i++) {
	    if(nodes.get(i).equals(element)) {
		//swap with last element, remove last element
		swap(i, size()-1);
		nodes.remove(size()-1);
		//upheap and downheap as necessary
		upHeap(i);
		downHeap(i);
		return true;
	    }
	}
	return false;
    }

    /** Checks the minimum element of the heap
     * @return the element with the minkey, or null when PQ is empty
     */ 
    public E peek() {
	return getRootElement();
    }

    /** Removes and returns the minimum element of the heap
     * @return the element with the minkey, or null when PQ is empty
     */ 
    public E poll() {
	E root = getRootElement();
	remove(root);
	return root;
    }

    /** Creates and returns an arraylist of the top n elements in the current heap
     * @param n The number of elements to put in the array list
     * @return an array list of the top n elements in the heap
     */ 
    public ArrayList<E> peekTopN(int n) {
	
	ArrayList<E> copy = new ArrayList<>();

	//if input number is greater than total number of candidates, print message
	if(n > size()) {
	    System.out.println("There are less than " + n + " candidates in files\n");
	    System.out.println("Top " + size() + " Candidates:");
	}
	int compare;
	//if n is 1 simply return root element
	if(n == 1) {
	    copy.add(0, peek());
	    return copy;
	}

	//if n is 2, compare children and return smaller child
	if(n == 2) {
	    copy.add(0, peek());
	    int smallerChild = 0;
	    int compareChildren = 0;
	    //if there is a left child
	    if(size() > 1) {
		smallerChild = left(0);
		//if there is a right child
		if(size() > 2) {
		    compareChildren = nodes.get(left(0)).compareTo(nodes.get(right(0)));
		    if(compareChildren > 0) {
			smallerChild = right(0);
		    }
		}
		copy.add(1, nodes.get(smallerChild));
	    }
	    return copy;
	}

	//copy array list
	for(int i = 0; i < size(); i++) {
	    copy.add(i, nodes.get(i));
	}

	//sort copied array list
	Collections.sort(copy);

	//remove extra elements
	int size = copy.size();
	for(int i = 0; i < size-n; i++) {
	    copy.remove(copy.size()-1);
	       
	}
	return copy;
    }
}
