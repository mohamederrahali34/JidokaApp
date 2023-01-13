import type { LayoutType } from "~layouts/config";

import type {
  NextComponentType as BaseNextComponentType,
  NextPage as BaseNextPage,
  NextPageContext as BaseNextPageContext,
} from "next";

export type NextPageProps = {
  layout?: LayoutType;
};
export type NextPage<P = {}, IP = P> = BaseNextPage<P, IP> & NextPageProps;

export type NextComponentType = BaseNextComponentType<BaseNextPageContext, unknown> & NextPageProps;
