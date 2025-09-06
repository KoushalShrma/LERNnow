@echo off
REM LEARNnow Start Script
echo Starting LEARNnow platform...

REM Start the Spring Boot backend
echo Starting Spring Boot backend...
start cmd /k "cd %~dp0 && mvnw spring-boot:run"

REM Wait for backend to initialize
echo Waiting for backend to initialize...
timeout /t 10 /nobreak

REM Start the Next.js frontend
echo Starting Next.js frontend...
start cmd /k "cd %~dp0\Desired Frontend && npm run dev"

echo.
echo LEARNnow platform is starting up!
echo Backend URL: http://localhost:8080
echo Frontend URL: http://localhost:3000
echo.
echo Press any key to stop all services...
pause

REM Kill all node and java processes
taskkill /f /im java.exe
taskkill /f /im node.exe
