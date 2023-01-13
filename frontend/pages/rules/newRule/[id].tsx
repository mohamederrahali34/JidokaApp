import { NextSeo } from "next-seo";
import type { NextPage } from "~types/next";
import { useForm } from "react-hook-form";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { LayoutType } from "~layouts/config";
import {
  headTableStyle,
  StyledApplayNewRule,
  StyledNewRuleContainer,
  StyledRuleContainer,
  StyledTableHeadElemnt,
  StyledTextWhite,
} from "~style/rules.style";
import { primaryColor } from "~style/colors";
import ruleApi from "~services/api/rule.service";

const NewRulePage: NextPage = () => {
  const [addRuleToBoard] = ruleApi.useNewRuleMutation();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [idBoard, setIdBoard] = useState("");
  const router = useRouter();
  useEffect(() => {
    const { id } = router.query;
    let boardId = "";

    if (typeof id == "string") {
      boardId = id;
    } else {
      if (id) {
        boardId = id[0];
      }
    }
    setIdBoard(boardId);
  }, []);
  const onSubmit = (data) => {
    addRuleToBoard({
      idBoard: idBoard,
      type: data.type,
      params: {
        columnName: data.colonne,
        duration: data.duration,
        unit: data.unit,
      },
      message: data.message,
      channel: data.channel,
      workspace: data.worspace,
    })
      .then((res) => {
        console.log({ res });
      })
      .catch((err) => {
        console.log({ err });
      });
  };
  return (
    <>
      <NextSeo title="création d'une règle" />
      <StyledNewRuleContainer>
        <StyledRuleContainer onSubmit={handleSubmit(onSubmit)}>
          <table>
            <tr style={headTableStyle}>
              <th style={StyledTableHeadElemnt}>Nom de la règle</th>
              <th style={StyledTableHeadElemnt}>La règle de gestion</th>
              <th style={StyledTableHeadElemnt}>Action</th>
            </tr>
            <tr>
              <td>
                <input {...register("name", { required: true })} />
              </td>
              <td></td>
              <td>
                {" "}
                <StyledApplayNewRule
                  type={"submit"}
                  style={{
                    backgroundColor: primaryColor,
                    borderRadius: "4px",
                    marginLeft: "5px",
                    fontWeight: "bold",
                    width: "120px",
                    height: "30px",
                  }}
                >
                  Appliquer
                </StyledApplayNewRule>
              </td>
            </tr>
          </table>
        </StyledRuleContainer>
      </StyledNewRuleContainer>
    </>
  );
};
NewRulePage.layout = LayoutType.Logout;
export default NewRulePage;
