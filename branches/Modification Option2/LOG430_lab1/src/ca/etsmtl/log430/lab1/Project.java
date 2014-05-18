package ca.etsmtl.log430.lab1;

import java.util.ArrayList;


/** This class defines the Project object for the system.
* 
* @author A.J. Lattanze, CMU
* @version 1.7, 2013-Sep-13
*/

/*
* Modification Log **********************************************************
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

public class Project {

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
	private ResourceList resourcesAssigned = new ResourceList();

	public Project() 
	{
		this(null);
		// TODO Load previous jobList
	}

	public Project(String id) 
	{
		this.setID(id);
		// TODO Load previous JobList
	}

	/**
	 * Assign a resource to a project.
	 * 
	 * @param resource
	 */
	public void assignResource(Resource resource) {
		resourcesAssigned.addResource(resource);
	}

	public void setID(String projectID) {
		this.id = projectID;
	}

	public String getID() {
		return id;
	}

	public void setProjectName(String time) {
		this.name = time;
	}

	public String getProjectName() {
		return name;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setResourcesAssigned(ResourceList resourcesAssigned) {
		this.resourcesAssigned = resourcesAssigned;
	}

	public ResourceList getResourcesAssigned() {
		return resourcesAssigned;
	}
	
	
	public void displayRoles(ResourceList existingResources)
	{
		ArrayList<String> roles = new ArrayList();
		// we going to check if the previous resources contains the actual project in the file
		if(existingResources.itemCount() > 0)
		{
			// ask previous resources
			// replacing the cursor of the list
			existingResources.goToFrontOfList();
			boolean doneIteratingResources = false;
			
			while(!doneIteratingResources)
			{
				// check if the current resource have a project that is associated with the current project.
				Resource r = existingResources.getNextResource();
				
				if(r != null)
				{
					// check first if the current resource have old projects that are associated with this one.
					if(r.getPreviouslyAssignedProjectList().itemCount() > 0)
					{
						ProjectList previousResourceList = r.getPreviouslyAssignedProjectList();
						boolean doneIteratingProjects = false;
						
						previousResourceList.goToFrontOfList();
						
						while(!doneIteratingProjects)
						{
							Project p = previousResourceList.getNextProject();
							// if the project actually exist
							if(p != null)
							{
								// compare the project Id with this current one.
								if(p.getID().equalsIgnoreCase(this.getID()))
								{
									// We find a resource that was associated with a project before run time!
									if(!roles.contains(r.getRole()))
									roles.add(r.getRole());
								}
							}
							else
							{
								doneIteratingProjects = true;
							}
						}
					}
				}
				else
				{
					doneIteratingResources = true;
				}
				// if it not the case then do nothing and proceed to the next resource
			}
		}
		
		// ask current resources
		if(resourcesAssigned.itemCount() > 0)
		{
			boolean done = false;
			resourcesAssigned.goToFrontOfList();
			
			while(!done)
			{
				Resource r = resourcesAssigned.getNextResource();
				
				if(r != null)
				{
					if(!roles.contains(r.getRole()))
					{
						roles.add(r.getRole());
					}
				}
				else
				{
					done = true;
				}
			}
		}
		
		if(roles.isEmpty())
		{
			System.out.println("The project : " + this.getProjectName() + "do not have ressources assigned!");
		}
		else
		{
			for(String elem: roles)
			{
				System.out.println(elem);
			}
		}
	}

} // Project class