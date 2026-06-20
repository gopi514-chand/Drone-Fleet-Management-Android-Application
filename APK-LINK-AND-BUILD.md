# Drone Fleet Management - APK Build & Link File

**App Name:** Drone Fleet Management  
**Package ID:** com.example.drone  
**App Version:** 1.0  
**Build Date:** 2026-06-20  
**Target SDK:** 34 (Android 14)  
**Min SDK:** 24 (Android 7.0)  

---

## 📱 APK Build Information

### Build Output Location
Once built successfully, the debug APK will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Release APK (for production)
To build a release APK, use:
```
./gradlew.bat assembleRelease
```

Release APK output location:
```
app/build/outputs/apk/release/app-release.apk
```

---

## 🛠 Quick Build Steps

### Prerequisites
- JDK 17 (or higher)
- Android SDK (API 34)
- Gradle 8.2+

### Step 1: Restore Gradle Wrapper (if needed)

If `gradle/wrapper/gradle-wrapper.jar` is missing, run this PowerShell command in the project root:

```powershell
$ErrorActionPreference = "Stop"
$ProgressPreference = "SilentlyContinue"
Write-Host "Downloading Gradle 8.2..."
(New-Object System.Net.WebClient).DownloadFile(
    'https://services.gradle.org/distributions/gradle-8.2-bin.zip',
    'gradle-8.2-bin.zip'
)
Write-Host "Extracting gradle-wrapper.jar..."
Add-Type -AssemblyName System.IO.Compression.FileSystem
$zip = [System.IO.Compression.ZipFile]::OpenRead('gradle-8.2-bin.zip')
$entries = $zip.Entries | Where-Object { $_.Name -eq 'gradle-wrapper.jar' }
if ($entries) {
    $entries[0] | ForEach-Object {
        [System.IO.Compression.ZipFileExtensions]::ExtractToFile($_, 'gradle\wrapper\gradle-wrapper.jar', $true)
    }
}
$zip.Dispose()
Remove-Item 'gradle-8.2-bin.zip' -Force
```

Or simply run the provided script:
```powershell
powershell -ExecutionPolicy Bypass -File restore-wrapper.ps1
```

### Step 2: Build Debug APK

```powershell
./gradlew.bat assembleDebug
```

### Step 3: Locate & Install APK

The debug APK will be at:
```
app/build/outputs/apk/debug/app-debug.apk
```

Install on an Android device:
```
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## 📦 APK Specifications

| Property | Value |
|----------|-------|
| **Application ID** | com.example.drone |
| **Min SDK** | 24 |
| **Target SDK** | 34 |
| **Compile SDK** | 34 |
| **Language** | Kotlin |
| **Architecture** | All (arm64-v8a, armeabi-v7a, x86, x86_64) |

---

## ✨ App Features Included

✅ Drone list with RecyclerView  
✅ Search by ID, name, or location  
✅ Filter by status (Flying/Idle/Charging)  
✅ Sort by battery percentage  
✅ Detailed drone view screen  
✅ Battery health indicators  
✅ Pull-to-refresh functionality  
✅ Error handling for empty lists  
✅ MVVM architecture with LiveData  
✅ ViewBinding for type-safe UI  

---

## 🏗 Project Structure

```
drone/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/drone/
│   │   │   ├── adapter/          (RecyclerView Adapter)
│   │   │   ├── model/            (Drone data class)
│   │   │   ├── repository/       (Data access layer)
│   │   │   └── ui/
│   │   │       ├── detail/       (DetailActivity)
│   │   │       └── list/         (MainActivity & ViewModel)
│   │   ├── res/
│   │   │   ├── layout/           (XML layouts)
│   │   │   └── values/           (Themes & resources)
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/wrapper/               (Gradle wrapper files)
├── gradlew & gradlew.bat         (Gradle wrapper scripts)
└── README.md
```

---

## 🔐 Signing for Release

To create a release APK for distribution:

1. Generate a keystore (if you don't have one):
```powershell
keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key
```

2. Add signing config to `app/build.gradle.kts`:
```kotlin
signingConfigs {
    create("release") {
        storeFile = file("my-release-key.jks")
        storePassword = "your_password"
        keyAlias = "my-key"
        keyPassword = "your_key_password"
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.getByName("release")
        isMinifyEnabled = true
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}
```

3. Build release APK:
```powershell
./gradlew.bat assembleRelease
```

---

## 📲 Installation Methods

### Method 1: Using ADB (Android Debug Bridge)
```powershell
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Method 2: Drag-and-Drop on Android Emulator
1. Run Android Emulator
2. Drag the APK file onto the emulator window
3. Follow installation prompts

### Method 3: Direct Transfer to Device
1. Copy APK to device storage via USB
2. Use file manager to locate and tap APK
3. Follow installation prompts

---

## 🔗 Download Links & Resources

| Resource | Link |
|----------|------|
| **Android Studio** | https://developer.android.com/studio |
| **Android SDK** | https://developer.android.com/studio/releases/sdk-tools |
| **JDK 17** | https://www.oracle.com/java/technologies/downloads/ |
| **ADB Tools** | https://developer.android.com/tools/adb |
| **Gradle 8.2** | https://services.gradle.org/distributions/gradle-8.2-bin.zip |

---

## 🐛 Troubleshooting

### "Gradle wrapper JAR not found"
- Run the `restore-wrapper.ps1` script
- Or manually download Gradle 8.2 and extract the JAR

### "Java not found"
- Ensure JDK 17 is installed
- Add JDK bin directory to system PATH
- Verify: `java -version`

### "Build fails with SDK not found"
- Open Android Studio → Settings → SDK Manager
- Install SDK API 34
- Set `ANDROID_HOME` environment variable

### APK not installing
- Ensure device has "Unknown Sources" enabled
- Check device storage has ~50MB free space
- Verify device API level ≥ 24

---

## 📝 APK Metadata

**Current Build Status:** Ready to build  
**Last Updated:** 2026-06-20  
**Build Configuration:** Debug (development)  
**Code Quality:** MVVM + ViewBinding (production-ready architecture)  

---

## 🚀 Next Steps

1. **Restore Gradle wrapper** using the PowerShell script above
2. **Build the APK** using `gradlew.bat assembleDebug`
3. **Install on device** using ADB or emulator
4. **Test features:** Search, Filter, Sort, and Detail screen
5. **For release:** Follow signing instructions and build release APK

---

*Drone Fleet Management - Android Application*  
*Version 1.0 | Build Date: 2026-06-20*
