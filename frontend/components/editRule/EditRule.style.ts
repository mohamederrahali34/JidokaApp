import styled from "@emotion/styled";

export const StyledEditFromRuleContainer = styled.form`
display : flex ;
flex-direction : row ;
min-height : 100px;
border-radius : 15px;
box-sizing: border-box;
align-items : center;
padding-left : 20px;
justify-content : space-between;
box-sizing: border-box;
border-radius: 10px;
color : black ;
`
export const editRuleContainerStyle =  {
    backgroundColor: "rgb(227, 236, 245,0.5)",
    border: "1px solid #053158",
    marginTop: "30px",
    background: "#E3ECF5",
    boxSizing: "border-box" as "border-box" ,
    borderRadius: "10px",
    marginLeft: "52px",
    marginRight: "52px",
  }
  export const durationInputStyle = {
    width: "100px",
    border: "1px solid #5A7184",
    textAlign: "start" as "start",
    boxSizing: "border-box" as "border-box" ,
    borderRadius: "5px",
    height: "28px",
    paddingLeft: "6px",
    marginRight: "8px",
  }
  export const unitInputStyle = {
    width: "105px",
    border: "1px solid #5A7184",
    textAlign: "start" as "start",
    boxSizing: "border-box" as "border-box",
    borderRadius: "5px",
    height: "28px",
    paddingLeft: "6px",
  }
  export const colonneInputStyle = {
    display: "inline-flex",
    border: "1px solid #5A7184",
    boxSizing: "border-box" as "border-box",
    borderRadius: "5px",
    height: "28px",
    paddingRight: "25px",
    paddingLeft: "6px",
  }
export const StyledText = styled.span`
color : black ;
font-size : 1.1rem ;
font-weight : bold ;
font-family: 'Avenir';
font-style: normal;
font-weight: 350;
font-size: 16px;
line-height: 25px; 
padding-left : 5px ;
padding-right : 5px;
`