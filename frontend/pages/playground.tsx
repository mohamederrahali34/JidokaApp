import * as React from "react";

import { SITE_CONFIG } from "~config/site";
import { useAppDispatch, useAppSelector } from "~store";
import { decrement, increment, incrementByAmount, selectCount } from "~store/counter/slice";
import type { NextPage } from "~types/next";

import { NextSeo } from "next-seo";

const PlaygroundPage: NextPage = () => {
  const dispatch = useAppDispatch();
  const count = useAppSelector(selectCount);
  const [incrementAmount, setIncrementAmount] = React.useState<number>(0);
  return (
    <div>
      <NextSeo title="Playground" />

      <h1>{SITE_CONFIG.title}</h1>
      <h2>{SITE_CONFIG.description}</h2>

      <h1>Welcome to the greatest app in the world!</h1>
      <h2>
        The current number is
        {count}
      </h2>
      <div>
        <input
          value={incrementAmount}
          onChange={(e) => {
            setIncrementAmount(Number(e.target.value));
          }}
          type="number"
        />
        <button onClick={() => dispatch(incrementByAmount(Number(incrementAmount)))}>Increment by amount</button>
      </div>
      <div>
        <button onClick={() => dispatch(decrement())}>Decrement by 1</button>
        <button onClick={() => dispatch(increment())}>Increment by 1</button>
      </div>
    </div>
  );
};

export default PlaygroundPage;
