import { Button } from "@mui/material";
import type { FC } from "react";
import { secondaryColor } from "~style/colors";

interface SecondaryButtonProps {
  text: string;
  disabled: boolean;
  type: "button" | "submit" | "reset" | undefined;
  style?: {};
  onClick?: () => void;
}
const SecondaryButton: FC<SecondaryButtonProps> = (props) => {
  return (
    <Button
      sx={{ textTransform: "none" }}
      type={props.type}
      disabled={props.disabled}
      style={{
        backgroundColor: props.disabled == true ? "#BCBDBD" : secondaryColor,
        color: "white",
        marginLeft: "15px",
        marginRight: "15px",
        boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
        borderRadius: "10px",
        padding: "16px 20px",
        fontFamily: "Circular Std",
        fontStyle: "normal",
        fontWeight: 500,
        fontSize: "18px",
        lineHeight: "23px",
        ...props.style,
      }}
      onClick={props.onClick}
    >
      {props.text}
    </Button>
  );
};

export default SecondaryButton;
