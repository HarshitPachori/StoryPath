import React from "react";
import { ContactUsIconPic } from "../assets";

const ContactPage = () => {
  return (
    <div className="flex flex-col md:flex-row  md:items-center md:justify-evenly py-5">
      <div className="md:w-[30%]">
        <img src={ContactUsIconPic} alt="contact us" className="" />
      </div>
      <div className="w-full bg-gray-300 rounded-lg shadow-md dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-900 dark:border-gray-900">
        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 className="md:text-2xl font-semibold text-gray-900 dark:text-white ">
            Contact Us
          </h1>
          <form className="space-y-4 md:space-y-6" action="#">
            <div>
              <label
                htmlFor="email"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Your mail
              </label>
              <input
                type="email"
                name="email"
                id="email"
                className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5   dark:border-gray-600 dark:placeholder-gray-500 dark:text-gray-800 dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="name@mail.com"
                required=""
              />
              {/* {errors.email && (
              <span className="text-red-500 text-sm">
                {errors.email.message}
              </span>
            )} */}
            </div>
            <div>
              <label
                htmlFor="content"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Content
              </label>
              <textarea
                type="content"
                name="content"
                id="content"
                placeholder="I am john doe . . . . .  ."
                rows={8}
                className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:border-gray-600 dark:placeholder-gray-500 dark:text-gray-800 dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required=""
              />
              {/* {errors.password && (
              <span className="text-red-500 text-sm">
                {errors.password.message}
              </span>
            )} */}
            </div>
            <button
              type="submit"
              className="w-full text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            >
              Contact
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ContactPage;
