import { FC } from "react";
import { StyledBoardInfo } from "./GuidePageInfostyle";
import { colorGray90 } from "../../style/colors";
import Title from "~components/title";

interface GuideInfoProps {
  info: string;
}
const GuidePageInfo: FC<GuideInfoProps> = (props) => {
  return (
    <div>
      <StyledBoardInfo backgroundColor={colorGray90}>
        <Title text={props.info || ""} />
      </StyledBoardInfo>
    </div>
  );
};

export default GuidePageInfo;
