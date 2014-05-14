package ca.etsmtl.log430.lab1;

import java.util.ArrayList;


/**
 * This class displays various types of information on projects and resources
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2013-Sep-13
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.6, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. Once a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page.
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a resource object's elements as follows: Resource's first name,
	 * last name, ID number, role.
	 * 
	 * Note that the projects previously assigned to the resource and the projects
	 * assigned to the resource in this execution of the system are not displayed.
	 * 
	 * @param resource
	 */
	public void displayResource(Resource resource) {

		System.out.println(resource.getID() + " "
				+ resource.getFirstName() + " "
				+ resource.getLastName() + " "
				+ resource.getRole());
	}

	/**
	 * Displays a project object's elements as follows: ID, name, start date,
	 * end date, and priority. Note that the resources assigned to the project
	 * are not listed by this method.
	 * 
	 * @param project
	 */
	public void displayProject(Project project) {
		System.out.println(project.getID() + " "
				+ project.getProjectName() + " "
				+ project.getStartDate() + " "
				+ project.getEndDate() + " "
				+ project.getPriority());
	}

	/**
	 * Lists the resources that have been assigned to the project.
	 * 
	 * @param project
	 */
	public void displayResourcesAssignedToProject(Project project) {

		boolean done;
		Resource resource;

		System.out.println("\nResources assigned to: " + " "
				+ project.getID() + " " + project.getProjectName() + " :");
		lineCheck(1);

		System.out
				.println("===========================================================");
		lineCheck(1);

		project.getResourcesAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			resource = project.getResourcesAssigned().getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);

			} // if

		} // while

	}
	
	public void displayRoles(Project paramProject, ResourceList existingResources)
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
								if(p.getID().equalsIgnoreCase(paramProject.getID()))
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
		if(paramProject.getResourcesAssigned().itemCount() > 0)
		{
			boolean done = false;
			ResourceList resourceFromParamProject = paramProject.getResourcesAssigned();
			resourceFromParamProject.goToFrontOfList();
			
			while(!done)
			{
				Resource r = resourceFromParamProject.getNextResource();
				
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
			System.out.println("The project : " + paramProject.getProjectName() + "do not have ressources assigned!");
		}
		else
		{
			System.out.println("\nRoles assigned to: " + " "
					+ paramProject.getID() + " " + paramProject.getProjectName() + " :");
			lineCheck(1);

			System.out
					.println("===========================================================");
			lineCheck(1);

			for(String elem: roles)
			{
				System.out.println(elem);
			}
			
			System.out
			.println("===========================================================");
			lineCheck(1);
		}
	}

	/**
	 * Lists the projects currently assigned to a resource during this session.
	 * 
	 * @param resource
	 */
	public void displayProjectsAssignedToResource(Resource resource) {

		boolean done;
		Project project;

		System.out.println("\nProjects assigned (in this session) to : "
				+ resource.getFirstName() + " " + resource.getLastName() + " "
				+ resource.getID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		resource.getProjectsAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			project = resource.getProjectsAssigned().getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(2);

			} // if

		} // while

	}

	/**
	 * Displays the resources in a resource list. Displays the same information
	 * that is listed in the displayResource() method listed above.
	 * 
	 * @param list
	 */
	public void displayResourceList(ResourceList list) {

		boolean done;
		Resource resource;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();

		done = false;

		while (!done) {

			resource = list.getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);
				lineCheck(1);

			} // if

		} // while

	}

	/**
	 * Displays the projects in a project list. Displays the same
	 * information that is listed in the displayProject() method listed above.
	 * 
	 * @param list
	 */
	public void displayProjectList(ProjectList list) {

		boolean done;
		Project project;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {

			project = list.getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(1);

			} // if

		} // while

	}
} // Display