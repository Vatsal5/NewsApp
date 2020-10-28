package com.androidstudio.newsassist;

public class News
{
    private String title;
    private String imageurl;
    private String publishedate;
    private String description;

    public News(String title, String imageUrl, String publishedAt,String description) {
        this.title = title;
        this.imageurl = imageUrl;
        this.publishedate = publishedAt;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPublishedate() {
        return publishedate;
    }

    public void setPublishedate(String publishedat) {
        this.publishedate = publishedate;
    }

    public String getDescription()
    {
        return description;
    }
}
