import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { env } from "process";
import { Board } from "~components/listBoards/ListBoards";
import { API_URL } from "~env";

export interface WebookType {
  callback : string ;
  description? : string ;
  idModel : string ;
  active? : boolean ;
  key : string ;
  token : string ;
}
interface newBoardRequestType {
    id : string 
    userId : string;
}
interface newBoardResponseType {
    id  : string ; 
}
interface getRuleRequestType {
id : string 
}
export interface RuleType {
  duration: number;
  unit: any;
  stockRuleColumnName: string;
  timeRuleColumnName : string;
  nbCards : number;
  owner : string;
  id : string ;
  name : string ;
  active : boolean,
  type : string ;
  message : string ;
  channel : string ;
  workspace : string ;
  board : {
    id : string;
  }

}
export const boardApi = createApi({
  reducerPath: "board",
  baseQuery: fetchBaseQuery({ baseUrl: API_URL }),
  endpoints: (builder) => ({
    newBoard: builder.mutation<newBoardResponseType, newBoardRequestType>({
      query: (body) => ({
        url: `/boards/new?userId=${body.userId}`,
        method: "POST",
        body 
      }),
    }),
    getRules : builder.mutation<RuleType[]  ,getRuleRequestType>({
      query: (body) => ({
        url: `/boards/rules`,
        method: "POST",
        body
      }),
    })
   
  }),
});
export default boardApi;
