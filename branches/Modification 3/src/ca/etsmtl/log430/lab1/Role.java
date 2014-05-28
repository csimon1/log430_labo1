/**
 * 
 */
package ca.etsmtl.log430.lab1;


/**
 * @author Charly
 *
 */
public class Role implements Identiable {

	private String id;

	@Override
	public String getID() {
		return id;
	}

	
	public Role(String ID) {
		this.id = ID;
	}
	
	
}
