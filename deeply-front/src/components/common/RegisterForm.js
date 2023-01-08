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
        const [show, setShow] = useState(false);

        /**
         * 파일 변경시 처리
         */
        const handleChangeFile = (e) => {
            let reader = new FileReader();
            console.log(e)
            if (e.target.files[0]) {
                reader.readAsDataURL(e.target.files[0]);
                setShow(true);
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
                    <h5 style={{textAlign: `left`}}> 이메일 </h5>
                    <Form.Control type="email"
                                  placeholder="이메일을 입력해주세요."
                                  value={email}
                                  onChange={(e) => setEmail(e.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPasswordRegister">
                    <h5 style={{textAlign: `left`}}> 비밀번호 </h5>
                    <Form.Control type="password"
                                  placeholder="Password"
                                  value={password}
                                  onChange={(e) => setPassword(e.target.value)}

                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPasswordConfirmRegister">
                    <h5 style={{textAlign: `left`}}> 비밀번호 확인 </h5>
                    <Form.Control type="password"
                                  placeholder="passwordConfirm"
                                  value={passwordConfirm}
                                  onChange={(e) => setPasswordConfirm(e.target.value)}

                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicUsernameRegister">
                    <h5 style={{textAlign: `left`}}> 이름 </h5>
                    <Form.Control type="text"
                                  placeholder="이름"
                                  value={username}
                                  onChange={(e) => setUsername(e.target.value)}

                    />
                </Form.Group>


                <Form.Group className="mb-3" controlId="formBasicFileRegister">
                    <h5 style={{textAlign: `left`}}> 프로필 </h5>
                    <Form.Control type="file"
                                  placeholder="file"
                                  onChange={handleChangeFile}
                    />
                </Form.Group>

                {/**
                 * 이미지가 등록되었을 때만 화면에 보여주기 : show: Booelan 변수
                 */}
                {
                    show &&
                    <img
                        alt="sample"
                        src={fileImage}
                        style={{width: `100px`, height: `100px`, border: `2px solid black`, marginBottom: `10px`}}
                    />
                }
                <br/>

                {/*<Button variant="primary" type="submit">작성 완료</Button>{' '}*/}
                <Button variant="info" type="button"
                        onClick={registerDB(email, password, passwordConfirm, username)}>회원가입</Button>{' '}
                <Button variant="dark" type="reset">초기화</Button>{' '}
            </Form>
        );
    }
;

export default RegisterForm;
