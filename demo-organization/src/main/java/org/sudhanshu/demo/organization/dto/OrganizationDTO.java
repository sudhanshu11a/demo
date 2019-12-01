/**
 * 
 */
package org.sudhanshu.demo.organization.dto;

import java.util.Date;

/**
 * @author Sudhanshu Sharma
 *
 */
public class OrganizationDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	

	/**
	 * @param createdDate
	 * @param modifiedDate
	 * @param name
	 */
	public OrganizationDTO(String id, Date createdDate, Date modifiedDate, String name) {
		super(id, createdDate, modifiedDate);
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
