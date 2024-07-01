import { Outlet } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import { useSelector } from "react-redux";
import { Suspense } from "react";
import CustomLoader from "./CustomLoader";

const Layout = () => {
  const isDarkMode = useSelector((state) => state.app.isDarkMode);
  return (
    <div
      className={`${
        isDarkMode ? "dark" : "light"
      } flex flex-col min-h-screen antialiased`}
    >
      <Header />
      <div className=" bg-gray-200 dark:bg-gray-800 flex-grow">
        <Suspense fallback={<CustomLoader />}>
          <Outlet />
        </Suspense>
      </div>
      <Footer />
    </div>
  );
};
export default Layout;
