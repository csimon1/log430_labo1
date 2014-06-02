package ca.etsmtl.log430.lab1.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This class provides the methods that allow the caller to open an existing
 * file and read one line of input (to end-of-line) from the file.
 *
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2014-May-09
 */

/* Modification Log
 **************************************************************************************
 * v1.4, S. Abraham  , 2014-May-09 - Added comments and refactoring.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 *  
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS. 
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 **************************************************************************************/
public class LineOfTextFileReader
{
	private File inputFile = null;
	private BufferedReader inFile = null;

	/**
	 * This function open a file from a specified file path.
	 * @param pathName the path of the file to open
	 * 
	 * @return true if the operation was a success, false otherwise.
	 */
	/**
	 * This function open a file from a specified file path.
	 * @param pathName the path of the file to open
	 * 
	 * @return true if the operation was a success, false otherwise.
	 */
	public boolean openFile(String pathName) 
	{
		boolean result;
		// This allows the opening of files located from this actual package.
		URL localPath = this.getClass().getResource(pathName);
		inputFile = new File(localPath.getFile());

		// Open input file. The input file is a field oriented and
		// space-separated.
		// The fields and expected formats are listed above in the header

		// Check to ensure that the inputFile exists
		if (!inputFile.exists()) 
		{
			System.out.println("\"" +localPath + "\" should be in the  same directory as: "
								+ this.getClass().getPackage().getName());
			
			result = false;
		}
		else 
		{
			try 
			{
				// Create a buffered reader the file
				inFile = new BufferedReader(new InputStreamReader(
						new FileInputStream((inputFile))));

				result = true;
			} // try
			catch (Exception Error) 
			{
				result = false;
			} // catch
		} // if

		return (result);
	} // OpenFile

	/**
	 * This function parse each line of text from a file. and return the 
	 * content of the text in a line.
	 * 
	 * @return the parsed line of text
	 */
	public String readLineOfText() 
	{
		String lineOfText = null;
		
		// Read a line of text from the input file and convert it to upper case
		try 
		{
			lineOfText = inFile.readLine();
		} // try
		catch (Exception Error) 
		{
			try 
			{
				throw (Error);
			} // try
			catch (Exception e) 
			{
				// We are in real trouble if we get here.
			} // catch

		} // catch

		return (lineOfText);
	} // ReadLineOfText

	/**
	 * This function close the current file in use.
	 * 
	 * @return true if the closing operation was a success,
	 * false otherwise
	 */
	public boolean closeFile() 
	{
		boolean result = true;
		// Close the input file
		try 
		{
			inFile.close();
		} // try
		catch (Exception Error) 
		{
			result = false;
		} // catch

		return (result);
	} // ReadLineOfText

} // LineOfTextFileReader