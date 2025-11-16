@echo off
echo ========================================
echo Liberation du port 8080
echo ========================================
echo.
echo Recherche des processus utilisant le port 8080...
netstat -ano | findstr :8080 | findstr LISTENING
if errorlevel 1 (
    echo Aucun processus trouve sur le port 8080.
    pause
    exit /b 0
)

echo.
echo Arret des processus...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
    echo Arret du processus PID: %%a
    taskkill /F /PID %%a
    if errorlevel 1 (
        echo ERREUR: Impossible d'arreter le processus %%a
        echo Essayez d'executer ce script en tant qu'administrateur (clic droit -^> Executer en tant qu'administrateur)
    ) else (
        echo Processus %%a arrete avec succes.
    )
)

echo.
echo Verification...
timeout /t 2 /nobreak >nul
netstat -ano | findstr :8080 | findstr LISTENING
if errorlevel 1 (
    echo Le port 8080 est maintenant libre!
) else (
    echo ATTENTION: Le port 8080 est toujours occupe.
    echo Vous devez peut-etre executer ce script en tant qu'administrateur.
)
echo.
pause

