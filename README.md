# Firebase Auth Module

This project is an authentication module using Firebase, Jetpack Compose, and Hilt to manage Gmail account login. The project follows Clean Architecture principles for a clear and maintainable structure.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contribution](#contribution)
- [License](#license)

## Overview

This module allows users to log in using their Google account via Firebase Authentication. The user interface is built with Jetpack Compose and dependency management is handled by Hilt.

## Architecture

The project is structured according to Clean Architecture principles:

- **Presentation Layer**: Contains ViewModels, Composables, and navigation logic.
- **Domain Layer**: Contains models, repository interfaces, and use cases.
- **Data Layer**: Contains repository implementations, data sources, and data entities.

## Prerequisites

- Android Studio Chipmunk or higher
- Kotlin 1.8.0 or higher
- Firebase account with a configured project
- Google API key for Google Sign-In

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/yourusername/firebase-auth-module.git
    cd firebase-auth-module
    ```

2. Open the project in Android Studio.

3. Add your Firebase configuration:
    - Download the `google-services.json` file from the Firebase console and place it in the `app` directory of your project.

4. Add the OAuth 2.0 client ID to `res/values/strings.xml`:

    ```xml
    <string name="default_web_client_id">YOUR_WEB_CLIENT_ID</string>
    ```

5. Sync your project with Gradle.

## Usage

### Running the Application

1. Run the application on an emulator or a real device.
2. The first view displayed will be the login screen.
3. Click the "Sign in with Google" button to start the login process.
4. Once logged in, you will be redirected to the welcome screen displaying the logged-in user's information.
5. You can log out by clicking the "Log Out" button.

## Contribution

Contributions are welcome! Please submit a pull request or open an issue to discuss the changes you wish to make.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


