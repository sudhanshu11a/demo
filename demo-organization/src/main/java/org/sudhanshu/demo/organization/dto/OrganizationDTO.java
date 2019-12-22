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
	private String orgName;
	private String displayName;

	

	public OrganizationDTO() {
		super();
	}

	/**
	 * @param createdDate
	 * @param modifiedDate
	 * @param name
	 */
	public OrganizationDTO(String id, Date createdDate, Date modifiedDate, String name, String displayName) {
		super(id, createdDate, modifiedDate);
		this.orgName = name;
		this.displayName = displayName;
	}
	
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	

}
