import {combineReducers} from "redux";

import user from "./user_reducer";
import board from "./board_reducer"

const rootReducer = combineReducers({
    user,
    board
})

export default rootReducer