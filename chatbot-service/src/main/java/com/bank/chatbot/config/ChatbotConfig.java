package com.bank.chatbot.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ChatbotConfig {

    private final ResourceLoader resourceLoader;

    public ChatbotConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public VectorStore vectorStore() throws IOException {
        SimpleVectorStore vectorStore = new SimpleVectorStore();
        
        // Charger les documents PDF depuis le dossier resources/documents
        Resource[] resources = resourceLoader.getResources("classpath:documents/*.pdf");
        
        if (resources.length > 0) {
            TextSplitter textSplitter = new TokenTextSplitter();
            
            for (Resource resource : resources) {
                PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(resource);
                List<Document> documents = pdfReader.get();
                
                // Diviser les documents en chunks plus petits
                List<Document> chunks = textSplitter.apply(documents);
                
                // Ajouter au vector store
                vectorStore.add(chunks);
            }
        }
        
        return vectorStore;
    }
}

