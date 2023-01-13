import React from "react";

import { SITE_CONFIG } from "~config/site";

import Head from "next/head";
import { useRouter } from "next/router";
import { DefaultSeo } from "next-seo";
import { colorGray90, primaryColor, secondaryColor } from "~style/colors";
import LogoutComp from "~components/logoutComp";

export const jidokaStyleLayout = {
  color: secondaryColor,
  fontSize: "3rem",
};
export const botStyleLayout = {
  color: primaryColor,
  fontSize: "3rem",
};
const LogoutLayout: React.FC = ({ children }) => {
  const router = useRouter();
  return (
    <>
      <Head>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
      </Head>

      <DefaultSeo
        canonical={SITE_CONFIG.siteUrl + (router.asPath || "")}
        defaultTitle={SITE_CONFIG.title}
        description={SITE_CONFIG.description}
        titleTemplate={`%s ${SITE_CONFIG.titleSeparator} ${SITE_CONFIG.title}`}
      />
      <div
        style={{
          backgroundColor: colorGray90,
          width: "100vw",
          display: "flex",
          flexDirection: "row",
          justifyContent: "end",
          gap: "200px",
          paddingTop: "15px",
        }}
      >
        <div
          style={{
            width: "250px",
            fontFamily: "Avenir",
            fontWeight: "850",
            marginRight: "15px",
          }}
        >
          <span style={jidokaStyleLayout}>
            Jidoka
            <span style={botStyleLayout}> Bot</span>
          </span>
        </div>
        <div>
          <LogoutComp />
        </div>
      </div>
      {children}
    </>
  );
};

export default LogoutLayout;
