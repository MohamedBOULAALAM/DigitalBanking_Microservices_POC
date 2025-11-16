package com.bank.chatbot.controller;

import com.bank.chatbot.dto.ChatRequest;
import com.bank.chatbot.dto.ChatResponse;
import com.bank.chatbot.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@Tag(name = "Chatbot", description = "API de chatbot bancaire avec IA")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/chat")
    @Operation(summary = "Chatter avec le bot", description = "Poser une question au chatbot bancaire")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        String response = chatbotService.chat(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(response));
    }
}

