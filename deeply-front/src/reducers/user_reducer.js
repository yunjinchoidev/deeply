import {
    AUTH_USER,
    LOGIN_USER, LOGOUT_USER, REGISTER_USER
} from "../actions/types";

export default function (state = {}, action){

    switch (action.type){
        case LOGIN_USER:
            return { ...state, loginSuccess: action.payload}
            break;
        case REGISTER_USER:
            return { ...state, loginSuccess: action.payload}
            break;
        case LOGOUT_USER:
            return { ...state, logoutSuccess: action.payload}
            break;
        case AUTH_USER:
            return { ...state, userData: action.payload}
            break;
        default:
            return state;
    }

}