import { useRouter } from "next/router";
import { FC } from "react";
import CardContainer from "../card/Card";
import { containerlstStyle, titleStyle, lstStyle } from "./ListBoards.style";
import { CSSProperties } from "react";
import boardApi from "~services/trello/board.service";
import Title from "~components/title";
import { useDispatch } from "react-redux";
import { getUserToken, saveBoardId } from "~store/user/userSlice";
import { getUserState } from "~store/configure";
import { NGROK_BACKEND_URL } from "~env";
import { APPLICATION_KEY } from "~application.info";

export interface Board {
  id: string;
  name: string;
  prefs: { backgroundImage: string };
}
interface IListBoardsProps {
  boards: Board[];
}
const ListBoards: FC<IListBoardsProps> = (props) => {
  const router = useRouter();
  const [saveBoard] = boardApi.useNewBoardMutation();
  const dispatch = useDispatch();
  const handleSaveBoard = (board: Board) => {
    dispatch(saveBoardId({ boardId: board.id, boardName: board.name }));
    saveBoard({
      id: board.id,
      userId: localStorage.getItem("userId") || "",
    })
      .then((_res) => {
        router.push(`/board`);
      })
      .catch((_err) => {
        console.log("impossible de sauvegarder le board ");
      });
  };

  return (
    <div style={containerlstStyle}>
      <Title text={"My Board(s)"} />
      <h1 style={titleStyle}>Select a project</h1>
      <ul style={lstStyle as CSSProperties}>
        {props.boards &&
          props.boards.map((b) => {
            return (
              <CardContainer
                onClick={() => handleSaveBoard(b)}
                key={b.id}
                image={{
                  src: b.prefs.backgroundImage,
                  alt: b.name,
                }}
                title={b.name}
                text={""}
              />
            );
          })}
      </ul>
    </div>
  );
};

export default ListBoards;
