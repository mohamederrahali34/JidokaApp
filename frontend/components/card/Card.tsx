import { FC } from "react";
import { StyledBoard, StyledTitle } from "./Card.style";
interface CardContainerProps {
  image: {
    src: string;
    alt: string;
  };
  title: string;
  text: string;
  onClick: () => void;
}
const CardContainer: FC<CardContainerProps> = (props) => {
  return (
    <StyledBoard image={props.image.src} onClick={props.onClick}>
      <StyledTitle>{props.title}</StyledTitle>
    </StyledBoard>
  );
};

export default CardContainer;
