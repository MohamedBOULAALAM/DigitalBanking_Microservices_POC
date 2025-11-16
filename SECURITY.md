# Sécurité du Système Distribué

## Architecture de Sécurité

### 1. Authentification OAuth2/JWT

Le système utilise OAuth2 avec des tokens JWT pour l'authentification :

- **Auth Service** : Service dédié à l'authentification et la génération de tokens JWT
- **JWT Tokens** : Tokens signés avec une clé secrète pour garantir l'intégrité
- **Token Refresh** : Mécanisme de rafraîchissement des tokens

### 2. Sécurisation des Microservices

Chaque microservice valide les tokens JWT via :
- Spring Security avec OAuth2 Resource Server
- Validation de la signature et de l'expiration des tokens
- Extraction des informations utilisateur depuis le token

### 3. API Gateway Security

Le Gateway Service agit comme point d'entrée unique :
- Filtrage des requêtes non authentifiées
- Propagation des tokens JWT aux microservices backend
- Rate limiting pour prévenir les attaques DDoS

### 4. Communication Inter-Services

- Utilisation de HTTPS pour toutes les communications
- Validation mutuelle TLS (mTLS) pour les communications sensibles
- Service Discovery sécurisé avec authentification

### 5. Recommandations

- Utiliser des secrets managés (Vault, AWS Secrets Manager)
- Implémenter le rate limiting
- Activer les logs de sécurité et monitoring
- Mettre en place un système de détection d'intrusion
- Chiffrer les données sensibles en base de données

