import {combineReducers} from "redux";

import user from "./user_reducer";
import board from "./board_reducer"
import file from "./file_reducer"
import profile from "./profile_reducer"

const rootReducer = combineReducers({
    user,
    board,
    profile,
    file
})

export default rootReducer