import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {BOARD_LIST} from "./types";

export function boardListAction(dataToSubmit) {
    let contents =
        [
            {
                author: "",
                contentL: '',
                createdDate: '',
                fileId: '',
                fileResponseDto: '',
                id: '',
                modifiedDate: "",
                title: "",
            }
        ]
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    // console.log("accessToken : " + accessToken)
    // console.log(dataToSubmit)
    axios.defaults.headers.common['Authorization'] = accessToken
    const request = axios({
        method: "post",
        url: "http://"+process.env.REACT_APP_DB_HOST+"/api/board/list",
        data: dataToSubmit,
    })
        .then((res) => {
            console.log("게시글 리스트 조회 성공")
            contents = res.data.content
            // console.log("contents"+contents)
            return contents
        })
        .catch((error) => {
            console.log(error);
            console.log("조회 실패")
        });
    return {
        type: BOARD_LIST,
        payload: request
    }
}
