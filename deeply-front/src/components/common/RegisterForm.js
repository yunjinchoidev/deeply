import React, {useEffect, useState} from 'react';
import {Button, Form} from "react-bootstrap";
import axios from 'axios'
import {useDispatch} from "react-redux";
import {registerUser} from "../../actions/user_actions";


/**
 * 회원가입
 */
const RegisterForm = (props) => {
        const [email, setEmail] = useState("");
        const [password, setPassword] = useState("");
        const [passwordConfirm, setPasswordConfirm] = useState("");
        const [file, setFile] = useState("");
        const [username, setUsername] = useState("");

        const dispatch = useDispatch()

        /**
         * 등록
         */
        const registerDB = (email, password, passwordConfirm) => {

            let body = {
                email: email,
                password: password
            }

            return function () {
                dispatch(registerUser(body))
            }
        }


        useEffect(() => {
            console.log("LoginPage render ... ");
        })

        const [userImage, setUserImage] = useState("");
        const [fileImage, setFileImage] = useState("");

        const handleChangeFile = (e) => {
            let reader = new FileReader();
            console.log(e)
            if (e.target.files[0]) {
                reader.readAsDataURL(e.target.files[0]);
            }
            reader.onloadend = () => {
                const resultImage = reader.result;
                console.log(resultImage)
                setFileImage(URL.createObjectURL(e.target.files[0]));
                setUserImage(resultImage)
            };
        }


        return (
            <Form>
                <Form.Group className="mb-3" controlId="formBasicEmailRegister">
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

                <Form.Group className="mb-3" controlId="formBasicPasswordRegister">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password"
                                  placeholder="Password"
                                  value={password}
                                  onChange={(e) => setPassword(e.target.value)}

                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPasswordConfirmRegister">
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control type="password"
                                  placeholder="passwordConfirm"
                                  value={passwordConfirm}
                                  onChange={(e) => setPasswordConfirm(e.target.value)}

                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicUsernameRegister">
                    <Form.Label>이름</Form.Label>
                    <Form.Control type="text"
                                  placeholder="이름"
                                  value={username}
                                  onChange={(e) => setUsername(e.target.value)}

                    />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicFileRegister">
                    <Form.Label>파일</Form.Label>
                    <Form.Control type="file"
                                  placeholder="file"
                                  onChange={handleChangeFile}
                    />
                </Form.Group>

                <img
                    alt="sample"
                    src={fileImage}
                    style={{margin: "auto"}}
                />

                <Button variant="primary" type="submit">작성 완료</Button>{' '}
                <Button variant="dark" type="reset">초기화</Button>{' '}
                <Button variant="info" type="button"
                        onClick={registerDB(email, password, passwordConfirm, username)}>회원가입</Button>
            </Form>
        );
    }
;

export default RegisterForm;
