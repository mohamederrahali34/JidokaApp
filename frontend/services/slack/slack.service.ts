import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { API_URL } from "~env";

export interface ChannelType{
    name : string;
} 
export type AllChannelsResponse = ChannelType[];
export interface GetAllChannelRequest  {
}
export interface GetAllChannelResponse {

}
export const slackApi = createApi({
    reducerPath: "slack",
  baseQuery: fetchBaseQuery({ baseUrl: API_URL }),
  endpoints: (builder) => ({
    getAllChannels: builder.mutation<AllChannelsResponse,GetAllChannelResponse>({
      query: () => ({
        url: `/slack/channels`,
        method: "GET"
      }),
    }),})
})
export default slackApi ;