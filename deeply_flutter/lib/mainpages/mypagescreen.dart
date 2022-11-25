import 'package:flutter/material.dart';

class MyPageApp extends StatefulWidget {
  const MyPageApp({Key? key}) : super(key: key);

  @override
  State<MyPageApp> createState() => _MyPageAppState();
}

class _MyPageAppState extends State<MyPageApp> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("My page"),
      ),
    );
  }
}
