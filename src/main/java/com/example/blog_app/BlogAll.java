package com.example.blog_app;

public class BlogAll {
    private final Long id;
    private final String title;
    private final String subText;

    public BlogAll(Long id, String title, String subText) {
        this.id = id;
        this.title = title;
        this.subText = subText;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubText() {
        return subText;
    }
}
