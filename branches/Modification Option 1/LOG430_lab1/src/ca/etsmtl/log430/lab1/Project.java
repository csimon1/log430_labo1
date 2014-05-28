package ca.etsmtl.log430.lab1;

import java.util.ArrayList;


/** This class defines the Project object for the system.
* 
* @author A.J. Lattanze, CMU
* @version 1.8, 2014-May-07
*/

/*
* Modification Log **********************************************************
* v1.8, S. Abraham,   2014-May-07 - Added comments
* 
* v1.7, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
* 
* v1.6, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
* 
* v1.5, R. Champagne, 2012-May-31 - Various refactorings for new lab.
* 
* v1.4, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
* 
* v1.3, R. Champagne, 2011-Feb-02 - Various refactorings, conversion of
* comments to javadoc format.
* 
* v1.2, R. Champagne, 2002-May-21 - Adapted for use at ETS.
* 
* v1.1, G.A.Lewis, 01/25/2001 - Bug in second constructor. Removed null
* assignment to id after being assigned a value.
* 
* v1.0, A.J. Lattanze, 12/29/99 - Original version.
* ***************************************************************************
*/
public class Project implements Identiable
{
	/**
	 * Project ID
	 */
	private String id;

	/**
	 * Project name.
	 */
	private String name;

	/**
	 * Project start date.
	 */
	private String startDate;

	/**
	 * Project end date.
	 */
	private String endDate;

	/**
	 * Project priority
	 */
	private String priority;

	/**
	 * List of resources assigned to the project
	 */
	private ResourceList resourcesAssigned;// = new ResourceList();
	
	/**
	 * Default constructor.
	 */

	public Project() 
	{
		this(null);
	}

	/**
	 * This function is the Class Constructor.
	 * @param id the project ID
	 */
	public Project(String id) 
	{
		resourcesAssigned = new ResourceList();

		this.setID(id);
	}

	/**
	 * Assign a resource to a project.
	 * 
	 * @param resource the Ressource object to assign
	 */
	public void assignResource(Resource resource) 
	{
		resourcesAssigned.addResource(resource);
	}

	/**
	 * This function change the project ID.
	 * @param projectID the project ID to change.
	 */
	public void setID(String projectID) 
	{
		this.id = projectID;
	}
	
	/**
	 * This function return the project ID.
	 * @return the project ID
	 */
	@Override
	public String getID() 
	{
		return id;
	}

	/**
	 * This function modifies the project name.
	 * @param name the project name 
	 */
	public void setProjectName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * This function return the project name.
	 * @return the project name
	 */
	public String getProjectName() 
	{
		return name;
	}

	/**
	 * This function modifies the startDate of the project.
	 * @param startDate the start date of the project
	 */
	public void setStartDate(String startDate) 
	{
		this.startDate = startDate;
	}
	
	/**
	 * This function return the start date of the project.
	 * @return the project start date
	 */
	public String getStartDate() 
	{
		return startDate;
	}
	
	/**
	 * This function modifies the end date of the project.
	 * @param endDate the end date of the project
	 */
	public void setEndDate(String endDate) 
	{
		this.endDate = endDate;
	}

	/**
	 * This function return the end date of the project.
	 * @return the end date of the project
	 */
	public String getEndDate() 
	{
		return endDate;
	}

	/**
	 * This function return the priority of the project.
	 * @return the priority of the project
	 */
	public String getPriority() 
	{
		return priority;
	}

	/**
	 * This function modifies the priority of the project.
	 * @param priority the priority of the project
	 */
	public void setPriority(String priority) 
	{
		this.priority = priority;
	}

	/**
	 * This function assign a new list of ressources to the project.
	 * @param resourcesAssigned RessourceList object to assign to the project
	 */
	public void setResourcesAssigned(ResourceList resourcesAssigned) 
	{
		this.resourcesAssigned = resourcesAssigned;
	}
	
	/**
	 * This function return a ressource list object.
	 * @return The RessourceList object
	 */
	public ResourceList getResourcesAssigned() 
	{
		return resourcesAssigned;
	}

} // Project class