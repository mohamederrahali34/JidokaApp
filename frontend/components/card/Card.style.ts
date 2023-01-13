import styled from "@emotion/styled";

export const StyledBoard = styled.div<{ image: string }>`
  background-image: ${(props) => `url("${props.image}")`};
  background-size: cover;
  background-color: #cccccc;
  margin: 10px;
  margin-left : 0px;
  width: 230px;
  height: 125px;
  border-radius: 10px;
  cursor : pointer;
  `;
export const StyledTitle = styled.div`
  color: white;
  margin-top : 10px;
  margin-left : 10px;
  fontFamily: 'Avenir';
  font-style: normal;
  font-weight: 850;
`;
