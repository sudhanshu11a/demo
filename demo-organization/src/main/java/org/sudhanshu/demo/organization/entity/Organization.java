/**
 * 
 */
package org.sudhanshu.demo.organization.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sudhanshu Sharma
 *
 */
@Entity
@Table(name = "sys_organization")
public class Organization extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String orgName;
	
	@Column(name = "dispayname")
	private String displayName;

	public Organization() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param name
	 * @param createdDate
	 * @param createdBy
	 * @param modifiedDate
	 * @param modifiedBy
	 * @param active
	 */
	public Organization(String id, String name, String displayName, Long createdBy, Date createdDate, Long modifiedBy,
			Date modifiedDate, Boolean active) {
		super(id, createdBy, createdDate, modifiedBy, modifiedDate, active);
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
