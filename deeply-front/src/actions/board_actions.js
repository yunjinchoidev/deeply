import axios from "axios";
import data from "bootstrap/js/src/dom/data";
import {BOARD_GET, BOARD_LIST} from "./types";


/**
 * 게시물 리스트 조회
 */
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
    axios.defaults.headers.common['Authorization'] = accessToken
    const request = axios({
        method: "post",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/api/board/list",
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


/**
 * 게시물 조회
 */
export function boardGetAction(dataToSubmit) {

      const Content=  {
            author: "",
            contentL: '',
            createdDate: '',
            fileId: '',
            fileResponseDto: '',
            id: '',
            modifiedDate: "",
            title: "",
        }
    let accessToken = "Bearer " + localStorage.getItem("accessToken");
    axios.defaults.headers.common['Authorization'] = accessToken
    const id = dataToSubmit.id
    const request = axios({
        method: "get",
        url: "http://" + process.env.REACT_APP_DB_HOST + "/api/board/" + id,
    })
        .then((res) => {
            console.log("게시글 조회 성공")
            return res.data
        })
        .catch((error) => {
            console.log(error);
            console.log("조회 실패")
        });
    return {
        type: BOARD_GET,
        payload: request
    }
}