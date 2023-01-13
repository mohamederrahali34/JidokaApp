import type { NextPage } from "~types/next";
import trelloApi from "~services/trello/trello.service";
import { NextSeo } from "next-seo";
import { useEffect, useState } from "react";
import { getUser } from "~store/user/userSlice";
import { getUserState } from "~store/configure";
import ListBoards from "~components/listBoards";
import { Board } from "~components/listBoards/ListBoards";
import { LayoutType } from "~layouts/config";
import { colorGray90 } from "~style/colors";
import { useGetBoards } from "~hooks/hooks";
const ListPage: NextPage = () => {
  const [userId, setUserId] = useState("");
  const [boards, setBoards] = useState<Board[]>([]);
  const [token, setToken] = useState("");

  //TODO : what is the state of the store when refreshing page ?
  useGetBoards(setBoards);
  return (
    <div
      style={{
        //backgroundImage: "url('jidoka.png')",
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        backgroundColor: colorGray90,
      }}
    >
      <NextSeo title="List des boards" />
      <ListBoards boards={boards} />
    </div>
  );
};
ListPage.layout = LayoutType.Logout;

export default ListPage;
