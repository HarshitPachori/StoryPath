import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navigate, useNavigate } from "react-router-dom";
import PageDivider from "../components/PageDivider";
import { useForm } from "react-hook-form";
import { CircularProgressBar } from "../components/CustomLoader";
import { resetErrorState, verifyAccount } from "../redux/slice/authSlice";
import toast from "react-hot-toast";

const VerifyAccountPage = () => {
  const isLoggedIn = useSelector((state) => state.auth.token !== null);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const { loading, error } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const verifyAccountHandler = async (data) => {
   
    try {
      const response = await dispatch(verifyAccount(data));
     
      if (response.error) {
        toast.error(response.payload.message);
        await new Promise((resolve) => setTimeout(resolve, 5000));
        dispatch(resetErrorState());
      } else {
        if (response.payload.isVerified) {
          toast.success(response.payload.message);
        }
        navigate("/login");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return isLoggedIn ? (
    <Navigate to={"/"} replace />
  ) : (
    <div>
      <h1 className="text-gray-800 dark:text-gray-300 text-xl sm:text-2xl font-semibold text-center py-5 ">
        Email Verification
      </h1>
      <h1 className="text-gray-800 dark:text-gray-300 max-w-sm sm:max-w-max sm:text-lg mx-auto mb-2">
        A verification code is sent to your email. Please fill that code below
        in order to complete your email verification
      </h1>
      {error && (
        <div className="text-red-500 text-sm my-4 font-medium text-center">
          {error}
        </div>
      )}
      <form
        action=""
        onSubmit={handleSubmit(verifyAccountHandler)}
        className="bg-gray-300 dark:bg-gray-900 mx-auto  max-w-sm rounded-lg shadow-lg py-2 px-5 sm:py-5 sm:px-6 my-5 sm:my-10 mb-10 "
      >
        <div className="text-gray-800 dark:text-gray-300 flex flex-col gap-2 mb-5">
          <label
            htmlFor=""
            className="block  font-medium text-gray-900 dark:text-white sm:text-xl py-2"
          >
            Email
          </label>
          <input
            type="email"
            name="email"
            id="email"
            className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5   dark:border-gray-600 dark:placeholder-gray-500 dark:text-gray-800 dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="name@company.com"
            required=""
            {...register("email", {
              required: "Email is required",
              pattern: {
                value:
                  /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
                message: "Please enter a valid email address",
              },
            })}
          />
          {errors.email && (
            <span className="text-red-500 text-sm">{errors.email.message}</span>
          )}
        </div>
        <div className="text-gray-800 dark:text-gray-300 flex flex-col gap-2">
          <label
            htmlFor=""
            className="block  font-medium text-gray-900 dark:text-white sm:text-xl py-2"
          >
            Verification Code
          </label>
          <input
            type="number"
            name="otp"
            id="otp"
            className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5   dark:border-gray-600 dark:placeholder-gray-500 dark:text-gray-800 dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="123456"
            required=""
            {...register("otp", {
              required: "code is required",
            })}
          />
          {errors.otp && (
            <span className="text-red-500 text-sm">{errors.otp.message}</span>
          )}
        </div>
        <button
          type="submit"
          className="w-full text-white  bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm  px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 my-5"
        >
          {loading ? <CircularProgressBar /> : "Submit Code"}
        </button>
      </form>
      <PageDivider />
    </div>
  );
};

export default VerifyAccountPage;
