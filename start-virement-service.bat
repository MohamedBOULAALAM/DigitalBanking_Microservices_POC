@echo off
echo ========================================
echo Demarrage du Virement Service
echo ========================================
echo.
echo Liberation du port 8082 si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8082 ^| findstr LISTENING') do (
    echo Arret du processus %%a utilisant le port 8082...
    taskkill /F /PID %%a >nul 2>&1
)
timeout /t 2 /nobreak >nul
echo.
echo Demarrage du Virement Service...
echo Note: Assurez-vous que le beneficiaire-service est demarre avant ce service.
cd virement-service
mvn spring-boot:run
pause

