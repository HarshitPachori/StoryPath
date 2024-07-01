import React from "react";
import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = () => {
  const isLoggedIn = useSelector((state) => state.auth.token !== null);
  return isLoggedIn ? <Outlet /> : <Navigate to={"/login"} replace />;
};

export default PrivateRoute;
