import { FC, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { fieldForm, RuleDataType } from "~components/slack/Slack";
import slackApi, { ChannelType } from "~services/slack/slack.service";
import { StyledChannel, StyledSlackMessage, StyledText, StyledWorkspace } from "./SlackForm.style";
interface ISlackFormProps {
  defaultMessage: string;
  defaultChannel: string;
  defaultWorkspace: string;
  verifySlackValidation: (isValid) => void;
  action: "create" | "edit";
  ruleData: RuleDataType;
  getFormActualFormData: (field: fieldForm) => void;
}
const SlackForm: FC<ISlackFormProps> = (props) => {
  const [getAllChannels] = slackApi.useGetAllChannelsMutation();
  const [channels, setChannels] = useState<ChannelType[]>();
  const [defaultMessage, setDefaultMessage] = useState("");

  useEffect(() => {
    setValue("message", props.defaultMessage);
    setValue("channel", props.defaultChannel);
    setValue("workspace", props.defaultWorkspace);
    if (props.action == "edit") {
      props.getFormActualFormData({
        name: "channel",
        value: props.defaultChannel,
      });
      props.getFormActualFormData({
        name: "workspace",
        value: props.defaultWorkspace,
      });
      verifySlackValidation();
    }
    getAllChannels({})
      .unwrap()
      .then((res) => {
        setChannels(res);
        props.getFormActualFormData({
          name: "message",
          value: props.defaultMessage,
        });
      })
      .catch((err) => console.log("errors", err));
  }, [props.ruleData]);

  const inializeSlackForm = () => {
    //setValue("message", props.ruleData?.messageData?.message);
    setValue("channel", props.ruleData?.messageData?.channel);
    setValue("workspace", props.ruleData?.messageData?.workspace);
  };
  const { register, getValues, setValue } = useForm({ mode: "onChange" });
  const verifySlackValidation = () => {
    const notValid = getValues("workspace") == "none" || getValues("channel") == "none" || getValues("message") == "";
    props.verifySlackValidation(!notValid);
  };

  return (
    <div>
      <StyledText>Post message</StyledText>
      <textarea
        style={StyledSlackMessage}
        defaultValue={defaultMessage}
        {...register("message", {
          required: true,
          onChange: () => {
            verifySlackValidation();
            props.getFormActualFormData({
              name: "message",
              value: getValues("message"),
            });
          },
        })}
      ></textarea>

      <p>
        <StyledText> In workspace </StyledText>
        <select
          style={StyledWorkspace}
          defaultValue={props.defaultWorkspace}
          {...register("workspace", {
            required: true,
            onChange: () => {
              verifySlackValidation();
              props.getFormActualFormData({
                name: "workspace",
                value: getValues("workspace"),
              });
            },
          })}
        >
          <option value="none">Select workspace</option>
          <option value="Nimbleways">JidokaBotWorkspace</option>
        </select>
        <StyledText>to Slack channel </StyledText>{" "}
        <select
          style={StyledChannel}
          defaultValue={props.defaultChannel}
          {...register("channel", {
            required: true,
            onChange: () => {
              verifySlackValidation();
              props.getFormActualFormData({
                name: "channel",
                value: getValues("channel"),
              });
            },
          })}
        >
          <option value="none">Select channel</option>
          {channels?.map((channel) => (
            <option value={channel.name} key={channel.name}>
              {channel.name}
            </option>
          ))}
        </select>{" "}
      </p>
    </div>
  );
};

export default SlackForm;
