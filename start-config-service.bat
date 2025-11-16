@echo off
echo ========================================
echo Demarrage du Config Service
echo ========================================
echo.
echo Liberation du port 8888 si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8888 ^| findstr LISTENING') do (
    echo Arret du processus %%a utilisant le port 8888...
    taskkill /F /PID %%a >nul 2>&1
)
timeout /t 2 /nobreak >nul
echo.
echo Demarrage du Config Service...
cd config-service
mvn spring-boot:run
pause
