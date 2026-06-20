Drone Fleet Management (Android)

Quick steps to build locally:

1) Ensure JDK 17 is installed and `java` is on PATH.

2) Restore Gradle wrapper JAR (if missing). Run in the project root (PowerShell):

```powershell
# download and extract only the wrapper JAR
$zip = "gradle-8.2-bin.zip"
Invoke-WebRequest 'https://services.gradle.org/distributions/gradle-8.2-bin.zip' -OutFile $zip
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::OpenRead($zip).Entries | Where-Object { $_.FullName -match 'gradle-wrapper.jar$' } | ForEach-Object { $_.ExtractToFile('gradle\\wrapper\\gradle-wrapper.jar', $true) }
Remove-Item $zip -Force
```

If `gradle` is installed system-wide, you can instead run:

```powershell
gradle wrapper
```

3) Build the app:

```powershell
# Windows
./gradlew.bat assembleDebug
```

Notes:
- If you prefer to build from Android Studio, open the project and let it download the wrapper automatically.
- I added `DetailActivity` to the manifest and verified ViewBinding is enabled in `app/build.gradle.kts`.

Next steps I can take now:
- Add Room persistence and Dashboard stats.
- Add dark mode and save UI preferences.
- Try to download the wrapper here if you want me to continue attempting that automatically.
