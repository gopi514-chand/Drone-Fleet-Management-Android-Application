**🚁 Drone Fleet Management – Android Application**

**APK Location:** app/build/outputs/apk/debug/app-debug.apk

**📌 Project Overview**

Drone Fleet Management is an Android application designed to monitor and manage multiple drones from a single interface.
The app allows users to view, search, filter, sort, and inspect detailed information about drones, making it suitable for use cases such as agriculture, surveying, inspection, and fleet monitoring.

The application follows modern Android development practices using MVVM architecture, ensuring clean code, scalability, and easy maintenance.

🎯 Key Features

📋 Display a list of drones with real-time status

🔍 Search drones by ID, name, or location

🎛 Filter drones by Flying, Idle, Charging

🔃 Sort drones by battery percentage

🔋 Visual battery health indicator

📄 Detailed drone information screen

⚠️ Proper error handling

🌙 Dark mode support (optional)

📊 Dashboard statistics (optional)

🧱 Architecture Used

The application is built using MVVM (Model–View–ViewModel) architecture.

Model → Represents drone data

View → Activities / Fragments (UI layer)

ViewModel → Handles business logic

Repository → Single source of truth for data


This ensures:

Separation of concerns
Lifecycle-aware UI updates
Better testability and scalability

🔄 End-to-End Working Flow

When the user launches the Drone Fleet Management Android application, the app opens with the Main Activity, which hosts the Drone List Screen. At startup, the app initializes the MVVM architecture, where the View (Activity/Fragment) communicates with the ViewModel, and the ViewModel interacts with the Repository to fetch drone data.

The Repository layer acts as the single source of truth. It provides drone data using hardcoded sample data, local JSON, or a mock API (Retrofit). The repository returns a list of drone model objects to the ViewModel, which then processes this data and exposes it to the UI using LiveData or StateFlow, ensuring lifecycle-safe updates.

Once the data reaches the Drone List Screen, it is displayed using a RecyclerView. Each drone appears inside a card showing the Drone ID, Drone Name, Battery Percentage, Current Status, and Location. The RecyclerView Adapter uses ViewBinding, ensuring type-safe UI access and preventing runtime errors.

A battery indicator is displayed visually for each drone. Based on the battery percentage, the app categorizes the battery health as:

Healthy (> 50%)
Warning (20–50%)
Critical (< 20%)

This is represented using a progress bar, text label, or icon, providing quick visual feedback.

When the user enters text in the search box, the input is sent instantly to the ViewModel. The ViewModel filters the original drone list based on Drone ID, Name, or Location, and the updated list is reflected in the RecyclerView in real time. If no results are found, the app shows a “No results found” message.

If the user selects a filter option such as Flying, Idle, or Charging, the selected status is passed to the ViewModel, which applies the filter and updates the displayed list. Since filtering and searching always work on the base data set, the UI remains smooth and consistent.

For sorting, when the user chooses High-to-Low or Low-to-High battery, the ViewModel sorts the drone list by battery percentage and updates the RecyclerView instantly.

When the user taps on a drone card, the app navigates to the Drone Detail Screen using the Navigation Component. The selected drone (or its ID) is passed safely between screens. The detail screen displays full drone information including Drone ID, Name, Battery, Status, Location, along with dummy values such as Altitude, Speed, and Last Updated Time.

The Drone Detail Screen also includes a detailed battery indicator. All data displayed on this screen is again driven by the ViewModel, maintaining a clean separation between UI and logic.

If Pull-to-Refresh is enabled, the ViewModel requests updated data from the repository, shows a loading indicator, and refreshes the list once data is loaded.

If a Room Database is implemented, the repository first checks for cached drone data for offline access. If local data is unavailable, it fetches data from the API and stores it in Room for future use.

Throughout the application, error handling is implemented for cases such as:

Empty drone list
Empty search results
Invalid or missing data

Instead of crashing, the app always shows meaningful messages to guide the user.

Optional features like Dashboard Statistics provide a quick overview of total drones and their operational status, while Dark Mode support improves usability in low-light environments.


🔁 Final Flow Summary (One-Line)

User → UI → ViewModel → Repository → Data Source → ViewModel → RecyclerView → Detail Screen

**🛠 Tech Stack**

Language: Kotlin

UI: XML, RecyclerView, ViewBinding

Architecture: MVVM

Data Handling: Repository Pattern / Retrofit (Mock API)
Optional: Room Database, Material Design, Dark Mode

**🚀 How to Run the Project**

Clone the repository
Open in Android Studio
Sync Gradle
Run on emulator or physical device

**📌 Use Cases**

Drone fleet monitoring
Agriculture drones
Surveying and mapping
Inspection and logistics

**📄 License**

This project is for learning and demonstration purposes.


