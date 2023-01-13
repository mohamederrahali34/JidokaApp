import { FC, useEffect } from "react";
import Rule from "~components/rule";
import { RuleType } from "~services/trello/board.service";
import { StyledActiveRuleTitle } from "./ListRules.style";

interface ListRulesProps {
  rules: RuleType[] | undefined;
  reloadRules: () => void;
}
const ListRules: FC<ListRulesProps> = (props) => {
  useEffect(() => {
    console.log({ "list rules :": props.rules });
  });
  return (
    <div>
      <StyledActiveRuleTitle>Active rules</StyledActiveRuleTitle>
      <ul>
        {props.rules?.map((r) => (
          <li key={r.id}>
            <Rule
              idBoard={r.board.id}
              id={r.id}
              type={r.type}
              colonne={r.type == "time" ? r.timeRuleColumnName : r.stockRuleColumnName}
              params={{ duration: r.duration, unit: r.unit, owner: r.owner, stock: r.nbCards }}
              active={r.active}
              message={r.message}
              channel={r.channel}
              workspace={r.workspace}
              changeStep={props.reloadRules}
              ruleId={r.id}
              owner={r.owner}
              stock={0}
            />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListRules;
