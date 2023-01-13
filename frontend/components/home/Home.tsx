import React, { CSSProperties, FC, useEffect, useState } from "react";
import { useRouter } from "next/router";
import {
  homeStyleContainer,
  linkStyle,
  subLinkStyle,
  descStyle,
  botStyle,
  trelloPresStyle,
  jidokaStyle,
  StyledPattern,
  StyledFooter,
} from "./Home.style";
import Image from "next/image";
import { APPLICATION_KEY } from "~application.info";
import { REDIRECT_URL } from "~env";
const Home: FC = () => {
  const router = useRouter();

  // const { authorize } = useLazyAuthorizeQuery();
  const handleContinueWithTrello = () => {
    //check if the user is already connected
    const token = localStorage.getItem("trello_token");
    if (token) {
      router.push("/redirect");
    } else {
      router.push(
        `https://trello.com/1/authorize?expiration=never&name=JidokaBot&scope=read&response_type=fragment&key=${APPLICATION_KEY}&return_url=${REDIRECT_URL}`,
      );
    }
  };
  return (
    <div style={homeStyleContainer as CSSProperties}>
      <StyledPattern>
        <Image src={"/Pattern.png"} layout={"fill"} />
      </StyledPattern>
      <div style={descStyle as CSSProperties}>
        <h1 style={jidokaStyle}>
          Jidoka
          <span style={botStyle}> Bot</span>
        </h1>
        <h2 style={trelloPresStyle}>TRELLO&apos;S BEST FRIEND TO SUCCEED IN YOUR SCRUM PROJECT</h2>
        <div style={linkStyle as CSSProperties}>
          <div style={subLinkStyle} onClick={handleContinueWithTrello}>
            <div>Continue with Trello </div>
            <div>
              <img src="/arrow-continue.svg" />
            </div>
          </div>
        </div>
      </div>
      <StyledFooter>
        <Image src={"/Pattern.png"} layout={"fill"} />
      </StyledFooter>
    </div>
  );
};

export default Home;
