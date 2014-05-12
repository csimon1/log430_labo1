package ca.etsmtl.log430.lab1;


/**
 * This class defines the Role object for the system. Besides the basic
 * attributes, there are two lists maintained. alreadyAssignedProjectList is a
 * ProjectList object that maintains a list of projects that the role was
 * already assigned to prior to this execution of the system.
 * projectsAssignedList is also a ProjectList object that maintains a list of
 * projects assigned to the Role during the current execution or session.
 * 
 * @author S. Abraham, Student
 * @version 1.0, 2014-May-10
 */

/* Modification Log
 ****************************************************************************
 * 
 * v1.0, 2014-May-10, S. Abraham - Original version.
 ****************************************************************************/
public class Job 
{
	/**
	 * Role's name
	 */
	private String name;
	
	/**
	 * Resource's identification number
	 */
	private String id;
	
	/**
	 * Resource's acronym 
	 */
	private String acronym;//role

	/**
	 *  List of projects the job is already allocated to
	 */
	private ProjectList alreadyAssignedProjectList = new ProjectList();

	/**
	 *  List of projects assigned to the job in this session
	 */
	private ProjectList projectsAssignedList = new ProjectList();

	/**
	 * Default Constructor.
	 */
	public Job() 
	{
		this(null);
		// TODO Load previous jobList
	}

	/**
	 * Parameter constructor.
	 * @param id the job id.
	 */
	public Job(String id) 
	{
		this.setID(id);
		// TODO Load previous JobList
	}
	
	/**
	 * Assigns a project to a job.
	 * 
	 * @param project
	 */
	public void assignProject(Project project) 
	{
		getProjectsAssigned().addProject(project);
	}

	/**
	 * This function modifies the job's name.
	 * @param name the name to modify.
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * This function return the job's name.
	 * @return the job's name.
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * This function modifies the job's identification number.
	 * @param id the id to modify.
	 */
	public void setID(String id) 
	{
		this.id = id;
	}

	/**
	 * This function return the job's identification number.
	 * @return the job's id.
	 */
	public String getID() 
	{
		return id;
	}

	/**
	 * This function set an acronym for the job.
	 * This acronym will be used by the Resource class.
	 * 
	 * @param acronym the job's acronym to assign.
	 */
	public void setAcronym(String acronym) 
	{
		this.acronym = acronym;
	}

	/**
	 * This function return the job's acronym.
	 * 
	 * @return the job's acronym.
	 */
	public String getAcronym() 
	{
		return acronym;
	}

	public void setPreviouslyAssignedProjectList(ProjectList projectList) 
	{
		this.alreadyAssignedProjectList = projectList;
	}

	public ProjectList getPreviouslyAssignedProjectList() 
	{
		return alreadyAssignedProjectList;
	}

	public void setProjectsAssigned(ProjectList projectList) 
	{
		this.projectsAssignedList = projectList;
	}

	public ProjectList getProjectsAssigned() 
	{
		return projectsAssignedList;
	}

} // Job class