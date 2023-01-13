import { FC, useEffect, useState } from "react";
import PrimaryButton from "~components/primaryButton";
import TimeRule from "~components/timeRule";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import { StyledHeadNewRule, StyledNewRuleConatiner, StyledNewRuleItemContainer } from "./NewRule.style";
import StorageIcon from "@mui/icons-material/Storage";
import CodeIcon from "@mui/icons-material/Code";
import StockRule from "~components/stockRule";
interface NewRuleProps {
  idBoard: string;
}
const NewRule: FC<NewRuleProps> = (props) => {
  const [displayRuleList, setDisplayRuleList] = useState(true);
  const [catigorieActivated, setCategorieActivated] = useState({
    time: false,
    stock: false,
    mouvement: false,
  });
  useEffect(() => {
    setCategorieActivated({
      time: false,
      stock: false,
      mouvement: false,
    });
  }, []);
  const onSlectCategory = (key) => {
    const initialState = {
      time: false,
      stock: false,
      mouvement: false,
    };
    setCategorieActivated({ ...initialState, [key]: true });
    setDisplayRuleList(true);
  };

  return (
    <StyledNewRuleConatiner>
      <StyledHeadNewRule>
        <PrimaryButton
          text="Time"
          actif={catigorieActivated.time}
          variant={"text"}
          onClick={() => onSlectCategory("time")}
        >
          {" "}
          <p>
            <AccessTimeIcon /> Time
          </p>
        </PrimaryButton>
        <PrimaryButton
          text="Stock"
          actif={catigorieActivated.stock}
          variant={"outlined"}
          onClick={() => onSlectCategory("stock")}
        >
          <p>
            <StorageIcon /> Stock
          </p>
        </PrimaryButton>
        <PrimaryButton
          text="Mouvement"
          actif={catigorieActivated.mouvement}
          variant={"outlined"}
          onClick={() => onSlectCategory("mouvement")}
        >
          <p>
            <CodeIcon /> Mouvement
          </p>
        </PrimaryButton>
      </StyledHeadNewRule>
      {displayRuleList && (
        <StyledNewRuleItemContainer>
          {catigorieActivated.time ? (
            <TimeRule idBoard={props.idBoard} returnToInitialStateCallback={() => setDisplayRuleList(false)} />
          ) : catigorieActivated.stock ? (
            <div>
              <StockRule idBoard={props.idBoard} returnToInitialStateCallback={() => setDisplayRuleList(false)} />
            </div>
          ) : catigorieActivated.mouvement ? (
            <div>Mouvement</div>
          ) : (
            <div></div>
          )}
        </StyledNewRuleItemContainer>
      )}
    </StyledNewRuleConatiner>
  );
};

export default NewRule;
