import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';



class TabPage extends StatefulWidget {
  const TabPage({Key? key}) : super(key: key);

  @override
  _TabPageState createState() => _TabPageState();
}

class _TabPageState extends State<TabPage> with TickerProviderStateMixin {

  late TabController _tabController;

  @override
  void initState() {
    _tabController = TabController(
      length: 2,
      vsync: this,  //vsync에 this 형태로 전달해야 애니메이션이 정상 처리됨
    );
    super.initState();
  }

  @override
  Widget build(BuildContext context) {



    void _showDialog(String text, String contents) {
      showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext context) {
          return AlertDialog(
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(8.0)
            ),
            title: new Text(text),
            content: SingleChildScrollView(child:new Text(contents)),
            actions: <Widget>[
              new TextButton(
                child: new Text("Close"),
                onPressed: () {
                  Navigator.pop(context);
                },
              ),
            ],
          );
        },
      );
    }



    return Scaffold(
      appBar: AppBar(
        title: Text(
          '환영합니다.',
        ),
      ),

      body: Column(
        children: [
          Container(
            decoration: BoxDecoration(
              border: Border.all(),
            ),
            child: TabBar(
              tabs: [
                Container(
                  height: 80,
                  alignment: Alignment.center,
                  child: Text(
                    '로그인하기',
                  ),
                ),
                Container(
                  height: 80,
                  alignment: Alignment.center,
                  child: Text(
                    '회원가입 하기',
                  ),
                ),
              ],
              indicator: BoxDecoration(
                gradient: LinearGradient(  //배경 그라데이션 적용
                  begin: Alignment.centerLeft,
                  end: Alignment.centerRight,
                  colors: [
                    Colors.blueAccent,
                    Colors.pinkAccent,
                  ],
                ),
              ),
              labelColor: Colors.white,
              unselectedLabelColor: Colors.black,
              controller: _tabController,
            ),
          ),


          Expanded(
            child: TabBarView(
              controller: _tabController,
              children: [
                // Column({
                //   Key key,
                //   MainAxisAlignment mainAxisAlignment = MainAxisAlignment.start,
                //   MainAxisSize mainAxisSize = MainAxisSize.max,
                //   CrossAxisAlignment crossAxisAlignment = CrossAxisAlignment.center,
                //   TextDirection textDirection,
                //   VerticalDirection verticalDirection = VerticalDirection.down,
                //   TextBaseline textBaseline,
                //   List<Widget> children = const <Widget>[],
                // }),
                // Padding(
                //   padding: const EdgeInsets.only(
                //       left: 15.0, right: 15.0, top: 15, bottom: 0),
                //   //padding: EdgeInsets.symmetric(horizontal: 15),
                //   child: TextField(
                //     obscureText: true,
                //     decoration: InputDecoration(
                //         border: OutlineInputBorder(),
                //         labelText: 'Password',
                //         hintText: 'Enter secure password'),
                //   ),
                // ),
                // TextButton(
                //   onPressed: () {
                //     //TODO FORGOT PASSWORD SCREEN GOES HERE
                //   },
                //   child: Text(
                //     'Forgot Password',
                //     style: TextStyle(color: Colors.blue, fontSize: 15),
                //   ),
                // ),
                // Container(
                //   height: 50,
                //   width: 250,
                //   decoration: BoxDecoration(
                //       color: Colors.blue, borderRadius: BorderRadius.circular(20)),
                //   child: TextButton(
                //     onPressed: () {
                //       _showDialog("로그인 성공", "성공");
                //       // Navigator.push(
                //       //     context, MaterialPageRoute(builder: (_) => MainPage()));
                //     },
                //     child: Text(
                //       'Login',
                //       style: TextStyle(color: Colors.white, fontSize: 25),
                //     ),
                //   ),
                // ),
                // Container(
                //     height: 50,
                //     width: 250,
                //     decoration: BoxDecoration(
                //         color: Colors.red, borderRadius: BorderRadius.circular(20)),
                //     child: ElevatedButton(
                //       onPressed: _callAPI,
                //       child: const Text('Call API'),
                //     )),
                // SizedBox(
                //   height: 130,
                // ),
                // Text('New User? Create Account'),


                Container(
                  color: Colors.yellow[200],
                  alignment: Alignment.center,
                  child: Text(
                    'Tab1 View',
                    style: TextStyle(
                      fontSize: 30,
                    ),

                  ),
                ),







                Container(
                  color: Colors.green[200],
                  alignment: Alignment.center,
                  child: Text(
                    'Tab2 View',
                    style: TextStyle(
                      fontSize: 30,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}





void _callAPI() async {
  var url = Uri.parse(
    'http://localhost:9000/auth/signin',
  );

  print(url);
  var data = {"email": "local-user@mail.com", "password": "1111"};
  var jsonbody = json.encode(data);

  var response = await http.post(url,
      body: jsonbody, headers: {"Content-Type": "application/json"});
  print('${response}');
  print('${response.statusCode}');
  print('${response.body}');

  // print('Response status: ${response.statusCode}');
  // print('Response body: ${response.body}');
}