/**
 * 
 */
package ca.etsmtl.log430.lab1.services;



/**
 * @author Charly
 *
 */
public class Role implements Identiable {

	private String id;
	private String name;

	@Override
	public String getID() {
		return id;
	}

	
	public Role(String ID) {
		this.id = ID;
		this.name = this.id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
