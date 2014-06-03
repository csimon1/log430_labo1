package ca.etsmtl.log430.lab1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class displays various types of information on projects and resources
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.7, 2014-May-09
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.7, S. Abraham , 2014-May-09 - Formating text for easier reading.
 * 
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
	 * Note that the projects previously assigned to the resource and the
	 * projects assigned to the resource in this execution of the system are not
	 * displayed.
	 * 
	 * @param resource
	 */
	public void displayResource(Resource resource) {

		System.out.print(resource.getID() + " " + resource.getFirstName()
				+ " " + resource.getLastName() + " " + resource.getRole().getName());
		
		String overallocated = "";
		if(resource.isOverallocated()){
			overallocated = " !!!overAllocated!!!";
		}
		
		System.out.println(overallocated);
		
		lineCheck(1);
	}

	/**
	 * Displays a project object's elements as follows: ID, name, start date,
	 * end date, and priority. Note that the resources assigned to the project
	 * are not listed by this method.
	 * 
	 * @param project
	 */
	public void displayProject(Project project) {
		
		SimpleDateFormat dForm = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(project.getID() + " " + project.getProjectName()
				+ " " + dForm.format(project.getStartDate()) + " " + dForm.format(project.getEndDate())
				+ " " + project.getPriority());
	}

	/**
	 * Lists all the resources that have been assigned to the project.
	 * 
	 * @param project
	 *            the project object.
	 */
	public void displayResourcesAssignedToProject(Project project) {

		System.out.println("\nResources assigned to: " + " " + project.getID()
				+ " " + project.getProjectName() + " :");
		lineCheck(1);
		for (Resource resource : project.getResourcesAssigned()) {
			displayResource(resource);
			lineCheck(1);
		}
		
		displaySeparator();

	}

	public void displayRoles(Project project) {
		ArrayList<Role> rolesAlreadyAssigned = new ArrayList<>();
		ArrayList<Role> rolesCurrentlyAssigned = new ArrayList<>();
		
		for (Resource r : project.getResourcesAssigned()) {
			Role role = r.getRole();
			
			if(r.getPreviouslyAssignedProjectList().contains(project)){
				if (role != null && !rolesAlreadyAssigned.contains(r.getRole())) {
					rolesAlreadyAssigned.add(r.getRole());
				}
			}else
				if(r.getProjectsCurrentlyAssigned().contains(project)){
					if (role != null && !rolesAlreadyAssigned.contains(r.getRole())) {
						rolesCurrentlyAssigned.add(r.getRole());
					}
			}
						
		}
		
		displaySeparator();
		
		System.out.println("Roles assigned to Project : " + project.getID()
				+ " " + project.getProjectName());
		
		lineCheck(1);
		
		displaySubSeparator();

		if (rolesAlreadyAssigned.isEmpty()) {
			System.out.println("The project does not have ressources already assigned!");
			lineCheck(1);
		} else {
			System.out.println("Roles already assigned :");
			
			lineCheck(1);
			
			for (Role role : rolesAlreadyAssigned) {
				displayRole(role);	
			}
			
			lineCheck(1);
			
		}
		
		displaySubSeparator();
		if (rolesCurrentlyAssigned.isEmpty()) {
			System.out.println("The project does not have ressources currently assigned!");
			lineCheck(1);
		} else {
			System.out.println("\nRoles currently assigned :");
			
			lineCheck(1);
			
			for (Role role : rolesCurrentlyAssigned) {
				displayRole(role);	
			}
			
			System.out.println("\n");
			
			lineCheck(1);
			
		}
				
		displaySubSeparator();
		
		System.out.println("Total role assigned : " + (rolesAlreadyAssigned.size() + rolesCurrentlyAssigned.size()));
		
		
		lineCheck(3);
		
		displaySeparator();

	}

	private void displaySeparator() {
		lineCheck(1);
		System.out.println("\n===========================================================\n");
		lineCheck(1);
	}
	
	private void displaySubSeparator() {
		System.out.println("\t---------------------------------------------------");
		lineCheck(1);
	}

	public void displayRole(Role role) {
		System.out.println(role.getID());
		lineCheck(1);
	}

	/**
	 * Lists the projects previously assigned to a resource .
	 * 
	 * @param resource
	 */
	public void displayProjectsPreviouslyAssignedToResource(Resource resource) {

		System.out.println("\nProjects previously assigned to : "
				+ resource.getFirstName() + " " + resource.getLastName() + " "
				+ resource.getID());
		
		lineCheck(2);


		for (Project project : resource.getPreviouslyAssignedProjectList()) {

			displayProject(project);
			lineCheck(1);

		}
		
		displaySeparator();
	}
	
	/**
	 * Lists the projects currently assigned to a resource during this session.
	 * 
	 * @param resource
	 */
	public void displayProjectsAssignedToResource(Resource resource) {

		System.out.println("\nProjects assigned (in this session) to : "
				+ resource.getFirstName() + " " + resource.getLastName() + " "
				+ resource.getID());
		
		lineCheck(2);


		for (Project project : resource.getProjectsAssigned()) {

			displayProject(project);
			lineCheck(1);

		}
		
		displaySeparator();
	}

	/**
	 * Displays the resources in a resource list. Displays the same information
	 * that is listed in the displayResource() method listed above.
	 * 
	 * @param list
	 */
	public void displayResourceList(ResourceList list) {
		System.out.print("\n");
		lineCheck(1);

		for (Resource resource : list) {
			displayResource(resource);
			lineCheck(1);
		}
	}

	/**
	 * Displays the projects in a project list. Displays the same information
	 * that is listed in the displayProject() method listed above.
	 * 
	 * @param list
	 */

	public void displayProjectList(ProjectList list) {

		System.out.print("\n");
		lineCheck(1);

		for (Project project : list) {
			displayProject(project);
			lineCheck(1);
		}
	}
	
} // Display