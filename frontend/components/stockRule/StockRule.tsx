import { FC, useState } from "react";
import { useForm } from "react-hook-form";
import SecondaryButton from "~components/secondaryButton";
import Slack from "~components/slack";
import { RuleDataType } from "~components/slack/Slack";
import StockRuleForm from "~components/stockRuleForm";
import { StyledRuleContainer } from "./StockRule.style";
interface StockRuleProps {
  returnToInitialStateCallback?: () => void;
  idBoard: string;
}
const StockRule: FC<StockRuleProps> = (props) => {
  const { handleSubmit } = useForm({
    mode: "onChange",
  });

  const [displaySlack, setDisplaySlack] = useState(false);
  const [displaySignalAlert, setDisplaySignalAlert] = useState(true);
  const [data, setData] = useState<RuleDataType>({
    ruleId: "",
    colonne: "",
    params: {
      stock: 0,
      owner: "anyone",
      duration: 0,
      unit: "m",
    },
    idBoard: "",
    type: "",
    messageData: { channel: "", message: "", workspace: "" },
  });
  const [disableSignalButton, setDisableSignalButton] = useState(true);

  const onSubmit = () => {
    setData({ ...data });
    setDisplaySlack(true);
    setDisplaySignalAlert(false);
  };
  const onError = (errors) => {
    console.log({ errors });
  };
  const addDataField = (field) => {
    setData((prev) => {
      const params = prev.params;
      const newData = {
        ...prev,
        params: {
          ...params,
        },
      };
      if (field.param) {
        newData.params[field.name] = field.value;
      } else {
        newData[field.name] = field.value;
      }
      console.log({ newData });

      return newData;
    });
  };
  return (
    <div
      style={{
        backgroundColor: "rgb(227, 236, 245,0.5)",
        border: "1px solid #053158",
        marginTop: "30px",
        background: "#E3ECF5",
        boxSizing: "border-box",
        borderRadius: "10px",
      }}
    >
      <StyledRuleContainer onSubmit={handleSubmit(onSubmit, onError)}>
        <StockRuleForm addDataField={addDataField} disableSignalButton={(disable) => setDisableSignalButton(disable)} />
        {displaySignalAlert == true ? (
          <SecondaryButton type={"submit"} text={"Signal Alert"} disabled={disableSignalButton} />
        ) : (
          <></>
        )}
      </StyledRuleContainer>
      {displaySlack == true ? (
        <Slack
          ruleData={data}
          idBoard={props.idBoard}
          returnToInitialStateCallback={props.returnToInitialStateCallback}
          isNotValid={disableSignalButton}
          action={"create"}
          defaultMessage={
            data.params.owner == "anyone"
              ? "There are more than {cardnumber} card(s) in column {columnname}"
              : "There are more than {cardnumber} card(s) belonging to {ownername} in column {columnname}"
          }
        />
      ) : (
        <></>
      )}
    </div>
  );
};

export default StockRule;
