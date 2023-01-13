import { Alert, Button, IconButton, Snackbar } from "@mui/material";
import { useRouter } from "next/router";

import React, { FC, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { MessageSlack } from "~components/editRule/EditRule";
import MessageAlert from "~components/messageAlert";
import SecondaryButton from "~components/secondaryButton";
import SlackForm from "~components/slackForm";
import { StyledText } from "~components/timeRule/TimeRule.style";
import ruleApi from "~services/api/rule.service";
import slackApi, { ChannelType } from "~services/slack/slack.service";
import { secondaryColor } from "~style/colors";
import { StyledChannel, StyledSlackContainer, StyledSlackMessage, StyledWorkspace } from "./Slack.style";

interface ISlackProps {
  ruleData: RuleDataType;
  idBoard?: string;
  returnToInitialStateCallback?: () => void;
  isNotValid?: boolean;
  action: "create" | "edit";
  defaultMessage: string;
}
export type RuleDataType = {
  ruleId?: string;
  colonne: string;
  idBoard: string;
  type: string;
  params: TimeRuleDataType & StockRuleDataType;
  messageData?: MessageSlack;
};
export type TimeRuleDataType = {
  duration: number;
  unit: string;
};
export type StockRuleDataType = {
  stock: number;
  owner: string;
};
interface SlackData {
  workspace?: string;
  channel?: string;
  message?: string;
}
export interface fieldForm {
  name: string;
  value: string;
}

const Slack: FC<ISlackProps> = (props) => {
  const router = useRouter();
  const [addRuleToBoard] = ruleApi.useNewRuleMutation();
  const [updateRuleOfBoard] = ruleApi.useUpdateRuleMutation();
  const [formSlackIsValid, setFormSlackIsValid] = useState(false);
  const [openSuccesMessage, setOpenSuccesMessage] = useState(false);
  const [slackData, setSlackData] = useState<SlackData>();

  const onSubmit = () => {
    const lastStepData = props.ruleData;
    console.log({ lastStepData });
    const data = {
      message: slackData?.message || "",
      workspace: slackData?.workspace || "",
      channel: slackData?.channel || "",
      id: lastStepData.ruleId || "",
      idBoard: lastStepData.idBoard,
      type: lastStepData.type,
      params:
        lastStepData.type == "time"
          ? {
              columnName: lastStepData.colonne,
              duration: lastStepData.params.duration,
              unit: lastStepData.params.unit,
            }
          : {
              columnName: lastStepData.colonne,
              stock: lastStepData.params.stock,
              owner: lastStepData.params.owner,
            },
    };
    if (props.action == "edit") {
      //update rule
      updateRuleOfBoard(data)
        .unwrap()
        .then((res) => {
          setOpenSuccesMessage(true);
        });
    } else if (props.action == "create") {
      //save new rule
      addRuleToBoard(data)
        .then((_res) => {
          setOpenSuccesMessage(true);
        })
        .catch((err) => {
          setOpenSuccesMessage(false);
        });
    }
  };
  const getFormActualFormData = (fieldForm: fieldForm) => {
    setSlackData((prev) => ({
      ...prev,
      [fieldForm.name]: fieldForm.value,
    }));
  };
  const verifySlackValidation = (isValid: boolean) => {
    setFormSlackIsValid(isValid);
  };

  const handleClose = (event: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenSuccesMessage(false);
  };
  console.log({ messageData: props.ruleData.messageData });

  return (
    <StyledSlackContainer>
      <SlackForm
        defaultMessage={props.action == "edit" ? props.ruleData?.messageData?.message || "" : props.defaultMessage}
        defaultChannel={props.action == "edit" ? props.ruleData?.messageData?.channel || "" : "none"}
        defaultWorkspace={props.action == "edit" ? props.ruleData?.messageData?.workspace || "" : "none"}
        verifySlackValidation={verifySlackValidation}
        action={props.action}
        ruleData={props.ruleData}
        getFormActualFormData={getFormActualFormData}
      />
      <div
        style={{
          marginTop: "15px",
        }}
      >
        <SecondaryButton
          text={"Validate"}
          disabled={props.isNotValid || !formSlackIsValid}
          type={"button"}
          onClick={onSubmit}
          style={{ marginLeft: "0" }}
        />
        <MessageAlert
          open={openSuccesMessage}
          onClose={handleClose}
          message={`Your rule has been ${props.action == "edit" ? "edited !" : "added !"}`}
        />
        <SecondaryButton
          text={"Cancel"}
          disabled={false}
          type={"button"}
          onClick={
            props.action == "edit"
              ? () => {
                  router.push("/board");
                }
              : props.returnToInitialStateCallback
          }
          style={{ color: secondaryColor, background: "white" }}
        />
      </div>
    </StyledSlackContainer>
  );
};

export default Slack;
