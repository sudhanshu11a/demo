package org.sudhanshu.demo.posts.dto;

public class ResultsPost {
    private String id;
    private String slug;
    private String backgroundImage;
    private String heading;
    private String subHeading;
    private String meta;
    private String body;

    public ResultsPost(String id, String slug, String backgroundImage, String heading, String subHeading, String meta, String body) {
        this.id = id;
        this.slug = slug;
        this.backgroundImage = backgroundImage;
        this.heading = heading;
        this.subHeading = subHeading;
        this.meta = meta;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
