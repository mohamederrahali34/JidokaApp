import React from "react";

import { SITE_CONFIG } from "~config/site";
import type { NextPage } from "~types/next";

import { NextSeo } from "next-seo";
import Home from "~components/home/Home";

const Index: NextPage = () => {
  return (
    <div>
      <NextSeo title="Home" />

      <Home />
    </div>
  );
};

export default Index;
