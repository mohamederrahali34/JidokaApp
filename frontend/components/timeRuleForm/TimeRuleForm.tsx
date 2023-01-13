import { FC, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { APPLICATION_KEY } from "~application.info";
import trelloApi, { ListType } from "~services/trello/trello.service";
import { getUserState } from "~store/configure";
import { getSelectedBoard } from "~store/user/userSlice";
import { StyledText } from "./TimeRuleForm.style";

export interface TimeRuleProps {
  addDataField: (field: { name: string; value: string; param: boolean }) => void;
  disableSignalButton: (isValid: boolean) => void;
}

const TimeRuleForm: FC<TimeRuleProps> = (props) => {
  const [columns, setColumns] = useState<ListType[]>([]);

  const { register, getValues, setValue } = useForm({
    mode: "onChange",
  });
  const boardId = getSelectedBoard(getUserState()).id;
  console.log({ boardId });

  const [getListsOfBoard] = trelloApi.useGetListsOfBoardMutation();
  useEffect(() => {
    setValue("duration", 0);
    props.addDataField({
      name: "type",
      value: "time",
      param: false,
    });
    props.addDataField({
      name: "idBoard",
      value: boardId,
      param: false,
    });
    props.addDataField({
      name: "unit",
      value: getValues("unit"),
      param: true,
    });
    if (!boardId) {
      console.log("probleme when loading columns: board is undefined");
    }
    getListsOfBoard({
      auth: {
        id: localStorage.getItem("userId") || "",
      },
      boardId: boardId,
    })
      .unwrap()
      .then((res) => {
        console.log({ columns: res });

        setColumns(res);
      })
      .catch((_err) => {
        console.log("error when loading columns");
      });
  }, [boardId]);

  const verifyValidation = () => {
    const notValid = getValues("duration") > 100 || getValues("duration") <= 0 || getValues("colonne") === "none";
    props.disableSignalButton(notValid);
  };
  return (
    <div>
      <p>
        <StyledText>When a card has spent more than</StyledText>{" "}
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
          {...register("duration", {
            required: true,
            min: 1,
            max: 100,
            onChange: () => {
              verifyValidation();
              props.addDataField({
                name: "duration",
                value: getValues("duration"),
                param: true,
              });
            },
          })}
        />{" "}
        <select
          style={{
            width: "105px",
            border: "1px solid #5A7184",
            textAlign: "start",
            boxSizing: "border-box",
            borderRadius: "5px",
            height: "28px",
            paddingLeft: "6px",
          }}
          {...register("unit", {
            required: true,
            onChange: () => {
              verifyValidation();
              props.addDataField({
                name: "unit",
                value: getValues("unit"),
                param: true,
              });
            },
          })}
        >
          <option value="h">Hour(s)</option>
          <option value="m">Minute(s)</option>
        </select>
        <StyledText> In </StyledText>
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
              verifyValidation();
              props.addDataField({
                name: "colonne",
                value: getValues("colonne"),
                param: false,
              });
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
    </div>
  );
};

export default TimeRuleForm;
