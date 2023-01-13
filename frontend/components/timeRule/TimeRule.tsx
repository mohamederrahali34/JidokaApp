import { FC, useState } from "react";
import { useForm } from "react-hook-form";
import SecondaryButton from "~components/secondaryButton";
import { StyledRuleContainer, StyledText } from "./TimeRule.style";
import Slack from "~components/slack";
import { RuleDataType } from "~components/slack/Slack";
import TimeRuleForm from "~components/timeRuleForm";
interface TimeRuleProps {
  idBoard: string;
  returnToInitialStateCallback?: () => void;
}
const DEFAULT_TIME_RULE_MESSAGE = `{Cardname} has exceeded the {timespent} timebox in {columnname}.
The card is owned by {ownername} and can be accessed here {cardlink}`;
const TimeRule: FC<TimeRuleProps> = (props) => {
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
    setDisplaySlack(true);
    setDisplaySignalAlert(false);
  };
  const onError = (errors) => {
    console.log({ errors });
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
        <TimeRuleForm
          addDataField={(field) => {
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
              return newData;
            });
          }}
          disableSignalButton={(disable) => setDisableSignalButton(disable)}
        />
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
          defaultMessage={DEFAULT_TIME_RULE_MESSAGE}
        />
      ) : (
        <></>
      )}
    </div>
  );
};

export default TimeRule;
