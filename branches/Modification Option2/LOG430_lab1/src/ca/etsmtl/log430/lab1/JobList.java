package ca.etsmtl.log430.lab1;


/**
 * This class is used by various other classes that need to keep a list of
 * roles on hand. It extends the List class which provides the basic
 * functionality for storage and retrieval of the Role Object from the list.
 * 
 * @author S. Abraham, Student
 * @version 1.0, 2014-May-10
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.0, 2014-May-10, S. Abraham - Original version.
 * **************************************************************************
 */

public class JobList extends List 
{
	/**
	 * Adds a new role to the list. All the issues of casting are taken care
	 * of within this class.
	 * 
	 * @param job
	 */
	public void addJob(Job job) 
	{
		appendItemToList((Object) job);
	}

	/**
	 * @return The role at the current position pointed to by the
	 *         internal list pointer. Subsequent calls will return the next
	 *         resource object in the list. A null object is returned if list is
	 *         empty or the end of list has been reached.
	 */
	public Job getNextJob() 
	{
		return (Job) getItemFromList();
	}

	/**
	 * Determines whether the Job object is currently in the list.
	 * 
	 * @param job
	 * @return true if the job is in the list, false otherwise.
	 */
	public boolean findJob(Job job) 
	{
		Job currentObject;
		boolean done = false;
		boolean result = false;

		// Note that this method assumes that all instances have different
		// identification numbers.

		goToFrontOfList();

		while (!done) 
		{
			currentObject = getNextJob();

			if (currentObject == null) 
			{
				done = true;
			} 
			else 
			{
				if (job.getID().compareTo(
						currentObject.getID()) == 0) 
				{
					result = true;
				} // if
			} // if

		} // while

		return (result);
	} // findJob

	/**
	 * Finds a job in a list using the jobID as the search key.
	 * 
	 * @param jobID
	 * @return if the current list object's jobID matches the jobID
	 *         passed to the method, the Job object is returned to the
	 *         caller. Otherwise, returns null.
	 */
	public Job findJobByID(String jobID) 
	{
		Job currentObject = null;
		boolean done = false;
		boolean found = false;

		// Note that this method assumes that all instances have different
		// identification numbers.

		goToFrontOfList();

		while (!done) 
		{
			currentObject = getNextJob();

			if (currentObject == null) 
			{
				done = true;
			} 
			else
			{
				if (currentObject.getID().compareTo(jobID) == 0) 
				{
					found = true;
					done = true;
				} // if
			} // if

		} // while

		if (found) 
		{
			return (currentObject);
		} 
		else 
		{
			return (null);
		} // if
	} // findJobByID

} // JobList