import 'dart:async';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';

import 'mainpage.dart';


class LandingPage extends StatelessWidget {
  const LandingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      home: MaterialFlutterApp(),
    );
  }
}

class MaterialFlutterApp extends StatefulWidget {


  const MaterialFlutterApp({Key? key}) : super(key: key);

  @override
  State<MaterialFlutterApp> createState() => _MaterialFlutterAppState();
}





class _MaterialFlutterAppState extends State<MaterialFlutterApp> {

  @override
  void initState() {
    // TODO: implement initState
    Timer(Duration(seconds: 1),(){
      Get.offAll(MainPage());
    });
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Deeply world 에 오신 여러분을 환영합니다.'),),
      // floatingActionButton: FloatingActionButton(child: Icon(Icon)),
      body: Container(
        child: Image.asset('asset/image/deeply.png'),
      ),

    );
  }
}
