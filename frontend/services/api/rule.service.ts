import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "~env";
import { RuleType } from "~services/trello/board.service";

interface newRuleRequestType {
    id? : string;
    idBoard : string ;
    type : string ;
    params : {},
    message : string ;
    workspace : string;
    channel : string;
}
interface updateRuleRequestType {
  id : string;
  idBoard : string;
  type : string ;
  params : {},
  message : string ;
  workspace : string;
  channel : string;
  active? : boolean;
}
interface toggleActivateRuleRequestType{
  id : string
}
interface toggleActivateRuleResponseType{
  id : string;
  idBoard : string;
  type : string ;
  params : {},
  message : string ;
  workspace : string;
  channel : string;
  active? : boolean;
}
interface deleteRuleRequestType {
  id : string
}
interface newRuleResponseType {
    id  : string ; 
    name : string ;
}
interface updateRuleResponseType {
  id  : string ; 
}

interface deleteRuleResponseType {
  id  : string ; 
}
interface getRuleByIdRequest {
  id : string;
  type? : string;
}

export const ruleApi = createApi({
  reducerPath: "rule",
  baseQuery: fetchBaseQuery({ baseUrl: API_URL }),
  endpoints: (builder) => ({
    newRule: builder.mutation<newRuleResponseType, newRuleRequestType>({
      query: (body) => ({
        url: `/rules/new`,
        method: "POST",
        body ,
      }),
    }),
    updateRule : builder.mutation<updateRuleResponseType, updateRuleRequestType>({
      query: (body) => ({
        url: `/rules/update`,
        method: "PUT",
        body ,
    })
  }),
  toggleActivateRule : builder.mutation<toggleActivateRuleResponseType, toggleActivateRuleRequestType>({
    query: (body) => ({
      url: `/rules/activate?id=${body.id}`,
      method: "PUT",
      body ,
  })
})
  ,
  deleteRule : builder.mutation<deleteRuleResponseType,deleteRuleRequestType>({
    query: (body) => ({
      url: `/rules/delete`, 
      method: "DELETE",
      headers : {
        AccessControlRequestMethod: "DELETE"
      },
      body ,
  })
  }),
  getRuleById : builder.mutation<RuleType,getRuleByIdRequest>({
    query: (body) => ({
      url: `/rules/rule?type=`+body.type+`&id=`+body.id, 
      method: "GET",
  })
  })
})
});
export default ruleApi;
