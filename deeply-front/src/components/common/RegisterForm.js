import React, {useEffect} from 'react';
import {Button, Form} from "react-bootstrap";
import axios from 'axios'


const LoginForm = (props) => {

    const loginDB = (id, password) => {
        return function () {
            axios({
                method: "post",
                url: "http://localhost:9000/auth/signup",
                data: {
                    email: 'cyj212@gmail.com',
                    password: '1111',
                },
            })
                .then((res) => {
                    console.log(res);
                    const accessToken = res.data.token;
                    alert("회원가입에 성공했습니다.. \n 디플리에 오신 걸 환영합니다.")
                    document.location.href = "/";
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }

        useEffect(() => {
            console.log("LoginPage render ... ");
        })

        return (
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" placeholder="Password"/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control type="password" placeholder="Password"/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out"/>
                </Form.Group>

                <Button variant="primary" type="submit">
                    작성 완료
                </Button>
                <Button variant="dark" type="reset">
                    초기화
                </Button>
                <Button variant="info" type="button" onClick={loginDB()}>
                    로그인 !
                </Button>
            </Form>
        );
    }
;

export default LoginForm;