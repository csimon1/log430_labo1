package ca.etsmtl.log430.lab1;

/**
 * Reads from the InputFile and instantiates the Resource objects in the system.
 * It is assumed that the InputFile is in the local directory, contains the
 * active resources, and each line of input is read and expected in the following
 * format: a field oriented and space-separated text file that lists all the
 * resources. The fields are as follows:
 * 
 * <pre>
 *     R001 Bleau Joseph ANA P001 P003 
 *     |    |     |      |   |
 *     |    |     |      |   Projects to which this resource is allocated (id)
 *     |    |     |      Role (ANA=Analyst, DES=Designer, PRG=programmer,
 *     |    |     |            TST=tester)
 *     |    |     Resource's First Name
 *     |    Resource's Last Name 
 *     Resource ID
 * </pre>
 * 
 * The resources.txt file has been provided as an example.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.8, 2014-May-07
 */

/*
 * Modification Log
 * *********************************************************************** v1.8,
 * 2014-May-07, S. Abraham , - Formated source code for easier reading.
 * 
 * v1.7, 2013-Sep-13, R. Champagne, Various refactorings for new lab.
 * 
 * v1.6, 2012-Jun-19, R. Champagne, Various refactorings for new lab.
 * 
 * v1.5, 2012-May-31, R. Champagne, Various refactorings for new lab.
 * 
 * v1.4, 2012-Feb-14, R. Champagne, Various refactorings for new lab.
 * 
 * v1.3, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.2, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.1, 2001-Jan-25, G.A. Lewis - Bug in ParseStudentText. There was a bug in
 * that was causing it not to read the last course into the list of courses
 * taken.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***********************************************************************
 */

public class ResourceReader extends LineOfTextFileReader {
	/**
	 * The list of drivers.
	 */
	private ResourceList listOfResources = new ResourceList();

	private ProjectList projects;

	/**
	 * 
	 * @param inputFile
	 *            the file path.
	 */
	public ResourceReader(String inputFile, ProjectList projects) {
		this.projects = projects;
		listOfResources = readResourceListFromFile(inputFile);
		
	} // Constructor #2

	/**
	 * Reads a line of text. The line of text is passed to the parseText
	 * method where it is parsed and a Resource object is returned. The Resource
	 * object is then added to the list. When a null is read from the Resource
	 * file the method terminates and returns the list to the caller. A list
	 * that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The list of drivers
	 */
	public ResourceList readResourceListFromFile(String inputFile) {
		String text; // Line of text from the file
		boolean done; // End of the file - stop processing

		// New resorce list object - this will contain all of the resources in
		// the file

		ResourceList ressources = new ResourceList();

		if (openFile(inputFile)) {
			done = false;

			while (!done) {
				try {
					text = readLineOfText();

					if (text == null) {
						done = true;

					} else {
						ressources.addResource(parseResource(text));
					} // if
				} // try
				catch (Exception Error) {
					return (null);
				} // catch
			} // while
		} else {
			return (null);
		} // if

		return (ressources);
	} // readTeacherListFromFile

	/**
	 * This function return a list of resource object.
	 * 
	 * @return the ResourceList object
	 */
	public ResourceList getListOfResources() {
		return listOfResources;
	}

	/**
	 * This function assign a list of resources to read.
	 * @param listOfResources the list of ressources to read
	 */
	public void setListOfDrivers(ResourceList listOfResources) {
		this.listOfResources = listOfResources;
	}

	/**
	 * Parse lines of text that are read from the text file containing driver
	 * information in the above format.
	 * 
	 * @param lineOfText
	 * @return populated Resource object
	 */
	private Resource parseResource(String ressourceText) {
		boolean done = false; // Indicates the end of parsing
		String token; // String token parsed from the line of text
		int tokenCount = 0; // Number of tokens parsed
		int frontIndex = 0; // Front index or character position
		int backIndex = 0; // Rear index or character position

		// Create a Resource object to record all of the info parsed from
		// the line of text

		Resource resource = new Resource();

		while (!done) {
			backIndex = ressourceText.indexOf(' ', frontIndex);

			if (backIndex == -1) {
				done = true;
				token = ressourceText.substring(frontIndex);

			} else {
				token = ressourceText.substring(frontIndex, backIndex);
			}

			switch (tokenCount) {
			case 0: // Resource ID
				resource.setID(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 1: // Resource's last name
				resource.setLastName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 2: // Resource's First name
				resource.setFirstName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 3: // Resource role (see this file's header)
				if (token.compareTo("") != 0) {
					resource.setRole(new Role(token));
				}
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			default:
				// This is where the projects are added to the list of projects
				// previously assigned to this resource. Note that there are
				// no details other than the project ID.

				Project p = this.projects.findProjectByID(token);

				if (p == null) {
					p = new Project(token);
				}
				
				p.assignResource(resource);
				frontIndex = backIndex + 1;
				break;
			} // end switch

		} // end while

		return (resource);
	} // parseText

} // ResourceReader