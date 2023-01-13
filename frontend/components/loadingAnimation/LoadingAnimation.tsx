import type { FC } from "react";
import Lottie from "react-lottie";
import animationData from "./loading-animation.json";
const LoadingAnimation: FC = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: animationData,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };
  return (
    <div>
      <Lottie height={100} width={100} options={defaultOptions} />
    </div>
  );
};

export default LoadingAnimation;
