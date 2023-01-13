import type { NextPage } from "~types/next";

import { NextSeo } from "next-seo";
import { LayoutType } from "~layouts/config";
import EditRule from "~components/editRule";
import { colorGray90 } from "~style/colors";
import { useRouter } from "next/router";
import GuidePageInfo from "~components/guidePageInfo";
import Back from "~components/back";

const EditRulePage: NextPage = () => {
  const router = useRouter();
  const { idBoard, ruleId, type } = router.query;

  return (
    <div style={{ backgroundColor: colorGray90, height: "85vh" }}>
      <NextSeo title="EditRule" />
      <div>
        <Back
          onClick={() => {
            router.push("/board");
          }}
        />
        <GuidePageInfo info={"Edit Rule"} />
        <EditRule
          idBoard={typeof idBoard == "string" ? idBoard : ""}
          ruleId={typeof ruleId == "string" ? ruleId : ""}
          type={typeof type == "string" ? type : ""}
        />
      </div>
    </div>
  );
};
EditRulePage.layout = LayoutType.Logout;
export default EditRulePage;
