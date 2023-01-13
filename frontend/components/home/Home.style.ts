import { CSSProperties } from "@mui/styled-engine";
import styled from "@emotion/styled";
import { secondaryColor, primaryColor } from "~style/colors";
export const homeStyleContainer: CSSProperties = {
  height: "100vh",
  display: "flex",
  flexDirection: "column",
  backgroundColor: secondaryColor,
  rowGap : "30px",
  color: "white",
  alignContent : "space-between"
};
export const linkStyle: CSSProperties = {
  display: "flex",
  flexDirection: "row",
  justifyContent: "center",
  fontSize: "20px",
  margin: "5px",
  marginTop : "30px",
  color : "white",
  minWidth : "600px"
 
};
export const subLinkStyle = {
  
  backgroundColor: primaryColor,
  padding: "10px",
  borderRadius: "8px" ,
  cursor: "pointer",  
  display: "flex",
  flexDirection: 'row' as 'row',
  flexWrap: 'nowrap'  as 'nowrap',
  justifyContent: "space-between",
  alignItems: "center",
  alignContent: "space-between",
  gap : "0 10px"
    };

export const descStyle = {
  display: "flex",
  flexDirection: "column",
  justifyContent: "flex-center",
  alignItems: "center",
  marginTop : 0,
  rowGap : "10px",

};
export const jidokaStyle = {
  color: "white",
  fontSize: "3.5rem",
  height  : "67.72px"
};
export const botStyle = {
  color: primaryColor,
  fontSize: "3.5rem",
  width: "134.98px",
  height : "63px"
};
export const trelloPresStyle = {
  color: "white",
  fontSize: "1rem",
  fontFamily: 'Avenir',
  fontStyle: "normal",
  marginTop : "30px"
};
export const StyledPattern = styled.div`
position : relative;
display : flex ;
justify-content : flex-start ;
left   : 15px; 
top : 20.88px;
color : black ;
width : 21%;
height : 31% ;
`
export const StyledFooter = styled.div`
position : absolute ;
right   :26px   ; 
bottom : 20px;
width : 21%;
height : 31% ;
`
