import { createSlice } from "@reduxjs/toolkit";

const app = createSlice({
  name: "app",
  initialState: {
    isDarkMode: localStorage.getItem("isDarkTheme") === "true" || true,
    isNavOpen: false,
    isProfileModalOpen: false,
  },
  reducers: {
    toggleTheme: (state) => {
      state.isDarkMode = !state.isDarkMode;
      localStorage.setItem("isDarkTheme", state.isDarkMode);
    },
    toggleNavMenu: (state) => {
      state.isNavOpen = !state.isNavOpen;
    },
    toggleProfileOpen: (state) => {
      state.isProfileModalOpen = !state.isProfileModalOpen;
    },
  },
});

export const { toggleTheme, toggleNavMenu, toggleProfileOpen } = app.actions;
export default app.reducer;
