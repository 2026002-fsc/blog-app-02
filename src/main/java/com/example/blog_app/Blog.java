package com.example.blog_app;

public class Blog {
    private final String title;
    private final String mainText;

    public Blog(String title, String mainText) {
        this.title = title;
        this.mainText = mainText;
    }

    public String getTitle() {
        return title;
    }
    
    public String getMainText() {
        return mainText;
    }

}
