import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

import { API_URL } from "~env";

interface newUserRequestType{
token : string;
}
interface newUserResponseType{
    email : string;
    token: string;
    name : string;
    id:string;
}
export const userApi = createApi({
    reducerPath: "user",
    baseQuery: fetchBaseQuery({ baseUrl: API_URL }),
    endpoints: (builder) => ({
      newUser: builder.mutation<newUserResponseType, newUserRequestType>({
        query: (body) => ({
          url: `/users/new`,
          method: "POST",
          body 
        }),
      })
     
    }),
  });
  export default userApi;