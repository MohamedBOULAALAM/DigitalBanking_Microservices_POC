# Guide de Sécurité - Configuration des Clés API

## ⚠️ Important : Protection des Secrets

Pour des raisons de sécurité, **ne commitez JAMAIS de clés API ou de secrets** dans le dépôt Git.

## Configuration de la Clé OpenAI pour le Chatbot Service

### Option 1 : Variable d'environnement (Recommandé)

1. **Windows (PowerShell)** :
   ```powershell
   $env:OPENAI_API_KEY="votre-cle-api-ici"
   ```

2. **Windows (CMD)** :
   ```cmd
   set OPENAI_API_KEY=votre-cle-api-ici
   ```

3. **Linux/Mac** :
   ```bash
   export OPENAI_API_KEY="votre-cle-api-ici"
   ```

### Option 2 : Fichier application-local.yml (Non versionné)

Créez un fichier `chatbot-service/src/main/resources/application-local.yml` :

```yaml
openai:
  api:
    key: votre-cle-api-ici
```

Puis démarrez avec le profil `local` :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Option 3 : Configuration dans votre IDE

Dans IntelliJ IDEA ou Eclipse, configurez les variables d'environnement dans les configurations d'exécution.

## Vérification

Pour vérifier que la clé est bien configurée, le service affichera une erreur au démarrage si la clé est manquante lors d'un appel à l'API OpenAI.

## Fichiers à ne JAMAIS commiter

- `**/application-local.yml`
- `**/application-prod.yml`
- `.env`
- Tous les fichiers contenant des secrets

Ces fichiers sont déjà dans `.gitignore`.

