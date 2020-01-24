/* Name: Sarah Coufal
 * File: NameDLL.java
 * Desc:
 *
 * This class creates a doubly linked list of Name objects with
 * standard doubly linked list methods. It also contains methods
 * used to traverse the list such as insert a name alphabetically 
 * and find a Name object in the list.
 * 
 */

import java.io.*;
import java.util.*;

public class NameDLL {
    
    private static class Node {
	private Name data;
	private Node prev;
	private Node next;

	//constructor
	public Node(Name data, Node prev, Node next) {
	    this.data = data;
	    this.prev = prev;
	    this.next = next;
	}

	//getters
	public Name getData() {
	    return data;
	}

	public Node getNext() {
	    return next;
	}

	public Node getPrev() {
	    return prev;
	}

	//setters
	public void setNext(Node next) {
	    this.next = next;
	}

	public void setPrev(Node prev) {
	    this.prev = prev;
	}
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    public NameDLL() {};

    public int size() {
	return size;
    }
    
     public boolean isEmpty() {
	return size == 0;
    }

    public Name first() {
	if(isEmpty()) {
	    return null;
	}
        else {
	    return head.getData();
	}
    }

    public Name last() {
	if(isEmpty()) {
	    return null;
	}
	 else {
	    return tail.getData();
	 }
    }

    public void addFirst(Name name) {
	Node newest = new Node(name, null, head);
	if(isEmpty()) {
	    head = newest;
	    tail = head;
	}
        else {
	    head.setPrev(newest);
	    head = newest;
	}
	size++;
    }

    public void addLast(Name name) {
	Node newest = new Node(name, tail, null);
	if(isEmpty()) {
	    tail = newest;
	    head = tail;
	}
        else {
	    tail.setNext(newest);
	    tail = newest;
	}
        size++;
    }

    public void addBtw(Name name, Node prev, Node next) {
	Node newest = new Node(name, prev, next);
	prev.setNext(newest);
	next.setPrev(newest);
	size++;
    }

    /** Inserts given Name object into the list before given node
     * @param name The Name object to be inserted
     * @param n The node to insert the Name object before
     * @return none
     */
    public void insertBefore(Name name, Node n) {
	if(isEmpty() || size == 1) {
	    addFirst(name);
	}
	else {	
	    Node predecessor = n.getPrev();
	    Node newest = new Node(name, predecessor, n);
	    if(predecessor != null) {
		newest.setPrev(predecessor);
		predecessor.setNext(newest);
		n.setPrev(newest);
		size++;
	    }
	    else {
		addFirst(name);
	    }
	}
    }
    /** Inserts given Name object into the list alphabetically
     * @param name The Name object to be inserted
     * @return none
     */
    public void insertSorted(Name name) {
	if(isEmpty()) {
	    addFirst(name);
	}
	else {
	    String newName = name.getName();
	    String existingName;
	    int compare;
	    Node n = head;
	    while(true) {
		existingName = n.getData().getName();
		compare = newName.compareTo(existingName);
		if(compare < 0) {
		    insertBefore(name, n);
		    break;
		}
		else {
		    n = n.getNext();
		    if (n == null) {
			addLast(name);
			break;
		    }
		    else if(n.getNext() == null) {
			compare = newName.compareTo(n.getData().getName());
			if(compare < 0) {
			    insertBefore(name, n);
			    break;
			}
			else {
			    addLast(name);
			    break;
			}
		    }
		}
	    }
	}	
    }

    public Name getName(int position) {
	Node n = head;
	int count = 1;
	while(n != null) {
	    if(position == count) {
		return n.getData();
	    }
	    count++;
	    n = n.getNext();
	}
	throw new IndexOutOfBoundsException();
    }

    public int getPosition(Name name) {
	Node node = head;
        int count = 1;
	while(node != null) {
	    if(name.equals(node.getData())) {
		return count;
	    }
	    count++;
	    node = node.getNext();
	}
	throw new IllegalStateException("Name is not in list");
    }

    /** Inserts given Name object into the list before given node
     * @param inputName The string of Name object to be searched for in
     * the list
     * @return The Name object with same name as input string
     */
    public Name findName(String inputName) {
	String foundName;
	Node node = head;
	if(node == null) {
	    return null;
	}
	while(true) {
	    foundName = node.getData().getName();
	    if(foundName.equals(inputName)) {
		return node.getData();
	    }
	    else if(node.getNext() != null) {
		node = node.getNext();
	    }
	    else {
		return null;
	    }
	}
    }

    
    public int calcRank(Name name) {
	Node node = head;
	int nameTotal = name.getNameTotal();
	int count = 1;
	int existingNameTotal;
	while(node != null) {
	    existingNameTotal = node.getData().getNameTotal();
	    if(nameTotal < existingNameTotal) {
	        count++;
		node = node.getNext();
	    }
	    else {
		node = node.getNext();
	    }
	}
	return count;
    }
    
 			     
    public Name removeFirst() {
	if(isEmpty()) {
	    return null;
	}
        Node target = head;
	if(head == tail) {
	    tail = null;
	    head = tail;
	}
        else {
	    head = head.getNext();
	    head.setPrev(null);
	}
        size --;
	return target.getData();
    }

    public Name removeLast() {
	if(isEmpty()) {
	    return null;
	}
        Node target = tail;
	if(head == tail) {
	    tail = null;
	    head = tail;
	}
        else {
	    tail = tail.getPrev();
	    tail.setNext(null);
	}
        size--;
	return target.getData();
    }

    public Name remove(Node n) {
	if(head == n) {
	    removeFirst();
	}
        else if(tail == n) {
	    removeLast();
	}
        else {
	    Node predecessor = n.getPrev();
	    Node successor = n.getNext();
	    predecessor.setNext(successor);
	    successor.setPrev(predecessor);
	}
        return n.getData();
    }

    public String toString() {
	String s = new String();
	for(Node n = head; n!= null; n=n.getNext()) {
	    s = s + n.getData();
	}
	return s;
    }
}
