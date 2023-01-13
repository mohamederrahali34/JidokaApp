import dynamic from "next/dynamic";
import type { ComponentType } from "react";

export enum LayoutType {
  Default = "default",
  About = "about",
  Logout = "logout",
}

export const layouts: Record<LayoutType, ComponentType> = {
  [LayoutType.Default]: dynamic(async () => import("~layouts/templates/default")),
  [LayoutType.About]: dynamic(async () => import("~layouts/templates/about")),
  [LayoutType.Logout]: dynamic(async () => import("~layouts/templates/logout")),
} as const;
