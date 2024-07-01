import React from "react";

const JournalFeaturesCard = ({ item }) => {
  return (
    <div className="bg-gray-800 dark:bg-gray-300  rounded-lg flex flex-col gap-1 overflow-hidden shadow-lg p-1">
      <div>
        <img
          src={item.imgUrl}
          alt=""
          className="bg-white dark:bg-gray-800 rounded-t-md h-52 w-60 object-contain"
        />
      </div>
      <div className="flex justify-between items-center px-4 py-2 bg-gray-300 dark:bg-gray-800 text-gray-800 dark:text-gray-300 rounded-b-md">
        <h1 className="font-semibold">{item.icon}</h1>
        <h1 className="text-xl font-medium capitalize">{item.content}</h1>
      </div>
    </div>
  );
};

export default JournalFeaturesCard;
