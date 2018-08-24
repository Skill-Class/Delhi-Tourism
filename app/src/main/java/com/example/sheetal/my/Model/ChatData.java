package com.example.sheetal.my.Model;

public class ChatData {
    private String chatMessage;
    private String userId;
    private String userName;

    public ChatData(String chatMessage, String userId, String userName) {
        this.chatMessage = chatMessage;
        this.userId = userId;
        this.userName = userName;
    }

    public ChatData() {
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


