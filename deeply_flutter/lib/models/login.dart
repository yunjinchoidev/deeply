class Login {
  final String email;
  final String password;
  final String id;

  Login(this.email, this.password, this.id);

  Login.fromJson(Map<String, dynamic> json)
      : email = json['email'],
        password = json['password'],
        id = json['id'];

  Map<String, dynamic> toJson() => {
    'email': email,
    'password': password,
    'id': id,
  };
}