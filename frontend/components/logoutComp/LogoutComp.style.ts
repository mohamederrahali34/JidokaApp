import styled from "@emotion/styled";
import { secondaryColor } from "~style/colors";

export const LogoutCompContainerStyle =  {
    display: "flex",
    flexDirection: 'row' as 'row',
    width: "330px",
    columnGap: "60px",
    justifyContent: "flex-start",
    alignItems : "center  ",
  } ;
  export const LogoutUserNameStyle =  {
    color: secondaryColor,
    marginTop: "30px",
    fontFamily: 'Avenir',
    fontStyle: "normal",
    fontWeight: "500",
    fontSize: "16px",
    marginBottom :"25px", 
  }
  export const  StyledDialigContent = styled.div`
  font-family: 'Avenir';
font-style: normal;
font-weight: 350;
font-size: 20px;
line-height: 31px;
text-align: center;
`
export const buttonStyle = {
  boxShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
  borderRadius: "10px",
  padding: "16px 20px",
  fontFamily: "Circular Std",
  fontStyle: "normal",
  fontWeight: "500",
  fontSize: "18px",
  lineHeight: "23px",
  width: "208px",
  border: "1px solid #053158",
}