package com.example.sharkle;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text){
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
