import {
    DISPLAY_FILE,
    LOGIN_USER, REGISTER_USER
} from "../actions/types";

export default function (state = {}, action){

    switch (action.type){
        case DISPLAY_FILE:
            return { ...state, displayFIleSuccess: action.payload}
            break;
        default:
            return state;
    }

}