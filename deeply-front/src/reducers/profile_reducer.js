import {
    FIND_PROFILE, MY_PROFILE
} from "../actions/types";

export default function (state = {}, action){
    switch (action.type){
        case FIND_PROFILE:
            return { ...state, fildProfileSuccess: action.payload}
            break;
        case MY_PROFILE:
            return { ...state, myProfileSuccess: action.payload}

        default:
            return state;
    }

}