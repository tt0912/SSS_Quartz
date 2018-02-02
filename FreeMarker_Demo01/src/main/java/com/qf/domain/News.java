package com.qf.domain;

public class News {

    private Integer id;
    private String title;
    private String content;
    private String time;
    private String urlhtml;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrlhtml() {
        return urlhtml;
    }

    public void setUrlhtml(String urlhtml) {
        this.urlhtml = urlhtml;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", urlhtml='" + urlhtml + '\'' +
                '}';
    }
}
