import { counterReducer } from "./counter/slice";

import { configureStore } from "@reduxjs/toolkit";
import { userReducer } from "./user/userSlice";

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    user: userReducer,
  },
});
export const  getUserState = ()=>{
  return store.getState().user ;
} 
