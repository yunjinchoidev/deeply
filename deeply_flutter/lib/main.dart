import 'dart:io';

import 'package:deeply_flutter/landingpage.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';


void main() {
  HttpOverrides.global = MyHttpOverrides();
  runApp(Myapp());
}

class Myapp extends StatelessWidget {

  const Myapp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      theme: ThemeData(fontFamily: 'bamin'),
      home: LandingPage(),
    );
  }
}


class MyHttpOverrides extends HttpOverrides {
  @override
  HttpClient createHttpClient(SecurityContext? context) {
    return super.createHttpClient(context)
      ..badCertificateCallback =
          (X509Certificate cert, String host, int port) => true;
  }
}