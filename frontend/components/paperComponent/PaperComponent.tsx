import { Paper, PaperProps } from "@mui/material";
import type { FC } from "react";
import Draggable from "react-draggable";
const PaperComponent: FC<PaperProps> = (props) => {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
};

export default PaperComponent;
