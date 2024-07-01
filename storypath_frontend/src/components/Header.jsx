import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import {
  toggleNavMenu,
  toggleProfileOpen,
  toggleTheme,
} from "../redux/slice/appSlice";
import { BookOpen, Menu, Moon, Sun, User, X } from "react-feather";
import UserProfile from "./UserProfile";
import { JournalIconLogo } from "../assets";

const Header = () => {
  const isLoggedIn = useSelector((store) => store.auth.token != null);
  const { isDarkMode, isNavOpen, isProfileModalOpen } = useSelector(
    (store) => store.app
  );
  const dispatch = useDispatch();
  const themeModeHandler = () => {
    dispatch(toggleTheme());
    if (isNavOpen) dispatch(toggleNavMenu());
    if (isProfileModalOpen) dispatch(toggleProfileOpen());
  };

  const navMenuVisibilityHandler = () => {
    dispatch(toggleNavMenu());
    if (isProfileModalOpen) dispatch(toggleProfileOpen());
  };

  const profileModalVisibilityHandler = () => {
    dispatch(toggleProfileOpen());
    if (isNavOpen) dispatch(toggleNavMenu());
  };
  return (
    <nav className="bg-lightPrimary dark:bg-darkPrimary text-lightText  dark:text-darkText py-4 sticky top-0 left-0 right-0 z-10 ">
      <div className="flex justify-between container px-5">
        <span>
          <Link to={"/"}>
            {/* <BookOpen /> */}
            <img src={JournalIconLogo} alt="" className="h-10" />
          </Link>
        </span>
        <div className="flex gap-x-10 justify-between ">
          <div className="relative">
            <ul
              className={`${
                isNavOpen ? "right-0" : "-right-full"
              } flex flex-col sm:flex-row items-center justify-center sm:justify-end fixed top-0 bottom-0   sm:static w-[70%] sm:w-auto bg-gray-300/80 dark:bg-gray-900/80 gap-x-5 gap-y-2 mt-[64px] sm:mt-0 transition-all duration-300 z-20 `}
            >
              <li className="px-2 py-1">
                <Link to={"/"}>Home</Link>
              </li>
              <li className="px-2 py-1">
                <Link to={"/about"}>About</Link>
              </li>
              <li className="px-2 py-1">
                <Link to={"/contact"}>Contact</Link>
              </li>
            </ul>
          </div>

          <div className="flex gap-x-5">
            {!isLoggedIn && (
              <button className="border border-gray-700 dark:border-gray-400 rounded-lg px-2 py-1 ">
                <Link to={"/login"}>Login</Link>
              </button>
            )}
            {isLoggedIn && (
              <button
                className="px-2  border border-gray-800 dark:border-gray-300 rounded-full"
                onClick={profileModalVisibilityHandler}
              >
                <User size={20} />
              </button>
            )}
            <button onClick={themeModeHandler}>
              {isDarkMode ? <Sun /> : <Moon />}
            </button>
            <button
              onClick={navMenuVisibilityHandler}
              className="sm:hidden px-2 py-1"
            >
              {isNavOpen ? <X /> : <Menu />}
            </button>
          </div>
        </div>
      </div>

      {
        // <div
        //   className={`${
        //     isProfileModalOpen ? "top-0" : "-top-full"
        //   } fixed top-0 bottom-0 w-full h-[90vh] bg-gray-300/90 dark:bg-gray-900/90 mt-[64px] z-10 transition-all duration-300 `}
        // >
        <div
          className={`${
            isProfileModalOpen ? "top-0" : "top-full"
          } flex flex-col  items-center  fixed top-0 bottom-0  w-full  bg-gray-200/90 dark:bg-gray-800/90  mt-[64px]  transition-all duration-300 z-10 overflow-scroll`}
        >
          <UserProfile />
        </div>
      }
    </nav>
  );
};

export default Header;
