import { FC, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import Slack from "~components/slack";
import {
  colonneInputStyle,
  durationInputStyle,
  editRuleContainerStyle,
  StyledEditFromRuleContainer,
  StyledText,
  unitInputStyle,
} from "./EditRule.style";
import { useGetBoardColumns } from "~hooks/hooks";
import ruleApi from "~services/api/rule.service";
interface TimeRuleProps {
  idBoard: string;
  returnToInitialStateCallback?: () => void;
  ruleId: string;
  type: string;
}
export interface MessageSlack {
  message: string;
  channel: string;
  workspace: string;
}
interface RuleFormData {
  duration: number;
  unit: string;
  owner: string;
  stock: number;
  colonne: string;
  type: string;
  idBoard: string;
}
const DEFAULT_MESSAGE_ANYONE = `There are more than {cardnumber} in column {columnname}`;
const DEFAULT_MESSAGE_SAMEOWNER = `There are more than {cardnumber} card(s) belonging to {ownername} in column {columnname}`;
const EditRule: FC<TimeRuleProps> = (props) => {
  const { register, setValue, getValues } = useForm({
    mode: "onChange",
  });
  const [messageData, setMessageData] = useState<MessageSlack | null>(null);
  const [getRuleById] = ruleApi.useGetRuleByIdMutation();
  const initializeForm = () => {
    getRuleById({ id: props.ruleId, type: props.type })
      .unwrap()
      .then((res) => {
        console.log({ res });
        if (props.type == "time") {
          setValue("duration", res.duration);
          setValue("unit", res.unit);
        } else if (props.type == "stock") {
          setValue("owner", res.owner);
          setValue("stock", res.nbCards);
        }

        setValue("colonne", res.type == "time" ? res.timeRuleColumnName : res.stockRuleColumnName);
        setMessageData({ message: res.message, channel: res.channel, workspace: res.workspace });
        getFormData();
        verifyTimeRuleValidation();
      })
      .catch((err) => console.log(err));
  };
  const getFormData = () => {
    setData({
      type: props.type,
      duration: props.type == "time" ? getValues("duration") : 0,
      unit: props.type == "time" ? getValues("unit") : "",
      owner: props.type == "stock" ? getValues("owner") : "anyone",
      stock: props.type == "stock" ? getValues("stock") : 0,
      colonne: getValues("colonne"),
      idBoard: props.idBoard,
    });
  };

  const columns = useGetBoardColumns(props.idBoard, initializeForm);
  const [data, setData] = useState<RuleFormData>({
    duration: 0,
    unit: "",
    colonne: "",
    type: "",
    idBoard: "",
    owner: "anyone",
    stock: 0,
  });
  const [disableSignalButton, setDisableSignalButton] = useState(true);
  const verifyTimeRuleValidation = () => {
    const notValid = getValues("duration") > 100 || getValues("duration") <= 0 || getValues("colonne") === "none";
    setDisableSignalButton(notValid);
    if (!notValid) {
      getFormData();
    }
  };
  const verifySockRuleValidation = () => {
    const notValid = getValues("stock") > 100 || getValues("stock") <= 0 || getValues("colonne") === "none";
    setDisableSignalButton(notValid);
    if (!notValid) {
      getFormData();
    }
  };
  return (
    <div style={editRuleContainerStyle}>
      <StyledEditFromRuleContainer>
        {props.type == "time" && (
          <p>
            <StyledText>When a card has spent more than</StyledText>{" "}
            <input
              style={durationInputStyle}
              type="number"
              {...register("duration", {
                required: true,
                min: 1,
                max: 100,
                onChange: () => {
                  verifyTimeRuleValidation();
                },
              })}
            />{" "}
            <select
              style={unitInputStyle}
              {...register("unit", {
                required: true,
                onChange: () => {
                  verifyTimeRuleValidation();
                },
              })}
            >
              <option value="h">Hour(s)</option>
              <option value="m">Minute(s)</option>
            </select>
            <StyledText> In </StyledText>
            <select
              style={colonneInputStyle}
              {...register("colonne", {
                required: true,
                onChange: () => {
                  verifyTimeRuleValidation();
                },
              })}
            >
              <option value={"none"}>Select column</option>
              {columns?.map((list) => (
                <option value={list.name} key={list.id}>
                  {list.name}
                </option>
              ))}
            </select>
            <input type={"text"} {...register("type")} value="time" hidden />
          </p>
        )}{" "}
        {props.type == "stock" && (
          <p>
            <StyledText>When </StyledText>{" "}
            <select
              style={{
                display: "inline-flex",
                border: "1px solid #5A7184",
                boxSizing: "border-box",
                borderRadius: "5px",
                height: "28px",
                paddingRight: "25px",
                paddingLeft: "6px",
              }}
              {...register("colonne", {
                required: true,
                onChange: () => {
                  verifySockRuleValidation();
                },
              })}
            >
              <option value={"none"}>Select column</option>
              {columns?.map((list) => (
                <option value={list.name} key={list.id}>
                  {list.name}
                </option>
              ))}
            </select>
            <StyledText>has more than</StyledText>
            <input
              style={{
                width: "100px",
                border: "1px solid #5A7184",
                textAlign: "start",
                boxSizing: "border-box",
                borderRadius: "5px",
                height: "28px",
                paddingLeft: "6px",
                marginRight: "8px",
              }}
              type="number"
              {...register("stock", {
                required: true,
                min: 1,
                max: 100,
                onChange: () => {
                  verifySockRuleValidation();
                },
              })}
            />
            <StyledText>card(s) owned by</StyledText>
            <select
              style={{
                width: "130px",
                border: "1px solid #5A7184",
                textAlign: "start",
                boxSizing: "border-box",
                borderRadius: "5px",
                height: "28px",
                paddingLeft: "6px",
              }}
              {...register("owner", {
                required: true,
                onChange: () => {
                  setMessageData({
                    channel: messageData?.channel || "",
                    workspace: messageData?.workspace || "",
                    message: getValues("owner") == "anyone" ? DEFAULT_MESSAGE_ANYONE : DEFAULT_MESSAGE_SAMEOWNER,
                  });

                  verifySockRuleValidation();
                },
              })}
            >
              <option value="anyone">anyone</option>
              <option value="ownerName">ownerName</option>
            </select>
            <input type={"text"} {...register("type")} value="stock" hidden />
          </p>
        )}
      </StyledEditFromRuleContainer>
      {messageData && (
        <Slack
          ruleData={{
            ruleId: props.ruleId,
            colonne: data.colonne,
            idBoard: data.idBoard,
            type: data.type,
            params: {
              duration: data.duration,
              unit: data.unit,
              owner: data.owner,
              stock: data.stock,
            },
            messageData: { ...messageData },
          }}
          idBoard={props.idBoard}
          returnToInitialStateCallback={() => {}}
          isNotValid={disableSignalButton}
          action={"edit"}
          defaultMessage={""}
        />
      )}
    </div>
  );
};

export default EditRule;
