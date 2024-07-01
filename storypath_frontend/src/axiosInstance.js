import axios from "axios";
import { BASE_URL } from "./appConstant";
import toast from "react-hot-toast";

const instance = axios.create({
  baseURL: BASE_URL,
});

//  intereptor to attch jwt token to each request using axiosinstance

instance.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// interceptor to handle 401 unauthorized error and redirection to login page

instance.interceptors.response.use(
  (response) => response,
  async (error) => {
    console.error(error);
    if (error?.code && error.code === "ERR_NETWORK") {
      toast.error(error.message);
      return;
    }
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("accessToken");
      window.location.href("/login");
    } else {
      return Promise.reject(error);
    }
  }
);

export default instance;
