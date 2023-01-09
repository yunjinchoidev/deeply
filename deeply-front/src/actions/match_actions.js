import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {BOARD_LIST, MATCH_LIST} from "./types";

export function matchListAction(dataToSubmit) {
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken
    const request = axios({
        method: "post",
        url: "http://"+process.env.REACT_APP_DB_HOST+"/api/match/list",
        data: dataToSubmit,
    })
        .then((res) => {
            console.log("게시글 리스트 조회 성공")
            return request
        })
        .catch((error) => {
            console.log(error);
        });
    return {
        type: MATCH_LIST,
        payload: request
    }
}
