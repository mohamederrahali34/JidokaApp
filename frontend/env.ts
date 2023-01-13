import { APPLICATION_KEY } from "~application.info";

export const API_URL = "http://localhost:8080/api";
export const NGROK_BACKEND_URL = "https://7f05-102-52-150-80.ngrok.io" ;
export const REDIRECT_URL = "http://localhost:3000/redirect";
export const TRELLO_AUTHORIZE_LINK =`https://trello.com/1/authorize?expiration=never&name=JidokaBot&scope=read&response_type=fragment&key=${APPLICATION_KEY}&return_url=${REDIRECT_URL}`
