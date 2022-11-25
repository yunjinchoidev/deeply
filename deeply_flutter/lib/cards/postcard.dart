import 'dart:math';

import 'package:flutter/material.dart';


class PostCard extends StatefulWidget {
  // const PostCard({Key? key}) : super(key: key);

  int number;

  PostCard({required this.number});

  @override
  State<PostCard> createState() => _PostCardState();
}

class _PostCardState extends State<PostCard> {

  // PageController _controller = PageController();


  @override
  Widget build(BuildContext context) {
    return Container(
        child: Column(
            children: [
              Container(
                  height: 50,
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  color: Colors.grey,
                  child: Center(child: Text("제목"))
              ),
              AspectRatio(
                aspectRatio: Random().nextDouble(),

                child: PageView(
                   // controller: _controller,
                    children: [
                      Container(
                        height: 400,
                        width: MediaQuery
                            .of(context)
                            .size
                            .width,
                        child: Image.network(
                            'https://source.unsplash.com/random',
                            fit: BoxFit.cover),
                        // color: Colors.purple,
                        // child: Center(child: Text("사진"))
                      ),
                      Container(
                        height: 400,
                        width: MediaQuery
                            .of(context)
                            .size
                            .width,
                        child: Image.network(
                            'https://source.unsplash.com/random',
                            fit: BoxFit.cover),
                        // color: Colors.purple,
                        // child: Center(child: Text("사진"))
                      ), Container(
                        height: 400,
                        width: MediaQuery
                            .of(context)
                            .size
                            .width,
                        child: Image.network(
                            'https://source.unsplash.com/random',
                            fit: BoxFit.cover),
                        // color: Colors.purple,
                        // child: Center(child: Text("사진"))
                      )
                    ]
                ),
              ),
              // ScrollingPageIndicator(
              //     dotColor: Colors.grey,
              //     dotSelectedColor: Colors.deepPurple,
              //     dotSize: 6,
              //     dotSelectedSize: 8,
              //     dotSpacing: 12,
              //     controller: _controller,
              //     itemCount: 3,
              //     orientation: Axis.horizontal
              // ),
              Container(
                  height: 30,
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  color: Colors.pink,
                  child: Center(child: Text("아이콘"))
              ),
              Container(
                  height: 30,
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  color: Colors.blue,
                  child: Center(child: Text("좋아요"))
              ),
              Container(
                  height: 30,
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  color: Colors.yellow,
                  child: Center(child: Text("댓글"))
              ),

              // Container(
              //   width: double.infinity,
              //   height: 200,
              //   color: Colors.primaries[Random().nextInt(
              //       Colors.primaries.length)],
              //   child: Center(child: Text(widget.number.toString()),),
              // )
            ]
        )
    );
  }
}
