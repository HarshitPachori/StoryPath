import React from "react";
import { JournalHeroPic } from "../assets";

const AboutPage = () => {
  return (
    <section className=" text-darkText py-1">
      <h1 className="text-xl sm:text-2xl text-center font-bold mb-2 px-5 py-1">
        About Us
      </h1>
      <div className="px-4 sm:px-5 py-1 flex flex-col md:flex-row sm:justify-evenly">
        <div className="py-1">
          <img
            src={JournalHeroPic}
            alt=""
            className="rounded-lg shadow-lg sm:w-[90%]"
          />
        </div>
        <div className=" sm:max-w-[70%] flex flex-col gap-y-5 ">
          <div className="">
            <h1 className="text-xl font-semibold py-1 pt-1">
              Your Journey to Self-Discovery Starts Here Welcome to StoryPath!
            </h1>
            <p className="text-darkText/80 text-justify">
              We're passionate about empowering individuals to explore their
              inner selves, capture life's experiences, and cultivate personal
              growth through journaling.
            </p>
          </div>
          <div className="">
            <h1 className="text-lg font-semibold pt-1 py-1">
              Our Mission: We believe journaling is a powerful tool for:
            </h1>
            <ul className="text-darkText/80 flex flex-col gap-y-2 list-disc pl-5">
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Understanding yourself:{" "}
                </span>
                By reflecting on thoughts, feelings, and experiences, you gain
                valuable insights to your motivations, values, and what truly
                matters to you.
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Processing emotions:{" "}
                </span>
                Journaling provides a safe space to explore and express your
                emotions, leading to greater self-awareness and emotional
                well-being.
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Setting and achieving goals:{" "}
                </span>
                Writing down your goals and tracking your progress keeps you
                motivated and accountable, helping you reach your full
                potential.
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Preserving memories:{" "}
                </span>
                Your journal becomes a personal time capsule, allowing you to
                revisit cherished moments and reflect on your life's journey.
              </li>
            </ul>
          </div>
          <div className="">
            <h1 className="text-lg font-semibold pt-1 py-1">
              What Makes Us Different:
            </h1>
            <ul className="text-darkText/80 flex flex-col gap-y-2 list-disc pl-5">
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Weekly Sentiment Analysis Report:{" "}
                </span>
                We offer weekly sentiment analysis report which will be sent to
                your email every Sunday at 9:00 AM on the basis of your weekly
                journal entries.
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Interactive journaling tools:{" "}
                </span>
                Enhance your journaling experience with features like hashtags,
                photo integration (coming soon), and audio recording
                capabilities (coming soon).
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Community and support:{" "}
                </span>
                Connect with fellow journalers, share your experiences, and find
                inspiration in a supportive online space. (coming soon)
              </li>
              <li className="text-justify">
                <span className="text-darkText/90 font-semibold">
                  Security and privacy:{" "}
                </span>
                We prioritize your privacy and ensure your journal entries are
                safe and secure.
              </li>
            </ul>
          </div>
          <div>
            <h1 className="text-lg font-semibold pt-1 py-1">
              Join Us on Your Journaling Journey:
            </h1>
            <p className="text-darkText/80 text-justify">
              Whether you're a seasoned journaler or just starting out, we're
              here to support you on your path to self-discovery. Sign up for a
              free account today and unlock the power of journaling!
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AboutPage;
