package com.bank.chatbot.dto;

import jakarta.validation.constraints.NotBlank;

public class ChatRequest {

    @NotBlank(message = "Le message est obligatoire")
    private String message;

    public ChatRequest() {
    }

    public ChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

