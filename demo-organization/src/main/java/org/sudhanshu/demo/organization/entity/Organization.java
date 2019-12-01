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
	private String name;

	/**
	 * @param id
	 * @param organizationName
	 * @param createdDate
	 * @param createdBy
	 * @param modifiedDate
	 * @param modifiedBy
	 * @param active
	 */
	public Organization(Long id, String name, Long createdBy, Date createdDate, Long modifiedBy,
			Date modifiedDate, Boolean active) {
		super(id, createdBy, createdDate, modifiedBy, modifiedDate, active);
		this.name = name;
	}

	public String geName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
