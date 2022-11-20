import {
    BOARD_LIST, MATCH_LIST
} from "../actions/types";

export default function (state = {}, action){
    switch (action.type){
        case MATCH_LIST:
            return { ...state, matchListSuccess: action.payload}
            break;

        default:
            return state;
    }

}