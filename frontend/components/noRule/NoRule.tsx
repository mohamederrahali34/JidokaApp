import Image from "next/image";
import type { FC } from "react";
import { secondaryColor } from "~style/colors";

const NoRule: FC = () => {
  return <div style={{textAlign : "center"}}>
<Image src={"/Frame.png"} width = {"130px"} height={"130px"}/>
<div style={{ textAlign: "center", color: secondaryColor }}>There are currently no rules set to this board
</div>
  </div>;
};

export default NoRule;
