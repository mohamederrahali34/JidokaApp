import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { Board } from "~components/listBoards/ListBoards";
import { API_URL } from "~env";
export interface IAuth {
 email?: string;
 id? : string;
 token? :string;
}
interface IGetBoardInfoRequest {
  auth: IAuth;
  idBoard: string;
}
export interface IAuthorizationResponse {
  id: string;
  fullName : string ; 
  email: string;
}

interface IGetListsOfBoardRequest {
  auth : IAuth,
  boardId : string
  
}
interface IGetListsOfBoardResponse {
  
}
export interface ListType{
id : string,
name : string
}
export const trelloLogin = () => {};
export const trelloApi = createApi({
  reducerPath: "trello",
  baseQuery: fetchBaseQuery({ baseUrl: API_URL+"/trello" }),
  endpoints: (builder) => ({
    myInformation: builder.mutation<IAuthorizationResponse, IAuth>({
      query: (auth) => ({
        url: `/member/me?userId=${auth.id}`,
        method: "GET",
      }),
    }),
    getListsOfBoard: builder.mutation<ListType[], IGetListsOfBoardRequest>({
      query: (params) => ({
        url: `boards/${params.boardId}/lists?userId=${params.auth.id}`,
        method: "GET",
      }),
    }),
    getBoards: builder.mutation<Board[], IAuth>({
      query: (auth) => ({
        url: `members/${auth.id}/boards?userId=${auth.id}`,
        method: "GET",
      }),
    }),
    getBoardInfo: builder.mutation<Board, IGetBoardInfoRequest>({
      query: (req) => ({
        url: `board/${req.idBoard}?userId=${req.auth.id}`,
        method: "GET",
      }),
    }),
  }),

});
export default trelloApi;
