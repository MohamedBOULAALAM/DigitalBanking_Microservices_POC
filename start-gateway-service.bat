@echo off
echo ========================================
echo Demarrage du Gateway Service
echo ========================================
echo.

echo Liberation du port 8008 si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8008 ^| findstr LISTENING') do (
    echo Arret du processus %%a utilisant le port 8008...
    taskkill /F /PID %%a >nul 2>&1
    if errorlevel 1 (
        echo ATTENTION: Impossible d'arreter le processus %%a automatiquement.
        echo Veuillez executer cette commande en tant qu'administrateur:
        echo taskkill /F /PID %%a
        echo.
        echo Ou fermez manuellement l'application utilisant le port 8008.
        pause
        exit /b 1
    )
)
timeout /t 2 /nobreak >nul

echo Verification du port 8008...
netstat -ano | findstr :8008 | findstr LISTENING >nul
if not errorlevel 1 (
    echo ERREUR: Le port 8008 est toujours occupe!
    echo Veuillez fermer l'application utilisant ce port manuellement.
    pause
    exit /b 1
)

echo Port 8008 libre!
echo.
echo Demarrage du Gateway Service...
echo Note: Assurez-vous que le discovery-service et le config-service sont demarres avant ce service.
cd gateway-service
mvn spring-boot:run
pause

