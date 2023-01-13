import { FC, useState } from "react";
import {
  activeLabelStyleOff,
  activeLabelStyleOn,
  DeleteContainerStyle,
  editContainerStyle,
  editLabelStyle,
  StyledActiveLabel,
  StyledOperationContainer,
  StyledParam,
  StyledRule,
} from "./Rule.style";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import ToggleOffIcon from "@mui/icons-material/ToggleOff";
import ToggleOnIcon from "@mui/icons-material/ToggleOn";
import { primaryColor } from "~style/colors";
import Image from "next/image";
import { DeleteIcon } from "~components/icons/delete";
import ruleApi from "~services/api/rule.service";
import Link from "next/link";
import { StockRuleDataType, TimeRuleDataType } from "~components/slack/Slack";
import StorageIcon from "@mui/icons-material/Storage";

interface RuleProps {
  id: string;
  type: string;
  colonne: string;
  params: TimeRuleDataType & StockRuleDataType;
  idBoard: string;
  active: boolean;
  owner: string;
  stock: number;
  message: string;
  channel: string;
  workspace: string;
  changeStep: () => void;
  ruleId: string;
}

const Rule: FC<RuleProps> = (props) => {
  const [isActive, setIsActive] = useState<boolean>(props.active);
  const [toggleActivationRule] = ruleApi.useToggleActivateRuleMutation();
  const [deleteRule] = ruleApi.useDeleteRuleMutation();
  const updateRuleSate = (prev: boolean) => {
    toggleActivationRule({ id: props.id })
      .unwrap()
      .then((res) => {
        console.log({ res });
      })
      .then((err) => {
        console.log({ err });
      });
    setIsActive((prev) => !prev);
  };

  const handleDeleteRule = () => {
    deleteRule({ id: props.id })
      .unwrap()
      .then((_res) => {
        props.changeStep();
      })
      .catch((err) => console.log("error :", err));
  };
  const toggleActive = () => {
    updateRuleSate(isActive);
  };
  const getActiveComponent = () => {
    return isActive == true ? (
      <ToggleOnIcon style={{ color: primaryColor, width: "30px", height: "35px", cursor: "pointer" }} />
    ) : (
      <ToggleOffIcon style={{ color: "black", width: "30px", height: "35px", cursor: "pointer" }} />
    );
  };

  const RuleDescriptionCompoenent =
    props.type == "time" ? (
      <div>
        <AccessTimeIcon style={{ color: "red", marginRight: "15px", float: "left" }} />
        When a card spent more than{" "}
        <StyledParam>
          {Number(props.params.duration)} {props.params.unit == "m" ? "minute(s)" : "hour(s)"}
        </StyledParam>{" "}
        in column <StyledParam>{props.colonne}</StyledParam> , post a message <br />
        in slack channel <StyledParam>{props.channel}</StyledParam>{" "}
      </div>
    ) : props.type == "stock" ? (
      <div>
        <StorageIcon style={{ color: "red", marginRight: "15px" }} />
        When <StyledParam>{props.colonne}</StyledParam> has more than <StyledParam>{props.params.stock}</StyledParam>{" "}
        card(s) owned by <StyledParam>{props.params.owner}</StyledParam> send a message to Slack{" "}
      </div>
    ) : (
      <div></div>
    );
  console.log({ props });
  return (
    <ul>
      <StyledRule>
        {RuleDescriptionCompoenent}
        <StyledOperationContainer>
          <div onClick={toggleActive}>
            <StyledActiveLabel style={activeLabelStyleOff}>Off</StyledActiveLabel> {getActiveComponent()}{" "}
            <StyledActiveLabel style={activeLabelStyleOn}>On</StyledActiveLabel>{" "}
          </div>
          <Link
            href={{
              pathname: "/rules/edit",
              query: { ruleId: props.ruleId, type: props.type, idBoard: props.idBoard },
            }}
          >
            <div style={editContainerStyle}>
              <Image src={"/edit_rule.png"} width={22} height={22} />
              <span style={editLabelStyle}>
                <a>Edit</a>
              </span>
            </div>
          </Link>
          <div style={DeleteContainerStyle} onClick={handleDeleteRule}>
            <DeleteIcon />
            <span>Delete</span>
          </div>
        </StyledOperationContainer>
      </StyledRule>
    </ul>
  );
};

export default Rule;
