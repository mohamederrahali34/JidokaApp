import Image from "next/image";
import type { FC } from "react";
import { StyledButtonBack } from "~style/board.style";

interface BackProps {
  onClick: () => void;
}
const Back: FC<BackProps> = (props) => {
  return (
    <div>
      <StyledButtonBack onClick={props.onClick}>
        <Image src="/left.svg" width={30} height={30} />
        <h2>Back</h2>
      </StyledButtonBack>
    </div>
  );
};

export default Back;
