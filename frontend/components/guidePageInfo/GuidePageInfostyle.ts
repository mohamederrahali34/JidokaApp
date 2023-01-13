import styled from "@emotion/styled";

export const StyledBoardInfo = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: row;
  align-items: baseline;
  margin-left : 50px;
  background-color: ${(props) => {
    return props.backgroundColor;
  }};
`;
export const StyledBoardName = styled.div`
  color: white;
  diplay: inline;
  margin: auto;
`;
