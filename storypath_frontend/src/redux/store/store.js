import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../slice/authSlice";
import appReducer from "../slice/appSlice";
import userReducer from "../slice/userSlice";
import journalReducer from "../slice/journalSlice"

export const store = configureStore({
  reducer: {
    auth: authReducer,
    app: appReducer,
    user: userReducer,
    journal:journalReducer
  },
});
