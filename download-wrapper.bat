@echo off
echo 正在下载gradle-wrapper.jar...
powershell -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.9-wrapper.jar' -OutFile 'gradle\wrapper\gradle-wrapper.jar'"
echo 下载完成!
pause