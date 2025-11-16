# Guide de Démarrage - Bank Microservices POC

## Prérequis

- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- Node.js & npm (pour Angular)
- Flutter SDK (pour l'application mobile)

## Architecture

### Microservices Techniques
1. **discovery-service** (Port 8761) - Eureka Server
2. **config-service** (Port 8888) - Spring Cloud Config Server
3. **gateway-service** (Port 8080) - Spring Cloud Gateway

### Microservices Fonctionnels
1. **beneficiaire-service** (Port 8081) - Gestion des bénéficiaires
2. **virement-service** (Port 8082) - Gestion des virements
3. **chatbot-service** (Port 8083) - Chatbot IA avec RAG
4. **auth-service** (Port 8084) - Authentification OAuth2/JWT

## Démarrage Local

### 1. Démarrer les services techniques

```bash
# Démarrer Discovery Service
cd discovery-service
mvn spring-boot:run

# Démarrer Config Service (dans un autre terminal)
cd config-service
mvn spring-boot:run

# Démarrer Gateway Service (dans un autre terminal)
cd gateway-service
mvn spring-boot:run
```

### 2. Démarrer les services fonctionnels

```bash
# Démarrer Beneficiaire Service
cd bénéficiaire-service
mvn spring-boot:run

# Démarrer Virement Service
cd virement-service
mvn spring-boot:run

# Démarrer Chatbot Service (nécessite OPENAI_API_KEY)
cd chatbot-service
export OPENAI_API_KEY=votre-clé-api
mvn spring-boot:run
```

### 3. Démarrer avec Docker Compose

```bash
docker-compose up -d
```

### 4. Démarrer le Frontend Angular

```bash
cd frontend/bank-frontend
npm install
ng serve
```

Accéder à http://localhost:4200

### 5. Démarrer l'application Flutter

```bash
cd mobile/flutter_bank_app
flutter pub get
flutter run
```

## Accès aux Services

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Swagger Beneficiaire**: http://localhost:8081/swagger-ui.html
- **Swagger Virement**: http://localhost:8082/swagger-ui.html
- **Swagger Chatbot**: http://localhost:8083/swagger-ui.html
- **Frontend Angular**: http://localhost:4200

## API Endpoints

### Bénéficiaires
- GET /api/beneficiaires - Liste des bénéficiaires
- POST /api/beneficiaires - Créer un bénéficiaire
- GET /api/beneficiaires/{id} - Obtenir un bénéficiaire
- PUT /api/beneficiaires/{id} - Mettre à jour un bénéficiaire
- DELETE /api/beneficiaires/{id} - Supprimer un bénéficiaire

### Virements
- GET /api/virements - Liste des virements
- POST /api/virements - Créer un virement
- GET /api/virements/{id} - Obtenir un virement
- DELETE /api/virements/{id} - Supprimer un virement

### Chatbot
- POST /api/chatbot/chat - Envoyer un message au chatbot

## Déploiement Kubernetes

```bash
kubectl apply -f k8s/
```

## Pipeline CI/CD

Le pipeline Jenkins est configuré dans le fichier `Jenkinsfile`. Il effectue :
1. Build Maven
2. Tests
3. Build des images Docker
4. Push vers le registry
5. Déploiement Kubernetes

## Sécurité

Voir le fichier `SECURITY.md` pour les détails sur la configuration de sécurité.

## Notes

- Les services utilisent H2 en mémoire pour la démo (données perdues au redémarrage)
- Le chatbot nécessite une clé API OpenAI valide
- Pour la production, remplacer H2 par PostgreSQL ou MySQL
- Configurer les variables d'environnement pour les secrets

