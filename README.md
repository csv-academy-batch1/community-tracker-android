# community-tracker-android

CSV Community Tracker - Android App Using Kotlin
====================================================

## Building the Sample App

First, clone the repo:
`git clone git@github.com:csv-academy-batch1/community-tracker-android.git`

### Android Studio (Recommended)

* Install Android Studio using this [guide](https://developer.android.com/studio/install)
* Open Android Studio and select `Project->Open...` and navigate to the root directory of your project.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed by going to `Build->Make Project`.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the Sample App

1. Connect an Android device to your development machine (Recommended)
2. Run an Android Emulator in Android Studio

### Android Studio

* To create an Android Emulator in Android Studio use this [guide](https://developer.android.com/studio/run/managing-avds#createavd)
* Select the device you wish to run the app on and click 'OK' (Either a physical device or android emulator)
* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar

### Gradle

* Install the debug APK on your device `./gradlew installDebug`
* Start the APK: `<path to Android SDK>/platform-tools/adb -d shell am start com.softvision.communitytrackerandroid/com.softvision.communitytrackerandroid.MyActivity`
