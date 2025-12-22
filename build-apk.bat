@echo off
echo Building WhisperCove APK...

REM Check if Gradle wrapper exists
if not exist "gradlew.bat" (
    echo Gradle wrapper not found. Please run this script from the project root.
    pause
    exit /b 1
)

REM Clean previous builds
echo Cleaning previous builds...
call gradlew clean

REM Build debug APK
echo Building debug APK...
call gradlew assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo Debug APK built successfully!
    echo APK location: app\build\outputs\apk\debug\app-debug.apk
) else (
    echo Failed to build debug APK.
    pause
    exit /b 1
)

REM Build release APK (if keystore exists)
if exist "keystore.jks" (
    echo Building release APK...
    call gradlew assembleRelease
    
    if %ERRORLEVEL% EQU 0 (
        echo Release APK built successfully!
        echo APK location: app\build\outputs\apk\release\app-release.apk
    ) else (
        echo Failed to build release APK.
    )
) else (
    echo Keystore not found. Skipping release build.
    echo To create a release APK, generate a keystore file first.
)

echo Build process completed.
pause