// File generated by FlutterFire CLI.
// ignore_for_file: lines_longer_than_80_chars, avoid_classes_with_only_static_members
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        return macos;
      case TargetPlatform.windows:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for windows - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyAFjP5YwK1bEB8yu2_bpFTR2pD_G8K2d24',
    appId: '1:25662537538:web:4c8d5d0dfd40db817f12db',
    messagingSenderId: '25662537538',
    projectId: 'deeply-b5979',
    authDomain: 'deeply-b5979.firebaseapp.com',
    databaseURL: 'https://deeply-b5979-default-rtdb.firebaseio.com',
    storageBucket: 'deeply-b5979.appspot.com',
    measurementId: 'G-JPWW7C056K',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyBv_Q4phn4ZN2ZBGuun07KaLLYiEpSklgg',
    appId: '1:25662537538:android:8d6b7453926bda857f12db',
    messagingSenderId: '25662537538',
    projectId: 'deeply-b5979',
    databaseURL: 'https://deeply-b5979-default-rtdb.firebaseio.com',
    storageBucket: 'deeply-b5979.appspot.com',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyBT5nF6QACqRYRZVHL-zrVDOGd-YodC_4w',
    appId: '1:25662537538:ios:c8f06b5fdd2e233c7f12db',
    messagingSenderId: '25662537538',
    projectId: 'deeply-b5979',
    databaseURL: 'https://deeply-b5979-default-rtdb.firebaseio.com',
    storageBucket: 'deeply-b5979.appspot.com',
    iosClientId: '25662537538-1qjc3d40hioqkn70mkgdueig217bkvts.apps.googleusercontent.com',
    iosBundleId: 'com.server.deeplyFlutter',
  );

  static const FirebaseOptions macos = FirebaseOptions(
    apiKey: 'AIzaSyBT5nF6QACqRYRZVHL-zrVDOGd-YodC_4w',
    appId: '1:25662537538:ios:c8f06b5fdd2e233c7f12db',
    messagingSenderId: '25662537538',
    projectId: 'deeply-b5979',
    databaseURL: 'https://deeply-b5979-default-rtdb.firebaseio.com',
    storageBucket: 'deeply-b5979.appspot.com',
    iosClientId: '25662537538-1qjc3d40hioqkn70mkgdueig217bkvts.apps.googleusercontent.com',
    iosBundleId: 'com.server.deeplyFlutter',
  );
}
