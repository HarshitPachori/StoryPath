import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { X } from "react-feather";
import PageDivider from "../components/PageDivider";
import { useDispatch, useSelector } from "react-redux";
import { createNewJournalEntry } from "../redux/slice/journalSlice";
import toast from "react-hot-toast";
import { CircularProgressBar } from "../components/CustomLoader";
import { getAllJournalsByUser } from "../redux/slice/userSlice";

const JournalPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm();

  const [tags, setTags] = useState([]);

  const dispatch = useDispatch();

  const { loading, error } = useSelector((state) => state.journal);

  const handleTagInput = (event) => {
    const value = event.target.value.trim();
    if (event.key === "Enter" || value === "") return;
    if (event.key === " ") {
      if (tags.includes(value)) return;
      setTags((prevTags) => [...prevTags, value]);
      event.target.value = "";
    }
  };

  const handleTagRemove = (index) => {
    const newTags = [...tags];
    newTags.splice(index, 1);
    setTags(newTags);
  };

  const handleBlur = (event) => {
    event.target.value = "";
  };

  const handleJournalDetailsSubmit = async (data) => {
    data.tags = tags;
    try {
      const response = await dispatch(createNewJournalEntry(data));
      if (response.error) {
        toast.error(response.payload.message);
      } else {
        toast.success(response.payload.message);
        reset();
        setTags([]);
      }
    } catch (error) {
      console.error(error);
    }
  };

   

  return (
    <div className="">
      {/* Create New Journal */}
      <section className="bg-gray-200 dark:bg-gray-800">
        <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto ">
          <div className="w-full bg-gray-300 rounded-lg shadow-md dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-900 dark:border-gray-900">
            <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
              <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                Let's Write A Journal Today
              </h1>
              {error && (
                <div className="text-red-500 text-sm mb-4 font-medium">
                  {error}
                </div>
              )}
              <form
                className="space-y-4 md:space-y-6"
                action="#"
                onSubmit={handleSubmit(handleJournalDetailsSubmit)}
              >
                <div>
                  <label
                    htmlFor="title"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Title
                  </label>
                  <input
                    type="text"
                    name="title"
                    id="title"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="Journal title"
                    required=""
                    {...register("title", { required: "Title is required" })}
                  />
                  {errors.title && (
                    <span className="text-red-500 text-sm">
                      {errors.title.message}
                    </span>
                  )}
                </div>
                <div>
                  <label
                    htmlFor="content"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Content
                  </label>
                  <textarea
                    name="content"
                    id="content"
                    placeholder="Describe your Journal's content here"
                    rows={10}
                    className="bg-
                    
                    -50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 overflow-hidden"
                    required=""
                    {...register("content", {
                      required: "Content is required",
                      minLength: {
                        value: 30,
                        message: "Content must be atleast 30 characters long",
                      },
                    })}
                  />
                  {errors.content && (
                    <span className="text-red-500 text-sm">
                      {errors.content.message}
                    </span>
                  )}
                </div>
                <div>
                  <label
                    htmlFor="title"
                    className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                  >
                    Tags ( press space to add tags )
                  </label>
                  <input
                    type="text"
                    name="tags"
                    id="tags"
                    className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                    placeholder="Journal tags eg: happy, sunny, vibe"
                    required=""
                    onKeyDown={handleTagInput}
                    onBlur={handleBlur}
                    {...register("tags", {})}
                  />
                  {errors.tags && (
                    <span className="text-red-500 text-sm">
                      {errors.tags.message}
                    </span>
                  )}
                  {
                    <ul className="flex flex-wrap gap-2 mt-2">
                      {tags.map((tag, index) => (
                        <li
                          className="bg-blue-300 px-2 py-1 rounded-full gap-x-1 flex items-center cursor-pointer"
                          key={index}
                          onClick={() => handleTagRemove(index)}
                        >
                          {tag}
                          <X className="h-4" />
                        </li>
                      ))}
                    </ul>
                  }
                </div>
                <button
                  type="submit"
                  className="w-full text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                >
                  {loading ? <CircularProgressBar /> : "Create Journal"}
                </button>
              </form>
            </div>
          </div>
        </div>
      </section>
      <PageDivider />
     
    </div>
  );
};

export default JournalPage;
