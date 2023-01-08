import React, {useEffect, useState} from 'react';
import {Button, Form} from "react-bootstrap";
import axios from 'axios'
import {useDispatch} from "react-redux";
import {loginUser} from "../../actions/user_actions";

const LoginForm = (props) => {

    const dispatch = useDispatch()

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // const loginDB = (id, password,e) => {
    //
    //     // // e.preventDefault()
    //     // if (!email) {
    //     //     alert("email를 입력하세요.");
    //     // }
    //     // else if (!password) {
    //     //     alert("Password를 입력하세요.");
    //     // }
    //
    //
    //     return function () {
    //         axios({
    //             method: "post",
    //             url: "http://localhost:9000/auth/signin",
    //             data: {
    //                 email: email,
    //                 password: password,
    //             },
    //         })
    //             .then((res) => {
    //                 console.log(res);
    //                 const accessToken = res.data.accessToken;
    //                 const refreshToken = res.data.refreshToken;
    //                 const email = res.data.email;
    //
    //                 alert("로그인 성공했습니다. \n 디플리에 오신 걸 환영합니다.")
    //                 // document.location.href = "/";
    //             })
    //             .catch((error) => {
    //                 console.log(error);
    //             });
    //     }
    // }


    const onSubmitHandler = (e) => {
        e.preventDefault()
        console.log(email)
        console.log(password)

        let body = {
            email: email,
            password: password
        }
        dispatch(loginUser(body))

    //     axios({
    //         method: "post",
    //         url: "http://localhost:9000/auth/signin",
    //         data: {
    //             email: email,
    //             password: password,
    //         },
    //     })
    //         .then((res) => {
    //             console.log(res);
    //             const accessToken = res.data.accessToken;
    //             const refreshToken = res.data.refreshToken;
    //             const email = res.data.email;
    //             alert("accessToken:"+accessToken+"\n refreshToken"+refreshToken)
    //             alert("로그인 성공했습니다. \n 디플리에 오신 걸 환영합니다.")
    //             // document.location.href = "/";
    //         })
    //         .catch((error) => {
    //             console.log(error);
    //             alert("회원 정보가 정확하지 않습니다.")
    //         });
    //
    }

        useEffect(() => {
            console.log("LoginPage render ... ");
        })

        return (
            <Form onSubmit={onSubmitHandler}>

                <Form.Group className="mb-3" controlId="formBasicEmailLogin">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email"
                                  placeholder="Enter email"
                                  value={email}
                                  onChange={(e) => setEmail(e.target.value)}
                    />
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPasswordLogin">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password"
                                  placeholder="Password"
                                  value={password}
                                  onChange={(e) => setPassword(e.target.value)}

                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicCheckboxLogin">
                    <Form.Check type="checkbox" label="Check me out"/>
                </Form.Group>

                <Button variant="primary" type="submit">
                    로그인
                </Button>
                <Button variant="dark" type="reset">
                    초기화
                </Button>
                {/*<Button variant="info" type="button" onClick={loginDB(email, password)}>*/}
                {/*    로그인 !*/}
                {/*</Button>*/}

            </Form>
        );
    }
;

export default LoginForm;
