class ResponseVO {
  final dynamic accessToken;

  ResponseVO({this.accessToken});

  factory ResponseVO.fromJSON(Map<String, dynamic> json){
    return ResponseVO(
        accessToken: json['accessToken']
    );
  }
}