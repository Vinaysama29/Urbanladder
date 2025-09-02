@echo off
cd /d "C:\Users\2421218\Downloads\cucumber_fp\UrbanLadder"

echo Running Maven Tests...
call mvn clean test

echo Generating Allure Report...
call allure generate target/allure-results -o test-output/allure-report --clean

echo Opening Allure Report...
call allure open test-output/allure-report

pause
