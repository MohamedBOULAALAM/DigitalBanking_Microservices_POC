@echo off
echo ========================================
echo Demarrage du Chatbot Service
echo ========================================
echo.
echo Liberation du port 8083 si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8083 ^| findstr LISTENING') do (
    echo Arret du processus %%a utilisant le port 8083...
    taskkill /F /PID %%a >nul 2>&1
)
timeout /t 2 /nobreak >nul
echo.
echo Demarrage du Chatbot Service...
cd chatbot-service
mvn spring-boot:run
pause

