/**
 * 
 */
package org.sudhanshu.demo.posts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Sudhanshu Sharma
 *
 */
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

	@Column(name = "heading", nullable = false)
	private String heading;
	
	@Column(name = "subHeading")
	private String subHeading;

	@Column(name="slug", length=128, nullable = false, unique = true)
	private String slug;

	@Column
	private String backgroundImage;

	@Column(name = "meta")
	private String meta;

	@Lob
	@Column
	private String body;

	public Post() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param createdBy
	 * @param createdDate
	 * @param modifiedBy
	 * @param modifiedDate
	 * @param active
	 */
	public Post(String id, Long createdBy, Date createdDate, Long modifiedBy, Date modifiedDate, Boolean active,
				String heading, String subHeading, String slug, String backgroundImage, String meta, String body) {
		super(id, createdBy, createdDate, modifiedBy, modifiedDate, active);
		this.heading = heading;
		this.subHeading = subHeading;
		this.slug = slug;
		this.backgroundImage = backgroundImage;
		this.meta = meta;
		this.body = body;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getSubHeading() {
		return subHeading;
	}

	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
