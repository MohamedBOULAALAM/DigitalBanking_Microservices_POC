# Bank Microservices POC

Application bancaire de gestion de virements basée sur une architecture microservices.

## Architecture

### Microservices fonctionnels
- **bénéficiaire-service** : Gestion des bénéficiaires
- **virement-service** : Gestion des virements bancaires
- **chatbot-service** : Chatbot IA avec RAG

### Microservices techniques
- **discovery-service** : Service de découverte (Eureka Server)
- **config-service** : Service de configuration centralisée
- **gateway-service** : API Gateway (Spring Cloud Gateway)

## Technologies

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Spring AI
- Eureka / Consul
- OpenAPI/Swagger

### Frontend
- Angular
- Flutter (Mobile)

### DevOps
- Docker & Docker Compose
- Jenkins
- Kubernetes

## Démarrage

Voir la documentation de chaque service pour les instructions de démarrage.

