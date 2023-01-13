/* eslint-disable no-param-reassign */
import type { RootState } from "~store/types";

import type { PayloadAction } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import { StarRate } from "@mui/icons-material";

interface User {
  id: string;
  token?: string;
  key?: string;
}
export type UserState = {
  user: User;
  selectedBoard: {
    id : string;
    name : string;
  } ;
};

const initialState: UserState = {
  user: { id: "" },
  selectedBoard: {
    id : "",
    name : ""
  }
};

export const userSlice = createSlice({
  name: "user",
  initialState,

  reducers: {
    login: (state, action: PayloadAction<User>) => {
      return {...state ,user :{ ...action.payload } }
    },
    logout: (state) => {
      return {user :{ id: "", token: "", key: "" },selectedBoard : {
        id : "",name : ""
      }}
    },
    saveBoardId : (state,action: PayloadAction<{boardId : string,boardName : string }>) => {
      console.log({action});
      
      return {

        ...state,selectedBoard : {
          id : action.payload.boardId ,
          name : action.payload.boardName
        }
      }
    }
  },
});
const getUserToken = (state:UserState) =>{
  return state.user.token ;
}
const getUserKey = (state : UserState) =>{
  return state.user.key ;
}
const getUserId = (state:UserState) =>{
  return state.user.id ;
}
const getSelectedBoard = (state:UserState) => {
  return state.selectedBoard ;
}
export {getUserToken,getUserKey,getUserId,getSelectedBoard}
export const { login, logout,saveBoardId } = userSlice.actions;
export const getUser = (state: UserState): User => state.user;
export const { reducer: userReducer } = userSlice;
