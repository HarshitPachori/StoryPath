import React, { useEffect, useState } from "react";
import { Check, Edit, X } from "react-feather";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../redux/slice/authSlice";
import {
  getAllJournalsByUser,
  updateUserProfile,
  userProfile,
} from "../redux/slice/userSlice";
import toast from "react-hot-toast";
import { toggleProfileOpen } from "../redux/slice/appSlice";
import PageDivider from "./PageDivider";
import JournalEntryCard from "./JournalEntryCard";
import CustomLoader from "../components/CustomLoader";

const UserProfile = () => {
  const { loading, error, user, journals } = useSelector((state) => state.user);
  const { isProfileModalOpen } = useSelector((state) => state.app);
  const isLoggedIn = useSelector((state) => state.auth.token !== null);
  const dispatch = useDispatch();
  const [wantSentimentDialog, setWantSentimentDialog] = useState(false);

  const userLogoutHandler = () => {
    dispatch(toggleProfileOpen());
    dispatch(logout());
  };

  const getUserProfileData = async () => {
    try {
      const response = await dispatch(userProfile());
      console.log(response);
      if (response.error && response?.payload?.message) {
        toast.error(response.payload.message);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const getUserJournalsData = async () => {
    try {
      const response = await dispatch(getAllJournalsByUser());
      if (response.error && response?.payload?.message) {
        toast.error(response.payload.message);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const updateProfileHandler = async () => {
    try {
      const response = await dispatch(updateUserProfile());
      console.log(response);
      if (response.error && response?.payload?.message) {
        toast.error(response.payload.message);
      } else {
        toast.success(response.payload.message);
      }
      setWantSentimentDialog(false);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    if (isLoggedIn && isProfileModalOpen) {
      getUserProfileData();
      getUserJournalsData();
    }
  }, [isProfileModalOpen]);

  if (loading) {
    return (
      <>
        <CustomLoader />
      </>
    );
  }
  return (
    // <div className="bg-gray-100 dark:bg-gray-900">
    <div className="w-full shadow-lg">
      <section className="flex flex-col items-center gap-y-3">
        <div className="flex flex-col justify-center items-center mt-5">
          {error && (
            <div className="text-red-500 text-sm mb-4 font-medium">
              {error.message === "Rejected"
                ? "Something Went Wrong"
                : error.message}
            </div>
          )}
          <img
            src="https://img.freepik.com/premium-vector/man-avatar-profile-round-icon_24640-14044.jpg?w=360"
            alt="default profile picture"
            className="h-32 w-32 rounded-full border border-gray-800 dark:border-gray-300"
          />
          <h1 className="mt-2">{user?.username}</h1>
        </div>
        <div className="text-center">
          <h1>
            Email : <span>{user?.email}</span>
          </h1>
        </div>
        <div className="flex items-center gap-2 ">
          <h1 className="flex gap-2">
            Wants Sentiment Analysis
            <span>{user?.wantsSentimentAnalysis ? <Check /> : <X />}</span>
          </h1>
          <button onClick={() => setWantSentimentDialog(true)}>
            <Edit className="h-4" />
          </button>
        </div>
        <div className="text-center">
          <button
            className="border rounded-md px-5 py-1 border-gray-600 dark:border-gray-400"
            onClick={userLogoutHandler}
          >
            Log out
          </button>
        </div>
      </section>
      {wantSentimentDialog && (
        <div className="flex justify-center items-center transition-all duration-300">
          <div className="bg-white  absolute z-10 rounded-lg px-5 py-5">
            <h1 className="dark:text-gray-800 pb-5 sm:text-lg">
              Do you want to change your preference ?
            </h1>
            <div className="flex justify-between">
              <button
                className="bg-red-500 flex-grow rounded-l-lg px-2 py-2 my-1 font-semibold text-gray-200"
                onClick={updateProfileHandler}
              >
                Yes
              </button>
              <button
                className="bg-blue-500 flex-grow rounded-r-lg px-2 py-2 my-1 font-semibold text-gray-200"
                onClick={() => setWantSentimentDialog(false)}
              >
                No
              </button>
            </div>
          </div>
        </div>
      )}
      <PageDivider />
      {/* Display My Journals */}
      <section className="py-1 px-1 sm:px-5">
        {journals !== null ? (
          journals?.map((journal) => (
            <JournalEntryCard
              key={journal.id.timestamp + journal.id.date}
              {...journal}
            />
          ))
        ) : (
          <p className="text-center text-gray-500">No journals found.</p>
        )}
      </section>
    </div>
  );
};

export default UserProfile;
