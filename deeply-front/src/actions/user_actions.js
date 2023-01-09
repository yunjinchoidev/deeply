import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {AUTH_USER, LOGIN_USER, LOGOUT_USER, REGISTER_USER} from "./types";


/**
 * 로그인
 */
export function loginUser(dataToSubmit) {
    const request = axios({
        method: "post",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/auth/signin",
        data: dataToSubmit,
    })
        .then((res) => {
            console.log(res.data);
            const accessToken = res.data.accessToken;
            const isAdmin = res.data.isAdmin;
            if (isAdmin == true){
                localStorage.removeItem("authority")
                localStorage.setItem("authority", 2) // 관리자 = 2
            }else{
                localStorage.removeItem("authority")
                localStorage.setItem("authority", 1) // 일반 유저 = 1
            }
            localStorage.setItem("accessToken", accessToken)
            alert("로그인 성공했습니다. \n 디플리에 오신 걸 환영합니다.")
            document.location.href = "/"; // 홈페이지로 강제 이동
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
export function registerUser(dataToSubmit) {

    const request = axios({
        method: "post",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/auth/signup",
        data: dataToSubmit,
    })
        .then((res) => {
            console.log(res);
            const id = res.data.id;
            console.log("id:" + id)
            console.log("회원가입에 성공했습니다.. \n 디플리에 오신 걸 환영합니다.")
            document.location.href = "/"; // 홈페이지로 강제 이동
        })
        .catch((error) => {
            console.log(error);
        });

    return {
        type: REGISTER_USER,
        payload: request
    }
}


/**
 * 로그아웃
 */
export function logoutUser() {

    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken

    const request = axios({
        method: "post",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/auth/logout",
    })
        .then((res) => {
            console.log(res);
            console.log(localStorage.getItem("accessToken"));
            console.log("로그아웃성공");
            localStorage.removeItem('accessToken');
            localStorage.removeItem('authority');
            // alert("로그아웃 성공했습니다. 안녕히 가십시오.")
            console.log(localStorage.getItem("accessToken"));
            document.location.href = "/"; // 홈페이지로 강제 이동

        })
        .catch((error) => {
            console.log(error);
            alert("로그아웃 실패했습니다.")
        });
    return {
        type: LOGOUT_USER,
        payload: request
    }
}


/**
 * 권한체크
 */
export function authUser(dataToSubmit) {

    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken
    console.log(localStorage.getItem("accessToken"))

    const request = axios({
        method: "get",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/auth/check",
        data: dataToSubmit,

    })
        .then((res) => {
            console.log(res);
            console.log("인증성공")
            // document.location.href = "/"; // 홈페이지로 강제 이동
        })
        .catch((error) => {
            console.log(error);
            console.log("인증 실패했습니다.")
        });

    return {
        type: AUTH_USER,
        payload: request
    }


}
