import styled from "@emotion/styled";
import { primaryColor, secondaryColor } from "~style/colors";

export const StyledRule = styled.div`
color : black;
margin-top : 15px;
font-size : 16px;
margin-bottom : 15px
font-family: 'Avenir';
font-style: normal;
font-weight: 400;
line-height: 25px;
min-height : 100px;
background: #FFFFFF;
border: 1px solid #053158;
border-radius: 10px;
display : flex;
flex-direction : row;
justify-content : space-between;
align-items : center;
margin-left : 55px;
padding-left : 15px;
margin-right : 30px;
` 
export const StyledActiveLabel = styled.span`
font-family: 'Avenir';
font-style: normal;
font-weight: 500;
font-size: 16px;
line-height: 25px;
flex-grow: 0;
`
export const StyledParam = styled.span`
color : ${primaryColor}
`
export const StyledOperationContainer = styled.div`
display : flex;
flex-direction : row;
justify-content : space-between;
width : 40%;
margin-right : 20px;
`
export const activeLabelStyleOff = {
    color: "black",
    fontFamily: "Avenir",
    fontStyle: "normal",
    fontWeight: "500",
    fontSize: "16px",
    lineHeight: "25px",
  }
  export const activeLabelStyleOn = {
    color: primaryColor,
    fontFamily: "Avenir",
    fontStyle: "normal",
    fontWeight: "500",
    fontSize: "16px",
    lineHeight: "25px",
  }
  export const editContainerStyle = {
    display: "flex",
    flexDirection: "row" as "row",
    justifyContent: "center",
    columnGap: "7px",
    alignItems: "center",
    cursor: "pointer",
  }
  export const editLabelStyle = {
    fontFamily: "Avenir",
    fontStyle: "normal",
    fontWeight: 500,
    fontSize: "16px",
    lineHeight: "25px",
  }
  export const DeleteContainerStyle = {
    display: "flex",
    flexDirection: "row" as "row",
    justifyContent: "center",
    columnGap: "7px",
    alignItems: "center",
    cursor: "pointer",
  }