# 8145474Assignment2 - NIT3213 Assignment 2

## Project Overview
This Android application is developed for the NIT3213 Mobile Application Development unit. The application serves as a "Tech Explorer" that authenticates users through a dedicated API, retrieves a dynamic `keypass`, and utilizes that keypass to fetch and display a curated list of technology entities from a dashboard endpoint. The app is built with a focus on modern Android architecture, professional UI design, and robust data handling.

## Features
- **Secure Authentication**: API-based login system with student-specific credentials.
- **Dynamic Data Retrieval**: Fetches a specific dataset (Technology) based on the authentication response.
- **Modern UI/UX**: Professional "Tech Explorer" theme using Material 3 components and a cohesive deep indigo palette.
- **List & Detail Flow**: A highly optimized RecyclerView list with detailed views for each entity.
- **Robust Architecture**: Built using MVVM (Model-View-ViewModel) and the Repository pattern.
- **Dependency Injection**: Fully configured with Hilt for loose coupling and testability.
- **Reactive State**: Uses Kotlin Coroutines and StateFlow for lifecycle-aware UI updates.
- **Unit Testing**: Local unit tests for critical business logic in ViewModels.

## Application Flow
1. **Login**: User enters credentials. On success, the API returns a `keypass`.
2. **Dashboard**: The app uses the `keypass` to fetch the Technology list. Items are displayed in a modern card-based RecyclerView.
3. **Details**: Tapping a card navigates to the Details screen, which displays comprehensive information about the selected device.

## Architecture
The project follows a clean, modular structure:
- **`data`**: Contains Kotlin data classes for API requests and responses (Moshi-ready).
- **`network`**: Defines the Retrofit `ApiService` and API endpoints.
- **`repository`**: Implements the Repository pattern to abstract data sources.
- **`di`**: Hilt modules (`NetworkModule`, `RepositoryModule`) for providing dependencies.
- **`ui/login`**: Fragment and ViewModel for the authentication screen.
- **`ui/dashboard`**: List UI, Adapter, and ViewModel for the technology list.
- **`ui/details`**: Detailed view for technology entities.

**MVVM Flow**: `UI (Fragment) → ViewModel → Repository → ApiService → Remote Server`

## API Integration
The app communicates with the following endpoints:
- **POST `/sydney/auth`**: Authenticates the user.
  - Request: `{"username": "YOUR_STUDENT_ID", "password": "YOUR_FIRST_NAME"}`
- **GET `/dashboard/{keypass}`**: Retrieves the technology dataset using the dynamic keypass.

## Dependency Injection
Hilt is utilized to manage the application lifecycle and provide singletons:
- **`TechnologyApp`**: Initialized with `@HiltAndroidApp`.
- **`NetworkModule`**: Provides `Retrofit`, `Moshi`, and `ApiService` instances.
- **`RepositoryModule`**: Binds the `TechnologyRepository` interface to its implementation.

## Technologies and Dependencies
- **Kotlin**: Core language.
- **Hilt**: Dependency Injection.
- **Retrofit & Moshi**: REST API communication and JSON parsing.
- **Navigation Component**: Type-safe navigation with Safe Args.
- **Coroutines & StateFlow**: Asynchronous programming and reactive UI updates.
- **RecyclerView**: Efficient list rendering.
- **Material 3**: Modern UI components and styling.

## Unit Testing
The project includes meaningful local unit tests:
- **`LoginViewModelTest`**: Tests initial state, validation logic, successful login flow, and API error handling.
- **`DashboardViewModelTest`**: Verifies the loading state, successful data retrieval, and error reporting.
- **`MainDispatcherRule`**: A custom JUnit rule for reliable coroutine testing.

## How to Build and Run
1. **Clone the repository** to your local machine.
2. **Open the project** in Android Studio (Ladybug or newer recommended).
3. **Allow Gradle Sync** to download the necessary dependencies.
4. **Select an Emulator or physical device** running Android 7.0 (API 24) or higher.
5. **Run the 'app' configuration**.
6. **Login** using your assigned NIT3213 credentials.

*Note: An active internet connection is required for API functionality. Permission is already configured in the manifest.*

## Running Unit Tests
To run the local unit tests:
- **In Android Studio**: Right-click on the `test` folder and select "Run 'Tests in com.enkhmaa...'"
- **Via Command Line**: Run `./gradlew testDebugUnitTest`

## Requirements
- Android Studio (Modern version)
- JDK 11+
- Android SDK 37 (Compile/Target)
- Internet connection for API access

## Security / Credentials
User credentials are entered at runtime and are never hardcoded or stored in the source control.

## Author
**Student ID**: 8145474  
**Unit**: NIT3213 Mobile Application Development
