package ca.etsmtl.log430.lab1;

import java.util.ArrayList;

import ca.etsmtl.log430.lab1.DAO.ProjectReader;
import ca.etsmtl.log430.lab1.DAO.ResourceReader;
import ca.etsmtl.log430.lab1.controller.Menus;
import ca.etsmtl.log430.lab1.presentation.Displays;
import ca.etsmtl.log430.lab1.services.Project;
import ca.etsmtl.log430.lab1.services.Resource;
import ca.etsmtl.log430.lab1.services.Role;

/**
 * Main class for assignment 1 for LOG430, Architecture logicielle.
 * 
 * <pre>
 * <b>Pseudo Code:</b>
 * 
 *   Instantiate lists
 *   do until done
 *     Present Menu
 *     if user choice = 1 then list resources
 *     if user choice = 2 then list projects
 *     if user choice = 3 then
 *        list resources
 *        ask user to select a resource (by ID)
 *        list projects assigned to that resource
 *     endif
 *     if user choice = 4 then
 *        list projects
 *        ask user to select a project (by ID)
 *        list resources assigned to that project
 *     endif
 *     if user choice = 5 then
 *        list resources
 *        ask user to select a resource (by ID)
 *        list projects
 *        ask user to select a project (by ID)
 *        assign project to resource (and vice versa)
 *     endif
 *     if user choice = x then you are done
 *   end do
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2014-May-07
 */

/*
 * Modification Log
 * **************************************************************************
 * v1.6, S. Abraham , 2014-May-07 - Formated source code for easy reading.
 * 
 * v1.5, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
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

public class ResourceAssignment {

	/**
	 * Main method
	 * 
	 * @param argv
	 *            arguments to add a the run configuration.
	 */
	public static void main(String argv[]) {
		if (argv.length != 2) {
			System.out.println("\n\nIncorrect number of input parameters -"
					+ " correct usage:");
			System.out.println("\njava ResourceAssignment <project file name>"
					+ " <resource file name> <job file name>");
		} else {
			
			
			Role r1 = new Role("DES");
			Role r2 = new Role("DES");
			
			System.out.println(r1.equals(r2));
			
			

			// Declarations:

			boolean done = false; // Loop invariant
			char userChoice; // User's menu choice
			Project project = null; // A project object
			Resource resource = null; // A resource object

			// Instantiates a menu object
			Menus menu = new Menus();

			// Instantiates a display object
			Displays display = new Displays();

			/*
			 * The following instantiations create a list of projects and
			 * resources. The pathname for the file containing course
			 * information is passed to the main program on the command line as
			 * the first argument (argv[0]). The pathname for the file
			 * containing resource information is passed to the main program on
			 * the command line as the second argument (argv[1]). An example
			 * resources file and projects file is provided as resources.txt and
			 * projects.txt
			 */
			ProjectReader projectList = new ProjectReader(argv[0]);
			ResourceReader resourceList = null;

			if (projectList.getListOfProjects() == null) {

				System.out
						.println("\n *** The projects list was not initialized ***");
				done = true;
			} else {

				resourceList = new ResourceReader(argv[1],
						projectList.getListOfProjects());

				if (resourceList.getListOfResources() == null) {
					System.out
							.println("\n *** The ressource list was not initialized ***");
					done = true;
				}

			}

			while (!done) {
				userChoice = menu.mainMenu();

				switch (userChoice) {
				case '1':

					display.displayResourceList(resourceList
							.getListOfResources());
					break;

				case '2':

					display.displayProjectList(projectList.getListOfProjects());
					break;

				case '3':

					display.displayResourceList(resourceList
							.getListOfResources());
					resource = menu.pickResource(resourceList
							.getListOfResources());
					if (resource != null) {
						display.displayProjectsAssignedToResource(resource);
					} // if
					break;

				case '4':

					display.displayProjectList(projectList.getListOfProjects());
					project = menu.pickProject(projectList.getListOfProjects());

					if (project != null) {
						display.displayResourcesAssignedToProject(project);
					} // if
					break;

				case '5':

					display.displayResourceList(resourceList
							.getListOfResources());
					resource = menu.pickResource(resourceList
							.getListOfResources());

					if (resource != null) {
						display.displayProjectList(projectList.getListOfProjects());
						project = menu.pickProject(projectList.getListOfProjects());

						boolean assign = false;
						if (project != null) {
							if (resource.isAvailableForProject(project)) {
								assign = true;
							}

							if (assign)
								project.assignResource(resource);
							display.displayResourcesAssignedToProject(project);

						} // if
					}
					break;

				case '6':
					display.displayResourceList(resourceList
							.getListOfResources());
					resource = menu.pickResource(resourceList
							.getListOfResources());
					if (resource != null) {
						display.displayProjectsPreviouslyAssignedToResource(resource);
					} // if
					break;

				case '7':

					// display all existing projects...
					display.displayProjectList(projectList.getListOfProjects());
					// prompt the user to select a project
					project = menu.pickProject(projectList.getListOfProjects());

					if (project != null) {
						// display all resources assigned to the project.
						display.displayRoles(project);
						// project.displayRoles(resourceList.getListOfResources());
					}
					break;

				case 'X':
				case 'x':
					done = true;

				} // switch
			} // while
		} // if
	} // main
} // Class