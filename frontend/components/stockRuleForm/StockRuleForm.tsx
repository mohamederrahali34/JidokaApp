import { FC, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { APPLICATION_KEY } from "~application.info";
import { StyledText } from "~components/editRule/EditRule.style";
import trelloApi, { ListType } from "~services/trello/trello.service";
import { getUserState } from "~store/configure";
import { getSelectedBoard } from "~store/user/userSlice";

export interface StockRuleFormProps {
  addDataField: (field: { name: string; value: string; param: boolean }) => void;
  disableSignalButton: (isValid: boolean) => void;
}

const StockRuleForm: FC<StockRuleFormProps> = (props) => {
  const [columns, setColumns] = useState<ListType[]>([]);
  const { register, getValues, setValue } = useForm({
    mode: "onChange",
  });
  const boardId = getSelectedBoard(getUserState()).id;

  const [getListsOfBoard] = trelloApi.useGetListsOfBoardMutation();
  useEffect(() => {
    setValue("stock", 0);
    props.addDataField({
      name: "type",
      value: "stock",
      param: false,
    });
    props.addDataField({
      name: "idBoard",
      value: boardId,
      param: false,
    });
    props.addDataField({
      name: "owner",
      value: getValues("owner"),
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
    const notValid = getValues("stock") > 100 || getValues("stock") <= 0 || getValues("colonne") === "none";
    props.disableSignalButton(notValid);
  };
  return (
    <div>
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
              verifyValidation();
              props.addDataField({
                name: "stock",
                value: getValues("stock"),
                param: true,
              });
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
              verifyValidation();
              props.addDataField({
                name: "owner",
                value: getValues("owner"),
                param: true,
              });
            },
          })}
        >
          <option value="anyone">anyone</option>
          <option value="ownerName">Same Owner</option>
        </select>
        <input type={"text"} {...register("type")} value="stock" hidden />
      </p>
    </div>
  );
};

export default StockRuleForm;
