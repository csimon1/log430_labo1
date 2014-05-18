package ca.etsmtl.log430.lab1;


/**
 * This class is used by various other classes that need to keep a list of
 * projects. It extends the List class which provides the basic functionality for
 * storage and retrieval of the Project object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2014-May-07
 */

/*
 * Modification Log
 * ****************************************************************************
 * v1.7, S. Abraham  , 2014-May-07 - Added comments.
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
 * ***************************************************************************
 */

public class ProjectList extends List<Project> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3023403675352237422L;


	/**
	 * Default Constructor and call the parent class's constructor.
	 */
	public ProjectList() {
		super();
	}

	/**
	 * @param project
	 *            New project to be added to the list. All the issues of casting
	 *            are taken care of within this class.
	 */
	public void addProject(Project project) {
		add(project);
	}

	/**
	 * This method assumes that all projects have different identification
	 * numbers.
	 * 
	 * @param project
	 * @return A Project instance if found in the list based on specified
	 *         criteria, null otherwise.
	 */
	public boolean findProject(Project project) {


		for (Project p : this) {
			if (project.getID().compareToIgnoreCase(p.getID()) == 0) 
			{
				return true;

			} // if

		}
		
		return false;
	}


	/**
	 * This function iterates throughout the list of project and return the 
	 * project if it exist or null if not.
	 * @param id the project ID
	 * @return the Project object or null
	 */
	public Project findProjectByID(String id){
		for (Project p : this) {
			if (id.compareToIgnoreCase(p.getID()) == 0) 
			{
				return p;
			} // if
		}
		
		return null;
	}

} // ProjectList