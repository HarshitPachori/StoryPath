import React, { useState } from "react";

const JournalEntryCard = ({ title, content, tags, date }) => {
  const formattedDate = new Intl.DateTimeFormat("en-IN", {
    weekday: "short",
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    hour12: true,
  }).format(new Date(date));

  const [isExpanded, setIsExpanded] = useState(false); // State to track content expansion

  const handleReadMore = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <div className="bg-lightPrimary dark:bg-darkPrimary mx-4 my-4 sm:my-5 px-5 py-2 sm:py-5 rounded-lg flex flex-col gap-y-2">
      <h1 className="text-base sm:text-lg text-right px-2 text-red-800 dark:text-orange-200 ">
        {formattedDate}
      </h1>
      <h1 className="text-lg text-lightText dark:text-darkText sm:text-xl sm:font-semibold">
        {title}
      </h1>
      <div
        className={`text-base sm:text-base text-lightText/80 dark:text-darkText/80 text-justify overflow-hidden transition-all duration-300 ${
          isExpanded ? "h-auto" : "h-20"
        }`}
      >
        {content}
      </div>
      <ul className="flex flex-wrap gap-y-1 gap-x-2 sm:gap-x-5">
        {tags.map((tag, index) => (
          <span
            key={tag}
            className={`${
              index === 0
                ? "text-pink-600 dark:text-pink-400"
                : index === 1
                ? "text-yellow-600 dark:text-yellow-400"
                : index % 2 === 0
                ? "text-cyan-600 dark:text-cyan-400"
                : index % 3 === 0
                ? "text-orange-600 dark:text-orange-400"
                : index % 5 === 0
                ? "text-green-600 dark:text-green-400"
                : "text-violet-600 dark:text-violet-400"
            }`}
          >
            #{tag}
          </span>
        ))}
      </ul>
      {
        <button
          className={`${
            isExpanded
              ? "rounded-bl-full rounded-br-full"
              : "rounded-tl-full rounded-tr-full"
          } text-orange-800/80 dark:text-orange-200/80 border  border-orange-600/70 dark:border-orange-300/70`}
          onClick={handleReadMore}
        >
          {isExpanded ? "Read less" : "Read more"}
        </button>
      }
    </div>
  );
};

export default JournalEntryCard;
