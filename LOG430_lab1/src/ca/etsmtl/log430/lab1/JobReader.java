package ca.etsmtl.log430.lab1;


/**
 * Reads from the InputFile and instantiates the Job objects in the system.
 * It is assumed that the InputFile is in the local directory, contains the
 * active jobs, and each line of input is read and expected in the following
 * format: a field oriented and space-separated text file that lists all the
 * jobs. The fields are as follows:
 * 
 * <pre>
 *     J001 Analyst ANA  
 *     |    |       |
 *     |    |       |   
 *     |    |       |     
 *     |    |       |
 *     |    |       Job's Acronym
 *     |    Jobs's Name 
 *     Job ID
 * </pre>
 * 
 * The jobs.txt file has been provided as an example.
 * 
 * @author S. Abraham, Student
 * @version 1.0, 2014-May-11
 */

/*
 * Modification Log
 ************************************************************************
 * v1.0, 2014-May-11, S. Abraham - Original version.
 * ***********************************************************************
 */

public class JobReader extends LineOfTextFileReader 
{
	/**
	 * The list of drivers.
	 */
	private JobList listOfJobs = new JobList();

	public JobReader() 
	{
		listOfJobs = null;
	} // Constructor #1

	public JobReader(String inputFile) 
	{
		listOfJobs = readJobListFromFile(inputFile);
	} // Constructor #2

	/**
	 * Reads a line of text. The line of text is passed to the parseText
	 * method where it is parsed and a Job object is returned. The Job
	 * object is then added to the list. When a null is read from the Job
	 * file the method terminates and returns the list to the caller. A list
	 * that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The list of drivers
	 */
	public JobList readJobListFromFile(String inputFile) 
	{
		String text; // Line of text from the file
		boolean done; // End of the file - stop processing

		// New job list object - this will contain all of the jobs in
		// the file

		JobList listObject = new JobList();

		if (openFile(inputFile)) 
		{
			done = false;
			
			while (!done) 
			{	
				try 
				{
					text = readLineOfText();

					if (text == null) 
					{
						done = true;
					} 
					else 
					{
						listObject.addJob(parseText(text));
					} // if
				} // try
				catch (Exception Error) 
				{
					return (null);
				} // catch
			} // while
		} 
		else 
		{
			return (null);
		} // if

		return (listObject);
	} // readTeacherListFromFile

	public JobList getListOfJobs() 
	{
		return listOfJobs;
	}

	public void setListOfDrivers(JobList listOfJobs) 
	{
		this.listOfJobs = listOfJobs;
	}

	/**
	 * Parse lines of text that are read from the text file containing driver
	 * information in the above format.
	 * 
	 * @param lineOfText
	 * @return populated Job object
	 */
	private Job parseText(String lineOfText)
	{
		boolean done = false; // Indicates the end of parsing
		String token; // String token parsed from the line of text
		int tokenCount = 0; // Number of tokens parsed
		int frontIndex = 0; // Front index or character position
		int backIndex = 0; // Rear index or character position

		// Create a Resource object to record all of the info parsed from
		// the line of text

		Job job = new Job();

		while (!done) 
		{
			backIndex = lineOfText.indexOf(' ', frontIndex);

			if (backIndex == -1) 
			{
				done = true;
				token = lineOfText.substring(frontIndex);
			} 
			else 
			{
				token = lineOfText.substring(frontIndex, backIndex);
			}

			switch (tokenCount) 
			{
				case 0: // Job ID
					job.setID(token);
					frontIndex = backIndex + 1;
					tokenCount++;
					break;
	
				case 1: // Job's name
					job.setName(token);
					frontIndex = backIndex + 1;
					tokenCount++;
					break;
	
				case 2: // Job's acronym
					job.setAcronym(token);
					frontIndex = backIndex + 1;
					tokenCount++;
					break;
	
				default:
					// This is where the projects are added to the list of projects
					// previously assigned to this job. Note that there are
					// no details other than the project ID.
					
					// Search if the Project exist
					
					job.getPreviouslyAssignedProjectList().addProject(new Project(token));
					frontIndex = backIndex + 1;
					break;
			} // end switch

		} // end while

		return (job);

	} // parseText

} // JobReader