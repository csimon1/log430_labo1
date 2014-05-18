package ca.etsmtl.log430.lab1;


/**
 * This class presents the user with menus, accepts their choice, ensures their
 * choice is valid, and returns their choice to the caller. The menu is
 * presented as follows:
 * <pre>
 *    1) List resources
 *    2) List projects
 *    3) List projects currently assigned to a resource
 *    4) List resources currently assigned to a project
 *    5) Assign a resource to a project
 *    X) Exit.</pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2014-May-07.
 * - Added comments
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.6, S. Abraham  , 2014-May-07 - Added comments and refactoring.
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
 * ***************************************************************************
 */
public class Menus 
{
	/**
	 * This method show the actual menu and
	 * prompt the user to select an option from the menu.
	 * @return the character selected by the user.
	 */
	public char mainMenu() 
	{
		Termio terminal = new Termio();
		char userChoice = ' ';
		boolean valid = true;

		while (valid) 
		{
			System.out.println("\n\n1) List resources");
			System.out.println("2) List projects");
			System.out.println("3) List projects currently assigned to a resource");
			System.out.println("4) List resources currently assigned to a project");
			System.out.println("5) Assign a resource to a project");
			System.out.println("X) Exit");
			System.out.print("\n\nEnter your choice and press return >> ");

			userChoice = terminal.keyboardReadChar();

			if ((userChoice != 'X') && (userChoice != 'x')
					&& (userChoice < '1') && (userChoice != '2')
					&& (userChoice != '3') && (userChoice < '4')
					&& (userChoice != '5')) 
			{
				System.out.print("\n\n*** Invalid Choice:: " + userChoice
						+ " ***");

			} 
			else 
			{
				valid = false;

			} // if

		} // while

		return (userChoice);

	} // MainMenu
	
	/**
	 * This method iterate throughout a resource list and return a RessourceList object by asking the user
	 * to enter a Resource ID via keyboard.
	 * 
	 * @param list the ResourceList object to iterate
	 * @return the Resource object or null if not
	 */
	public Resource pickResource(ResourceList list) 
	{
		Termio terminal = new Termio();
		String userChoice;
		Resource resource = null;

		System.out.print("\n\nEnter resource ID and press return >> ");
		userChoice = terminal.keyboardReadString();

		resource = list.findResourceByID(userChoice);

		if (resource == null) 
		{
			System.out.println("\n\n*** Resource ID " + userChoice
					+ " not found ***");
		} // if

		return (resource);
	}

	/**
	 * This method iterate throughout a project list and return a ProjectList object by asking the user
	 * to enter a Project ID via keyboard.
	 * @param list the ProjectList object to iterate
	 * @return the Project object or null if not.
	 */
	public Project pickProject(ProjectList list) 
	{
		Termio terminal = new Termio();
		String userChoiceProjectID;
		Project project = null;

		System.out.print("\nEnter project ID and press return >> ");
		userChoiceProjectID = terminal.keyboardReadString();

		project = list.findProjectByID(userChoiceProjectID);

		if (project == null) 
		{
			System.out.print("\n\n*** Project ID:" + userChoiceProjectID + " not found ***");
		} // if

		return (project);

	} // pickProject

} // Menus