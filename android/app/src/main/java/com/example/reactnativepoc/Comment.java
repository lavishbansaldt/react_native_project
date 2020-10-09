package com.example.reactnativepoc;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int postId;

    @SerializedName("id")
    private int userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String emailAddress;

    @SerializedName("body")
    private String emailBody;

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmailBody() {
        return emailBody;
    }
}