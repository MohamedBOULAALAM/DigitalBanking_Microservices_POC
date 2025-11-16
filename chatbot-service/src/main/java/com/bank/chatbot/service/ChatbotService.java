package com.bank.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

    @Value("${openai.api.key:}")
    private String openaiApiKey;

    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String openaiApiUrl;

    private final WebClient webClient;

    public ChatbotService() {
        this.webClient = WebClient.builder().build();
    }

    public String chat(String message) {
        // Si pas de clé API, retourner une réponse simple
        if (openaiApiKey == null || openaiApiKey.isEmpty()) {
            return generateSimpleResponse(message);
        }

        try {
            // Appel à l'API OpenAI
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", "Tu es un assistant bancaire expert. Réponds aux questions des clients de manière claire et professionnelle.");
            
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", message);
            
            requestBody.put("messages", new Object[]{systemMessage, userMessage});
            requestBody.put("max_tokens", 200);

            Mono<Map> response = webClient.post()
                    .uri(openaiApiUrl)
                    .header("Authorization", "Bearer " + openaiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class);

            Map<String, Object> result = response.block();
            if (result != null && result.containsKey("choices")) {
                @SuppressWarnings("unchecked")
                java.util.List<Map<String, Object>> choices = (java.util.List<Map<String, Object>>) result.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, Object> messageObj = (Map<String, Object>) choice.get("message");
                    return (String) messageObj.get("content");
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse simple
            return generateSimpleResponse(message);
        }

        return generateSimpleResponse(message);
    }

    private String generateSimpleResponse(String message) {
        String lowerMessage = message.toLowerCase();
        
        if (lowerMessage.contains("virement") || lowerMessage.contains("transfert")) {
            return "Pour effectuer un virement, vous devez d'abord ajouter un bénéficiaire. Ensuite, vous pouvez créer un virement en spécifiant le montant et le RIB source.";
        } else if (lowerMessage.contains("bénéficiaire") || lowerMessage.contains("beneficiaire")) {
            return "Un bénéficiaire est une personne ou une entreprise à qui vous pouvez transférer de l'argent. Vous pouvez ajouter un bénéficiaire avec son nom, prénom, RIB et type (Physique ou Morale).";
        } else if (lowerMessage.contains("compte") || lowerMessage.contains("rib")) {
            return "Le RIB (Relevé d'Identité Bancaire) est un identifiant unique de 24 chiffres qui identifie votre compte bancaire.";
        } else if (lowerMessage.contains("bonjour") || lowerMessage.contains("salut")) {
            return "Bonjour ! Je suis votre assistant bancaire. Comment puis-je vous aider aujourd'hui ?";
        } else {
            return "Je suis votre assistant bancaire. Je peux vous aider avec les virements, les bénéficiaires et les questions générales sur nos services. Que souhaitez-vous savoir ?";
        }
    }
}
