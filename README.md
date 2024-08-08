# FeedzaiTest

FeedzaiTest is an Android application . This app gathers device information, presents it, and sends it to an API in a secure way.

## Info gathered

- Local ip
- Public ip
- User Agent
- VPN Status
- Language
- Timezone
- Device location

## Requirements

- Android Studio (developed using Android Studio Koala | 2024.1.1)
- Minimum Android SDK version: 30 (Android 11)
- Kotlin support

## Installation

1. Clone the repository: ```$ git clone https://github.com/AdrianMarinGonzalez/FeedzaiTest.git```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the application on an emulator or physical device running Android 11 or higher.

## Configuration

Before running the app, you need to configure the data processing endpoint

1. Modify ```feedzaiEndpoint``` inside ```FeedzaiTest/data/src/main/java/com/example/data/network/base/Constants.kt``` with your endpoint
2. Modify ```keyTxt``` inside ```FeedzaiTest/data/src/main/java/com/example/data/network/base/Constants.kt``` with the encryption key you want to use

## Sample Execution

