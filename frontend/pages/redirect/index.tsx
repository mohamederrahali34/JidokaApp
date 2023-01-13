import type { NextPage } from "~types/next";

import { NextSeo } from "next-seo";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { trelloApi } from "~services/trello/trello.service";
import { useDispatch } from "react-redux";
import { login } from "~store/user/userSlice";
import { StyledRedirect, StyledRedirectText } from "~style/redirect.style";
import { APPLICATION_KEY } from "~application.info";
import { TRELLO_AUTHORIZE_LINK } from "~env";
import LoadingAnimation from "~components/loadingAnimation";
import userApi from "~services/users/user.service";

const RedirectPage: NextPage = () => {
  const router = useRouter();
  const dispatch = useDispatch();
  const [saveUser] = userApi.useNewUserMutation();
  const authorizeUser = () => {
    router.push(TRELLO_AUTHORIZE_LINK);
  };
  useEffect(() => {
    let token = "";
    //persist token in db
    const tokenPath = router.asPath.split("=", 2)[1];
    const tokenLocalStorage = localStorage.getItem("trello_token");

    // token = tokenPath ?? tokenLocalStorage;
    // if(!token){error}
    //else()

    if (!tokenPath) {
      if (tokenLocalStorage) {
        token = tokenLocalStorage;
      } else {
        localStorage.removeItem("trello_token");
        authorizeUser();
      }
    } else {
      token = tokenPath;
    }
    if (token) {
      //save user with the token
      saveUser({ token })
        .unwrap()
        .then((res) => {
          localStorage.setItem("trello_token", token);
          localStorage.setItem("fullName", res.name);
          localStorage.setItem("userId", res.id);
          router.push("boards/list");
        })
        .catch((err) => {
          localStorage.removeItem("trello_token");
          console.log({ err });

          authorizeUser();
        });
    }
  }, []);
  return (
    <StyledRedirect>
      <NextSeo title="Redirect" />
      <StyledRedirectText>
        <LoadingAnimation />
        <p>You will be redirect to board&apos;s page in a few moments</p>
      </StyledRedirectText>
    </StyledRedirect>
  );
};

export default RedirectPage;
