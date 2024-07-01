import React from "react";

const CustomLoader = () => {
  return (
    <div className="flex h-[75vh] space-x-2 m-10 items-center justify-center ">
      <div className="h-8 w-8 dark:bg-gray-400 bg-gray-800 rounded-full animate-bounce [animation-delay:-0.15s]"></div>
      <div className="h-8 w-8 dark:bg-gray-400 bg-gray-800 rounded-full animate-bounce [animation-delay:-0.2s]"></div>
      <div className="h-8 w-8 dark:bg-gray-400 bg-gray-800 rounded-full animate-bounce [animation-delay:-0.3s]"></div>
    </div>
  );
};
export const CircularProgressBar = () => {
  return (
    <div className="flex items-center justify-center">
      <div className="h-5 w-5 border-2 border-gray-300 rounded-full animate-spin  border-t-transparent"></div>
    </div>
  );
};
export default CustomLoader;
