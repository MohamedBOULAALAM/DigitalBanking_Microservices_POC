@echo off
echo ========================================
echo Demarrage des Microservices Bancaires
echo ========================================
echo.

echo Liberation des ports si necessaire...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8761 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8888 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8008 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8082 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8083 ^| findstr LISTENING') do taskkill /F /PID %%a >nul 2>&1
timeout /t 2 /nobreak >nul

echo [1/6] Demarrage du Discovery Service (Eureka)...
start "Discovery Service" cmd /k "cd discovery-service && mvn spring-boot:run"
timeout /t 15 /nobreak >nul

echo [2/6] Demarrage du Config Service...
start "Config Service" cmd /k "cd config-service && mvn spring-boot:run"
timeout /t 10 /nobreak >nul

echo [3/6] Demarrage du Gateway Service...
start "Gateway Service" cmd /k "cd gateway-service && mvn spring-boot:run"
timeout /t 10 /nobreak >nul

echo [4/6] Demarrage du Beneficiaire Service...
start "Beneficiaire Service" cmd /k "cd bénéficiaire-service && mvn spring-boot:run"
timeout /t 10 /nobreak >nul

echo [5/6] Demarrage du Virement Service...
start "Virement Service" cmd /k "cd virement-service && mvn spring-boot:run"
timeout /t 10 /nobreak >nul

echo [6/6] Demarrage du Chatbot Service...
start "Chatbot Service" cmd /k "cd chatbot-service && mvn spring-boot:run"

echo.
echo ========================================
echo Tous les services sont en cours de demarrage...
echo ========================================
echo.
echo Acces aux services:
echo - Eureka Dashboard: http://localhost:8761
echo - API Gateway: http://localhost:8008
echo - Swagger Beneficiaire: http://localhost:8081/swagger-ui.html
echo - Swagger Virement: http://localhost:8082/swagger-ui.html
echo - Swagger Chatbot: http://localhost:8083/swagger-ui.html
echo.
echo Note: Attendez que tous les services soient demarres avant de tester.
echo.
pause

