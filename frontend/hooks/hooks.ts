import {  useEffect, useState } from "react";
import boardApi, { RuleType } from "~services/trello/board.service";
import trelloApi, { ListType } from "~services/trello/trello.service";
import { getUserState } from "~store/configure";
import { getSelectedBoard} from "~store/user/userSlice";
import { Board } from "~components/listBoards/ListBoards";

export const useGetRulesOfActualBoard = (reloadRules) => {
  const [rules, setRules] = useState<RuleType[]>([]);
  const [getRulesOfBoard] = boardApi.useGetRulesMutation();
    useEffect(() => {
        const idOfBoard = getSelectedBoard(getUserState()).id;
        getRulesOfBoard({ id: idOfBoard })
          .unwrap()
          .then((data) => {
            setRules(data);
          })
          .catch((err) => console.log("error when loading rules"));
      },[reloadRules]);
      return {rules}
}
export const useGetBoardInfo = (step) => {
  const [boardInfo,setBoardInfo] = useState<Board>();
  const [getBoardInfo] = trelloApi.useGetBoardInfoMutation();
    useEffect(() => {
      const token = localStorage.getItem("trello_token") || ""
      if(!token){
        console.log("token invalide !");
      }
      else {
        getBoardInfo({
          idBoard: getSelectedBoard(getUserState()).id,
          auth: { token: token ,id : localStorage.getItem("userId") || "" }
        })
          .unwrap()
          .then((res) => {
            setBoardInfo(res);
          })
          .catch((err) => console.log("impossible de charger le board"));
      }
     
      }, [step]);
      return {boardInfo}
}

export const useGetBoards = (setBoards)=>{
  const [getBoards] = trelloApi.useGetBoardsMutation();

    useEffect(() => {
      const userIdfromLocalStorage = localStorage.getItem("userId") || "";
      const tokenFromLocalStorage = localStorage.getItem("trello_token") || "";
        getBoards({ id:userIdfromLocalStorage })
          .unwrap()
          .then((res) => {
            setBoards(res);
          })
          .catch((err) => console.log("impossible de charger les boards"));
      }, []);
} 
export const useGetBoardColumns = (boardId,initializeForm)=>{
  const [columns, setColumns] = useState<ListType[]>([]);
  const [getListsOfBoard] = trelloApi.useGetListsOfBoardMutation();
  useEffect(() => {
    if (!boardId) {
      console.log("probleme when loading columns: board is undefined");
    }
    getListsOfBoard({
      auth: {
        id: localStorage.getItem("userId") || "",
      },
      boardId: boardId,
    })
      .unwrap()
      .then((res) => {
        console.log({ columns: res });
        setColumns(res);
        initializeForm()
      })
      .catch((_err) => {
        console.log("error when loading columns");
      });
  }, [boardId]);
  return columns
}
