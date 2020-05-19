/**
 * 
 */
package org.sudhanshu.demo.organization.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Sudhanshu Sharma
 *
 */
public class BaseDTO implements Serializable {

	private static final long serialVersionUID = 8002080636214707471L;

	private String uuid;

	/** The created date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	/** The modified date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date modifiedDate;

	
	public BaseDTO() {
		super();
	}
	
	/**
	 * @param createdDate
	 * @param modifiedDate
	 */
	public BaseDTO(String id, Date createdDate, Date modifiedDate) {
		super();
		this.uuid = id;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}


	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
