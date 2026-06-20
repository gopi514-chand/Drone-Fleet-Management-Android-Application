@echo off
set DIR=%~dp0
if exist "%DIR%gradle\wrapper\gradle-wrapper.jar" (
	java -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*
) else (
	echo Gradle wrapper JAR not found in "%DIR%gradle\wrapper\"
	echo Please run 'gradle wrapper' or download the gradle-wrapper.jar into that folder.
	exit /b 1
)
