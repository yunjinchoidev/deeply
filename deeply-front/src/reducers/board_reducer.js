import {
    BOARD_GET,
    BOARD_LIST
} from "../actions/types";

export default function (state = {}, action){
    switch (action.type){
        case BOARD_LIST:
            return { ...state, boardListSuccess: action.payload}
            break;
        case BOARD_GET:
            return { ...state, boardGetSuccess: action.payload}
            break;

        default:
            return state;
    }

}