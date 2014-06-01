package ca.etsmtl.log430.lab1.services;


/**
 * This class is used by various other classes that need to keep a list of
 * resources on hand. It extends the List class which provides the basic
 * functionality for storage and retrieval of the Resource Object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2014-May-07
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.7, S. Abraham  , 2014-May-07 - Formated source code for easier reading.
 * 
 * v1.6, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * **************************************************************************
 */

public class ResourceList extends List<Resource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7923181622317924870L;

	/**
	 * Adds a new resource to the list. All the issues of casting are taken care
	 * of within this class.
	 * 
	 * @param resource
	 */
	public void addResource(Resource resource) {
		add(resource);
	}


	/**
	 * Determines whether the Resource object is currently in the list.
	 * 
	 * @param resource
	 * @return true if the resource is in the list, false otherwise.
	 */

	public boolean findResource(Resource resource) 
	{
		for (Resource r : this) {
			if (resource.getID().compareToIgnoreCase(r.getID()) == 0) 
			{
				return true;
			} // if
		}
		
		return false;
	} // findResource

	/**
	 * Finds a resource in a list using the resourceID as the search key.
	 * 
	 * @param resourceID
	 * @return if the current list object's resourceID matches the resourceID
	 *         passed to the method, the Resource object is returned to the
	 *         caller. Otherwise, returns null.
	 */

	public Resource findResourceByID(String resourceID) 
	{
		for (Resource r : this) {
			if (r.getID().compareTo(resourceID) == 0) 
			{
				return r;

			} // if

		}
		
		return null;
				
	} // findResourceByID

} // ResourceList