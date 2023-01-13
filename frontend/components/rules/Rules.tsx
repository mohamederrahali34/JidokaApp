import { FC, useCallback, useEffect, useState } from "react";
import ListRules from "~components/listRules";
import NoRule from "~components/noRule";
import { useGetRulesOfActualBoard } from "~hooks/hooks";
import boardApi, { RuleType } from "~services/trello/board.service";
import { getUserState } from "~store/configure";
import { getSelectedBoard } from "~store/user/userSlice";

const Rules: FC = () => {
  const [rules, setRules] = useState<RuleType[]>([]);
  //const { rules } = useGetRulesOfActualBoard(reloadRules);
  const [getRulesOfBoard] = boardApi.useGetRulesMutation();
  useEffect(() => {
    loadRules();
  }, []);
  const loadRules = () => {
    const idOfBoard = getSelectedBoard(getUserState()).id;
    getRulesOfBoard({ id: idOfBoard })
      .unwrap()
      .then((data) => {
        setRules(data);
      })
      .catch((err) => console.log("error when loading rules"));
  };
  return rules.length === 0 ? <NoRule /> : <ListRules rules={rules} reloadRules={() => loadRules()} />;
};
export default Rules;
