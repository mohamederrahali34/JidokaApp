import type { FC } from "react";
import { StyledTitle } from "./Title.style";
import DoubleArrowIcon from "@mui/icons-material/DoubleArrow";
interface TitleProps {
  text: string;
}
const Title: FC<TitleProps> = (props) => {
  return (
    <StyledTitle>
      <div>
        <img src="/doubleRight.svg" />
      </div>
      <h1>{props.text}</h1>
    </StyledTitle>
  );
};
export default Title;
