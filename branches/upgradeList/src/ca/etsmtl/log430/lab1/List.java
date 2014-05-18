package ca.etsmtl.log430.lab1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the base class that is used to construct the various list objects
 * used in this application.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2014-May-18
 */

/* Modification Log
 **************************************************************************************
 * v1.6, 18-May-2014 Charly Simon change to extends ArrayList and implements List<T>
 *
 * v1.5, S. Abraham  , 2014-May-10 - Formated curly bracket for easy reading.
 *
 * v1.4, R. Champagne, 2013-Sep-13 - Various refactorings to make classe
 *       independant of future changes..
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 *  
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS. 
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 **************************************************************************************/

public class List<E> extends ArrayList<E> implements java.util.List<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996553246710229736L;
	/**
	 * Used to index elements in the list.
	 */
	int index; 

	public List() {
		super();
	

	} // Constructor

	
	public List(Collection<? extends E> c) {
		super(c);
	
	} // Constructor
	
	public List(int initialCapacity) {
		super(initialCapacity);
	

	} // Constructor
	
} // List Class
