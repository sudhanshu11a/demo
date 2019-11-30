/**
 * 
 */
package org.sudhanshu.demo.organization.dto;

/**
 * @author Sudhanshu Sharma
 *
 */
public class OrganizationDTO {

	private String name;

	public OrganizationDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public OrganizationDTO(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
