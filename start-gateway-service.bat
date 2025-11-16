@echo off
echo ========================================
echo Demarrage du Gateway Service
echo ========================================
echo.
echo Liberation du port 8080 si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
    echo Arret du processus %%a utilisant le port 8080...
    taskkill /F /PID %%a >nul 2>&1
)
timeout /t 2 /nobreak >nul
echo.
echo Demarrage du Gateway Service...
echo Note: Assurez-vous que le discovery-service et le config-service sont demarres avant ce service.
cd gateway-service
mvn spring-boot:run
pause

