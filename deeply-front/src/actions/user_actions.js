import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {LOGIN_USER, REGISTER_USER} from "./types";

/**
 * 로그인
 */
export function loginUser(dataToSubmit){

    const request = axios({
            method: "post",
            url: "http://localhost:9000/auth/signin",
            data: dataToSubmit,
        })
            .then((res) => {
                console.log(res);
                const accessToken = res.data.accessToken;
                const refreshToken = res.data.refreshToken;
                const email = res.data.email;
                localStorage.setItem("accessToken",accessToken)
                console.log(localStorage.getItem("accessToken"))
                alert("accessToken:"+accessToken+"\n refreshToken"+refreshToken)
                alert("로그인 성공했습니다. \n 디플리에 오신 걸 환영합니다.")
                // document.location.href = "/";
            })
            .catch((error) => {
                console.log(error);
                alert("회원 정보가 정확하지 않습니다.")
            });
    return {
        type: LOGIN_USER,
        payload: request
    }
}

/**
 *  회원가입
 */
export function registerUser(dataToSubmit){

    const request = axios({
            method: "post",
            url: "http://localhost:9000/auth/signup",
            data: dataToSubmit,
        })
            .then((res) => {
                console.log(res);
                const id = res.data.id;
                console.log("id:"+id)
                console.log("회원가입에 성공했습니다.. \n 디플리에 오신 걸 환영합니다.")
            })
            .catch((error) => {
                console.log(error);
            });

    return {
        type: REGISTER_USER,
        payload: request
    }
}
