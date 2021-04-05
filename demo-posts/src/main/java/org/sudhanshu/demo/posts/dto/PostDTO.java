package org.sudhanshu.demo.posts.dto;

import java.util.Date;

public class PostDTO extends BaseDTO{

    private String heading;
    private String subHeading;
    private String slug;
    private String backgroundImage;
    private String meta;
    private String body;

    public PostDTO() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param id
     * @param createdDate
     * @param modifiedDate
     * @param heading
     * @param subHeading
     * @param slug
     * @param backgroundImage
     * @param meta
     * @param body
     */
    public PostDTO(String id, Date createdDate, Date modifiedDate, String heading, String subHeading
            ,String slug, String backgroundImage, String meta, String body) {
        super(id, createdDate, modifiedDate);
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
