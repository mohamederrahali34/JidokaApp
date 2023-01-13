import React from "react";

import { SITE_CONFIG } from "~config/site";
import { LayoutType } from "~layouts/config";
import type { NextPage } from "~types/next";

import { NextSeo } from "next-seo";

const AboutPage: NextPage = () => {
  return (
    <div>
      <NextSeo title="AboutPage" />

      <h1>{SITE_CONFIG.title}</h1>
      <h2>{SITE_CONFIG.description}</h2>
    </div>
  );
};

AboutPage.layout = LayoutType.About;

export default AboutPage;
