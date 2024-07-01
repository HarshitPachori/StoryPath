import React from "react";
import { Heart } from "react-feather";

const Footer = () => {
  return (
    <div className="bg-gray-300 dark:bg-gray-900 text-gray-800 dark:text-gray-300    py-2 px-2 ">
      <h1 className="text-center">Copyright &copy; StoryPath 2024</h1>
      <p className="text-center">
        Made with
        <span>
          <Heart className="inline-block h-4 fill-red-500 stroke-red-500" />
        </span>
        by Harshit Pachori
      </p>
    </div>
  );
};

export default Footer;
