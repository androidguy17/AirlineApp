# Airline App 🛫

A modern Android application built with Clean Architecture, showcasing airline data with offline
capabilities, favorites management, and smooth pagination.

## 📋 Architecture Overview

This project implements **Clean Architecture** principles with clear separation of concerns across
three main layers:

### 🏗️ Layer Structure

```
app/
├── data/                    # Data Layer
│   ├── local/              # Room database entities & DAOs
│   ├── remote/             # Retrofit API interfaces & DTOs
│   ├── repository/         # Repository implementations
│   └── Mappers/            # Data transformation utilities
├── domain/                 # Domain Layer
│   ├── model/              # Domain models
│   ├── repository/         # Repository interfaces
│   └── use_case/           # Business logic use cases
├── presentation/           # Presentation Layer
│   ├── FlightList/         # Flight listing screen
│   ├── FlightDetails/      # Flight details screen
│   ├── Favorites/          # Favorites management screen
│   └── ui/theme/           # Material 3 theming
├── di/                     # Dependency Injection
└── common/                 # Shared utilities & constants
```

### 🔄 Data Flow

1. **Presentation Layer**: Compose screens with ViewModels
2. **Domain Layer**: Use cases contain business logic
3. **Data Layer**: Repositories coordinate between API and local database
4. **Local Storage**: Room database with offline caching
5. **Remote API**: Retrofit for network operations

### 🎯 Key Architecture Benefits

- **Separation of Concerns**: Each layer has a single responsibility
- **Testability**: Dependencies are injected and easily mockable
- **Maintainability**: Clear boundaries make code easier to modify
- **Scalability**: New features can be added without affecting existing code

## 🛠️ Tools & Libraries Used

### Core Framework

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Material Design 3** - Design system with dynamic theming

### Architecture & DI

- **Dagger Hilt** - Dependency injection framework
- **Android Architecture Components** - ViewModel, LiveData, Navigation

### Database & Caching

- **Room Database** - Local data persistence
    - FlightEntity, FavoritesEntity, RemoteKeys
    - Type-safe SQL queries with DAOs
    - Migration support

### Networking

- **Retrofit 2** - REST API client
- **OkHttp** - HTTP client with logging interceptor
- **Gson** - JSON serialization/deserialization

### Pagination & Performance

- **Paging 3** - Efficient data loading and pagination
- **RemoteMediator** - Coordinates between API and local database
- **LazyColumn** - Performant list rendering

### Image Loading

- **Coil** - Image loading library optimized for Compose
- if image does not load, then first two initials of the name get shown

### Navigation

- **Navigation Compose** - Type-safe navigation between screens

## 🎨 Features Implemented

### Dynamic Material 3 Theming

- **Dynamic Color Scheme**: Adapts to user's wallpaper colors (Android 12+)
- **Dark Theme Support**: Automatic system theme detection
- **Consistent Design**: Material 3 components throughout the app

### Offline-First Architecture

- **Local Caching**: All flight data cached in Room database
- **Offline Access**: App works without internet connection
- **Smart Sync**: RemoteMediator handles data synchronization

### Advanced Pagination

- **Infinite Scrolling**: Seamless loading of large datasets
- **Loading States**: Progress indicators for better UX
- **Error Handling**: Graceful error handling

## 🚀 Build & Run Instructions

### Prerequisites

- **Android Studio** Hedgehog | 2023.1.1 or later
- **JDK 11** or higher
- **Android SDK** with API level 28+ (minimum) and 36 (target)
- **Node.js** (for local JSON server setup)

### Local Development Setup

#### 1. **Set Up Mock JSON Server**

The app includes a local JSON server for development and testing purposes.

**Install Dependencies:**

use this to create a test server
https://github.com/typicode/json-server/tree/v0

**Start the JSON Server:**
- add db.json (which i have attached in webserver-files>db.json) in this project
-  add this to your  scripts"serve-json": "json-server --watch db.json --host 10.0.2.2 --port 3000"
```bash
npm run serve-json
```

This will start the server at `http://10.0.2.2:3000` - **Important**: The IP address `10.0.2.2` is
specifically used for Android emulator networking, as it maps to the host machine's localhost.

**Why 10.0.2.2?**

- Android emulator runs in its own network
- `10.0.2.2` is the special IP that maps to the host machine's `127.0.0.1`
- This allows the Android app to connect to your local development server

#### 2. **Configure API Endpoint**

-i have already setted the url and everything, if it does not work then you can change it by 
Update the base URL in `app/src/main/java/com/example/airlineapp/common/Constants.kt`:

```kotlin
const val BASE_URL = "http://10.0.2.2:3000/"
```

#### 3. **Available API Endpoints**


Once the JSON server is running, you can access:

- **Get all flights**: `GET http://10.0.2.2:3000/flights`
- **Get single flight**: `GET http://10.0.2.2:3000/flights/{id}`
- **Pagination**: `GET http://10.0.2.2:3000/flights?_page=1`

The server includes 32 sample airlines with mock data for testing.


### Setup Steps

1. **Clone the Repository**
 
2. **Set Up JSON Server**
   ```bash
   npm install
   npm run serve-json
   ```

3. **Open in Android Studio**
    - Launch Android Studio


4. **Run the Application**


### Testing with Physical Device

If testing on a physical device, you'll need to:

1. Find your computer's IP address on the local network
2. Update `Constants.kt` with your actual IP: `http://YOUR_IP_ADDRESS:3000/`
3. Ensure your device and computer are on the same WiFi network

## 🎯 Challenges & Improvements

### Major Challenges Overcome

#### 1. Paging 3 Library Implementation

**Challenge**: The Paging 3 library was initially complex to understand and implement correctly.

**Learning Process**:

- Had to deep-dive into RemoteMediator pattern
- Understanding the relationship between LoadType (REFRESH, APPEND, PREPEND)
- Properly managing RemoteKeys for pagination state
- Coordinating between API responses and local database

**Solution Implemented**:

- Created a comprehensive RemoteMediator with proper key management
- Implemented database transactions for data consistency
- Added proper end-of-pagination detection logic
- Created helper functions for different load scenarios

**Key Learnings**:

- RemoteKeys are essential for maintaining pagination state
- Database transactions ensure data consistency during refresh
- Proper LoadType handling prevents duplicate or missing data

#### 2. Clean Architecture Integration

**Challenge**: Maintaining clean boundaries between layers while implementing complex features.

**Solution**:

- Strict interface definitions in the domain layer
- Repository pattern for data abstraction
- Use cases for business logic encapsulation
- Proper dependency injection setup

### Current Features

✅ **Dynamic Material 3 Theming**

- Automatic color extraction from user's wallpaper
- Seamless dark/light theme switching
- Consistent Material Design 3 components

✅ **Advanced Dark Theme Implementation**

- System theme detection
- Custom color schemes for both light and dark modes
- Dynamic color support for Android 12+ devices
- 
- Added Search feature
- Added add to fav option
- Added Pagination, for testing add items more than 10, as the app requests new iteam in the batches of 10
- uses http://10.0.2.2:3000/flights?_page=2. to paginate


## 📱 Screenshots
[![Logo](images/flight_list.png)](images/flight_list.png)
![](images/flight_list.png)  
![](images/flight_details.png)  
![](images/favorites_screen.png)  
![](images/dark_mode.png)  

---

*Built with ❤️ using Clean Architecture, Jetpack Compose, and Material Design 3*
