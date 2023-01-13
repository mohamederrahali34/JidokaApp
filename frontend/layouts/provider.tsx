import React from "react";

import type { LayoutType } from "./config";
import { layouts } from "./config";

interface LayoutProviderProps {
  layout?: LayoutType;
}

export const LayoutProvider: React.FC<LayoutProviderProps> = ({ children, layout }) => {
  const Layout = React.useMemo(() => layouts[layout ?? "default"], [layout]);

  return <Layout>{children}</Layout>;
};
