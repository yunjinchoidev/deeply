import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {FIND_PROFILE, LOGIN_USER, MY_PROFILE, REGISTER_USER} from "./types";

export function findProfile(dataToSubmit) {
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken

    let result =
        {
            age: '',
            childrenYn:'',
            email:'',
            gender:'',
            id:'',
            loc:'',
            money:'',
            phoneNumber:'',
            role:'',
            user:'',
            username:'',
        }

    const request = axios({
        method: "get",
        url: "http://"+process.env.REACT_APP_DB_HOST+"/api/profile/" + dataToSubmit,
    })
        .then((res) => {
            console.log(res)
            console.log("res:" + res.data)
            result = res.data
            return result
        })
        .catch((error) => {
            console.log(error);
            console.log("조회 실패")
        });
    // const request = axios.post("http://localhost:9000/auth/signin", dataToSubmit)
    //     .then(response => response.data)
    return {
        type: FIND_PROFILE,
        payload: request
    }
}

export function myProfile() {
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken

    let result =
        {
            age: '',
            childrenYn:'',
            email:'',
            gender:'',
            id:'',
            loc:'',
            money:'',
            phoneNumber:'',
            role:'',
            user:'',
            username:'',
        }

    const request = axios({
        method: "get",
        url: "http://"+process.env.REACT_APP_DB_HOST+"/api/profile/mine",
    })
        .then((res) => {
            console.log(res)
            console.log("res:" + res.data)
            result = res.data
            return result
        })
        .catch((error) => {
            console.log(error);
            console.log("조회 실패")
        });
    // const request = axios.post("http://localhost:9000/auth/signin", dataToSubmit)
    //     .then(response => response.data)
    return {
        type: MY_PROFILE,
        payload: request
    }
}
