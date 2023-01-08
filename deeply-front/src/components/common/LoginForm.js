import React, {useEffect, useState} from 'react';
import {Button, Form} from "react-bootstrap";
import axios from 'axios'
import {useDispatch} from "react-redux";
import {loginUser} from "../../actions/user_actions";

const LoginForm = (props) => {

        const dispatch = useDispatch()
        const [email, setEmail] = useState("");
        const [password, setPassword] = useState("");
        const onSubmitHandler = (e) => {
            e.preventDefault()
            console.log(email)
            console.log(password)

            let body = {
                email: email,
                password: password
            }
            dispatch(loginUser(body))
        }

        useEffect(() => {
            console.log("LoginPage render ... ");
        })

        return (
            <Form onSubmit={onSubmitHandler}>
                <h2>gg</h2>
                <h2>{process.env.REACT_APP_DB_HOST}d</h2>
                <Form.Group className="mb-3" controlId="formBasicEmailLogin">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control type="email"
                                  placeholder="Enter email"
                                  value={email}
                                  onChange={(e) => setEmail(e.target.value)}
                    />
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
                    <Form.Check type="checkbox" label="자동 로그인"/>
                </Form.Group>

                {/**버튼*/}
                <Button variant="dark" type="submit">{' '}로그인</Button>{' '}
                <Button variant="dark" type="reset">초기화</Button>{' '}
                <Button variant="dark" type="reset">이메일찾기</Button>{' '}
                <Button variant="dark" type="reset">비밀번호 찾기</Button>{' '}
            </Form>
        );
    }
;

export default LoginForm;
