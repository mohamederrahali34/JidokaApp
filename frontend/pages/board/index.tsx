import { NextSeo } from "next-seo";
import { useRouter } from "next/router";
import type { NextPage } from "~types/next";
import { LayoutType } from "../../layouts/config";
import { useState } from "react";

import { StyledButtonBackContainer, StyledButtonNewRule, StyledContainer } from "~style/board.style";
import AddIcon from "@mui/icons-material/Add";
import NewRule from "~components/newRule";
import { useGetBoardInfo } from "~hooks/hooks";
import GuidePageInfo from "~components/guidePageInfo";
import Rules from "~components/rules";
import { getSelectedBoard } from "~store/user/userSlice";
import { getUserState } from "~store/configure";
import Back from "~components/back";
const BoardPage: NextPage = () => {
  const router = useRouter();
  const [step, setStep] = useState(0);
  const [displayNewRuleButton, setDisplayNewRuleButton] = useState(true);
  //0 : No rules 1: create new rule 2: validate (signal alert)
  const board = getSelectedBoard(getUserState());
  const { boardInfo } = useGetBoardInfo(step);
  const handleChangeStep = (step) => {
    switch (step) {
      case 0: {
        //retour à la liste des boards
        setDisplayNewRuleButton(true);
        router.push("/boards/list");
        break;
      }
      case 1: {
        setStep(0);
        setDisplayNewRuleButton(true);
        break;
      }
      case 2: {
        setStep(1);
        break;
      }
      default: {
        router.push("/boards/list");
        break;
      }
    }
  };
  const loadListOfRules = () => {
    return <Rules />;
  };
  const displayListOfRules = (step) => {
    return step == 0 ? loadListOfRules() : step == 1 ? <NewRule idBoard={board.id} /> : <div>Validate</div>;
  };
  return (
    <StyledContainer>
      <NextSeo title={"Board"} />
      <StyledButtonBackContainer>
        <Back onClick={() => handleChangeStep(step)} />
      </StyledButtonBackContainer>
      <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-between" }}>
        <GuidePageInfo info={step == 0 ? getSelectedBoard(getUserState()).name : step == 1 ? "Add New Rule" : ""} />
        {displayNewRuleButton && (
          <StyledButtonNewRule
            onClick={() => {
              setDisplayNewRuleButton(false);
              setStep(1);
            }}
          >
            <div> Add new rule </div>
            <AddIcon />
          </StyledButtonNewRule>
        )}
      </div>
      <div>{displayListOfRules(step)}</div>
    </StyledContainer>
  );
};
BoardPage.layout = LayoutType.Logout;
export default BoardPage;
