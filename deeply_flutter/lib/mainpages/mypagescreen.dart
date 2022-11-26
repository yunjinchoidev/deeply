import 'package:flutter/material.dart';

class MyPageApp extends StatefulWidget {
  const MyPageApp({Key? key}) : super(key: key);

  @override
  State<MyPageApp> createState() => _MyPageAppState();
}

class _MyPageAppState extends State<MyPageApp> {
  String sum = '';
  TextEditingController value1 = TextEditingController();
  TextEditingController value2 = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("My page"),
      ),
      body: Column(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.all(15),
            child: Text("계산기"),
          ),
          Padding(
            padding: EdgeInsets.all(15),
            child: TextField(
                keyboardType: TextInputType.number, controller: value1),
          ),
          Padding(
            padding: EdgeInsets.all(15),
            child: TextField(
                keyboardType: TextInputType.number, controller: value2),
          ),
          Padding(
              padding: EdgeInsets.all(15),
              child: ElevatedButton(
                onPressed: () {
                  setState(() {
                    int result = int.parse(value1.value.text)+int.parse(value2.value.text);
                    sum = '$result';
                  });

                },
                child: Row(
                  children: <Widget>[Icon(Icons.add), Text("더하기")],
                ),
              )),
          Padding(
              padding: EdgeInsets.all(14)
              ,child: Text(
            '결과 : $sum',
            style: TextStyle(fontSize: 20),
          ),
          ),
        ],
      ),
    );
  }
}
