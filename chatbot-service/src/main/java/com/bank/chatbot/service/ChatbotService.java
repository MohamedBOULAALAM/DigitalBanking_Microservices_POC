package com.bank.chatbot.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private VectorStore vectorStore;

    public String chat(String message) {
        // Recherche de documents similaires dans le vector store
        List<Document> similarDocuments = vectorStore.similaritySearch(message);
        
        // Construire le contexte à partir des documents trouvés
        String context = similarDocuments.stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n\n"));

        // Construire le prompt avec le contexte
        String promptText = String.format(
                "Tu es un assistant bancaire expert. Réponds aux questions des clients en te basant sur les informations suivantes:\n\n%s\n\nQuestion du client: %s\n\nRéponse:",
                context.isEmpty() ? "Informations générales sur les services bancaires." : context,
                message
        );

        Prompt prompt = new Prompt(promptText);
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}

