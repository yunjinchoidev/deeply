import 'dart:io';

import 'package:deeply_flutter/landingpage.dart';
import 'package:deeply_flutter/mainpage.dart';
import 'package:deeply_flutter/mainpages/homescreen.dart';
import 'package:deeply_flutter/mainpages/loginscreen.dart';
import 'package:deeply_flutter/mainpages/mypagescreen.dart';
import 'package:deeply_flutter/mainpages/registerscreen.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:provider/provider.dart';
import 'firebase_options.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );


  runApp(Myapp());
}

class Myapp extends StatelessWidget {

  const Myapp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(



      // initialRoute: '/',
      // routes: {
      //   // When we navigate to the "/" route, build the FirstScreen Widget
      //   // "/" Route로 이동하면, FirstScreen 위젯을 생성합니다.
      //   // '/': (context) => MainPage(),
      //   // "/second" route로 이동하면, SecondScreen 위젯을 생성합니다.
      //   '/service': (context) => MainPage(),
      //   '/login': (context) => LoginApp(),
      //   '/signup': (context) => RegisterApp(),
      //   '/service': (context) => HomeScreen(),
      //   '/mypage': (context) => MyPageApp(),
      // },






      theme: ThemeData(fontFamily: 'bamin'),
      // home: LandingPage(),

      home:

      FutureBuilder(
        future: Firebase.initializeApp(), // 선언해야 할 함수
        builder: (context, snapshot) {
          if (snapshot.hasError) { // 만약 선언 시 에러가 나면 출력될 위젯
            return Center(
              child: Text('Error'),
            );
          }
// 선언 완료 후 표시할 위젯
          if (snapshot.connectionState == ConnectionState.done) {
            _getToken();
            _initFirebaseMessaging(context);
            return LandingPage();
          }
// 선언되는 동안 표시할 위젯
          return Center(
            child: CircularProgressIndicator(),
          );
        },

      ),



    );
  }
}

_getToken() async {
  FirebaseMessaging messaging = FirebaseMessaging.instance;
  print("messaging.getToken() , ${await messaging.getToken()}");
}

_initFirebaseMessaging(BuildContext context) {
  FirebaseMessaging.onMessage.listen((RemoteMessage event) {
    print(event.notification!.title);
    print(event.notification!.body);
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text("알림"),
            content: Text(event.notification!.body!),
            actions: [
              TextButton(
                child: Text("Ok"),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  });
  FirebaseMessaging.onMessageOpenedApp.listen((RemoteMessage message) {});
}

