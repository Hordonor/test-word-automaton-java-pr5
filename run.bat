@echo off
setlocal
chcp 65001 >nul
set "MAVEN_OPTS=-Dfile.encoding=UTF-8"
call mvnw.cmd clean test
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" goto finish
call mvnw.cmd exec:java
set "RESULT=%ERRORLEVEL%"

:finish
echo.
echo Press any key to close this window...
pause >nul
endlocal & exit /b %RESULT%
