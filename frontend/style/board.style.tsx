import styled from "@emotion/styled";
import { colorGray90, primaryColor, secondaryColor } from "./colors";

export const StyledButtonNewRule = styled.button`
  height: 45px;
  background-color: ${primaryColor};
  color: white;
  margin-right: 30px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 10px 7px;
  align-items: center;
  padding: 10px;
`;
export const StyledButtonBack = styled.button`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-left: 58px;
  align-items: center;
  gap: 0 5px;
  height: 45px;
  width: 200px;
  color: ${secondaryColor};
  font-weight: bold;
  font-size: 20px;
  font-family: "Avenir";
  font-style: normal;
  font-weight: 850;
  font-size: 30px;
  line-height: 47px;
`;
export const StyledContainer = styled.div`
  background-color: ${colorGray90};
  height: 100vh;
  display: flex;
  flex-direction: column;
  column-gap: 15px;
  width: 100vw;
`;
export const StyledButtonBackContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 15px;
  margin-top: 30px;
`;

export const STyledRulesList = styled.ul``;
export const StyledToggleWebhook = styled.button`
  background-color: ${secondaryColor};
  height: 100vh;
  display: flex;
  flex-direction: column;
  column-gap: 15px;
  width: 100vw;
`;
