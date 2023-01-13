import { FC, useState } from "react";
import Button from "@mui/material/Button";
import { primaryColor } from "~style/colors";
import { PrimaryButtonTextStyle } from "./PrimaryButton.style";
interface PrimaryButtonProps {
  actif: boolean;
  variant: "text" | "outlined" | "contained" | undefined;
  text: string;
  onClick: () => void;
  onMouseLeave?: () => void;
  onMouserEnter?: () => void;
  hoverStyle?: {};
  style?: {};
}

const PrimaryButton: FC<PrimaryButtonProps> = (props) => {
  const [isHover, setHover] = useState(false);
  const defaultstyle = {
    backgroundColor: props.actif ? primaryColor : "white",
    color: props.actif ? "white" : primaryColor,
    border: `1px solid ${primaryColor}`,
    boxSizing: "border-box" as "border-box",
    boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
    borderRadius: "10px",
    display: "flex",
    flexDirection: "row" as "row",
    justifyContent: "start",
    paddingLeft: "15px",
    height: "60px",
    ...props.style,
  };
  const hoverStyle = {
    backgroundColor: primaryColor,
    color: "white",
  };
  const hoverSt = isHover ? hoverStyle : {};
  return (
    <Button
      sx={{ textTransform: "none" }}
      variant={props.variant}
      style={{ ...defaultstyle, ...hoverSt }}
      onClick={props.onClick}
      onMouseLeave={() => setHover(false)}
      onMouseEnter={() => setHover(true)}
    >
      <PrimaryButtonTextStyle>{props.children}</PrimaryButtonTextStyle>
    </Button>
  );
};

export default PrimaryButton;
