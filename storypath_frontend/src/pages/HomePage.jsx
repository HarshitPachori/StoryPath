import React from "react";
import { Mail, Smartphone } from "react-feather";
import { EmailNotifyPic, JournalHeroPic, SentimentPic } from "../assets";
import JournalFeaturesCard from "../components/JournalFeaturesCard";
import PageDivider from "../components/PageDivider";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const journalFeatures = [
  {
    id: 1,
    content: "email notification",
    icon: <Mail />,
    imgUrl: EmailNotifyPic,
  },
  {
    id: 2,
    content: "sentiment analysis",
    icon: <Smartphone />,
    imgUrl: SentimentPic,
  },
  {
    id: 3,
    content: "sentiment report",
    icon: <Mail />,
    imgUrl: EmailNotifyPic,
  },
];
const HomePage = () => {
  const navigate = useNavigate();
  const isLoggedIn = useSelector((state) => state.auth.token !== null);
  const handleCtaBtnClick = () => {
    if (isLoggedIn) {
      navigate("/journal");
    } else {
      navigate("/login");
    }
  };
  return (
    <div className="py-5 sm:py-10 flex flex-col gap-y-20">
      <div className="container mx-auto flex flex-col-reverse md:flex-row py-5 sm:py-10 px-4 sm:px-5 w-full justify-between gap-y-5 sm:gap-y-0 ">
        <div className="flex flex-col  justify-center md:max-w-lg lg:max-w-xl gap-y-2 md:gap-y-5">
          <h1 className="text-2xl md:text-5xl font-semibold text-gray-800 dark:text-gray-300">
            Empower Your Journey: Write, Reflect, Grow{" "}
          </h1>
          <p className="text-lg  text-gray-800 dark:text-gray-400">
            Your personal sanctuary for thoughts, feelings, and experiences.
            Cultivate self-awareness, unleash creativity, and achieve your goals
            through the power of journaling.
          </p>
          <div>
            <button
              className="border border-gray-600 dark:border-gray-500 rounded-md text-gray-800 dark:text-gray-300 px-2 py-2 font-semibold"
              onClick={handleCtaBtnClick}
            >
              Start Your Journal Today
            </button>
          </div>
        </div>
        <div>
          <img
            src={JournalHeroPic}
            alt=""
            className="rounded-lg overflow-hidden shadow-lg"
          />
        </div>
      </div>
      <PageDivider />
      <div className="mt-5 mb-10">
        <h1 className="text-center text-4xl text-gray-800 dark:text-gray-300 font-semibold py-2">
          Key Features
        </h1>
        <div className="flex flex-wrap justify-center items-center gap-5 mt-10">
          {journalFeatures.map((item) => (
            <JournalFeaturesCard item={item} key={item.id} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
