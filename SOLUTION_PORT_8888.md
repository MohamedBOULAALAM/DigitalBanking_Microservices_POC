# Solution : Port 8888 déjà utilisé

## Problème
Le config-service ne peut pas démarrer car le port 8888 est déjà utilisé par un autre processus.

## Solution

### Option 1 : Utiliser le script automatique (Recommandé)
Double-cliquez sur `start-config-service.bat` - il libère automatiquement le port avant de démarrer.

### Option 2 : Libérer le port manuellement

1. **Trouver le processus qui utilise le port 8888 :**
   ```cmd
   netstat -ano | findstr :8888
   ```

2. **Arrêter le processus (remplacer PID par le numéro trouvé) :**
   ```cmd
   taskkill /F /PID <PID>
   ```

3. **Démarrer le config-service :**
   ```cmd
   cd config-service
   mvn spring-boot:run
   ```

### Option 3 : Changer le port du config-service

Si vous préférez utiliser un autre port, modifiez `config-service/src/main/resources/application.yml` :

```yaml
server:
  port: 8889  # Changez ici
```

**Note :** Si vous changez le port, n'oubliez pas de mettre à jour la configuration Eureka dans les autres services.

## Vérification

Pour vérifier que le port est libre :
```cmd
netstat -ano | findstr :8888
```

Si rien n'apparaît, le port est libre.

