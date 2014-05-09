package ca.etsmtl.log430.lab1;


/**
 * This class defines the Resource object for the system. Besides the basic
 * attributes, there are two lists maintained. alreadyAssignedProjectList is a
 * ProjectList object that maintains a list of projects that the resource was
 * already assigned to prior to this execution of the system.
 * projectsAssignedList is also a ProjectList object that maintains a list of
 * projects assigned to the resource during the current execution or session.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2014-May-07
 */

/* Modification Log
 ****************************************************************************
 * v1.7, S. Abraham  , 2014-May-07 - Added comments
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

 ****************************************************************************/

public class Resource 
{
	/**
	 * Resource's last name
	 */
	private String lastName;
	
	/**
	 * Resource's first name
	 */
	private String firstName;
	
	/**
	 * Resource's identification number
	 */
	private String id;
	
	/**
	 * Resource role 
	 */
	private String role;

	/**
	 *  List of projects the resource is already allocated to
	 */
	private ProjectList alreadyAssignedProjectList = new ProjectList();

	/**
	 *  List of projects assigned to the resource in this session
	 */
	private ProjectList projectsAssignedList = new ProjectList();

	/**
	 * Assigns a project to a resource.
	 * 
	 * @param project the project to assign
	 */
	public void assignProject(Project project) 
	{
		getProjectsAssigned().addProject(project);

	}

	/**
	 * This function modifies the last name.
	 * @param lastName the last name to change
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	/**
	 * This function return the last name.
	 * @return the last name
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * This function modifies the first name. 
	 * @param firstName the first name to change
	 */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	/**
	 * This function return the first name.
	 * @return the first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}

	/**
	 * This function modifies the resource ID.
	 * @param id the resource id to change
	 */
	public void setID(String id) 
	{
		this.id = id;
	}

	/**
	 * This function return the resource ID.
	 * @return the resource id
	 */
	public String getID() 
	{
		return id;
	}

	/**
	 * This function modifies the resource's role.
	 * @param role the new role to modifies
	 */
	public void setRole(String role) 
	{
		this.role = role;
	}

	/**
	 * This function returns the resource's role.
	 * @return the role
	 */
	public String getRole() 
	{
		return role;
	}

	/**
	 * This function modifies the previous project list.
	 * @param projectList the previous project list to assign
	 */
	public void setPreviouslyAssignedProjectList(ProjectList projectList) 
	{
		this.alreadyAssignedProjectList = projectList;
	}

	/**
	 * This function return the previous project list.
	 * @return the current project list.
	 */
	public ProjectList getPreviouslyAssignedProjectList() 
	{
		return alreadyAssignedProjectList;
	}
	
	/**
	 * This function modifies the current project list.
	 * @param projectList the current project list
	 */
	public void setProjectsAssigned(ProjectList projectList) 
	{
		this.projectsAssignedList = projectList;
	}

	/**
	 * This function return the list of the project assigned to the current session.
	 * @return the current project assigned to this session
	 */
	public ProjectList getProjectsAssigned() 
	{
		return projectsAssignedList;
	}

} // Resource class