@echo off
cd /d "C:\seleniumGrid"

echo Starting selenium grid...
call java -jar selenium-server-4.35.0.jar standalone

pause
