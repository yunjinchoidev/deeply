import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {DISPLAY_FILE, FIND_PROFILE, LOGIN_USER, MY_PROFILE, REGISTER_USER} from "./types";

export function displayFile(dataToSubmit) {
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken


    const request = axios({
        method: "get",
        url: "http://"+process.env.REACT_APP_DB_HOST+"/display/" + dataToSubmit,
    })
        .then((res) => {
            console.log("파일 정보")
            console.log(res)
            console.log("res:" + res.data)
            return res.data
        })
        .catch((error) => {
            console.log(error);
            console.log("조회 실패")
        });
    return {
        type: DISPLAY_FILE,
        payload: request
    }
}
