package com.example.sheetal.my.Model;

public class ChatData {
    private String chatMessage;
    private String userId;

    public ChatData(String chatMessage, String userId) {
        this.chatMessage = chatMessage;
        this.userId = userId;

        }

    public ChatData() {
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


