import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { BASE_URL } from "../../appConstant";
import axiosInstance from "../../axiosInstance";

export const userProfile = createAsyncThunk(
  "user/profile",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.get(`${BASE_URL}/user/profile`);
      return response.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        return rejectWithValue(error.response.data.message);
      } else {
        return rejectWithValue("An Error Occurred");
      }
    }
  }
);

export const getAllJournalsByUser = createAsyncThunk(
  "user/allJournals",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.get(`${BASE_URL}/user/journals`);
      return response.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        return rejectWithValue(error.response.data.message);
      } else {
        return rejectWithValue("An Error Occurred");
      }
    }
  }
);

export const updateUserProfile = createAsyncThunk(
  "user/updateProfile",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.put(`${BASE_URL}/user/profile`);
      return response.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        return rejectWithValue(error.response.data.message);
      } else {
        return rejectWithValue("An Error Occurred");
      }
    }
  }
);

const initialState = {
  loading: false,
  user: null,
  journals: [],
  error: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: () => {},
  extraReducers: (builder) => {
    builder
      .addCase(userProfile.pending, (state) => {
        state.loading = true;
        state.error = false;
      })
      .addCase(userProfile.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload.data;
        state.error = null;
      })
      .addCase(userProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error;
      })
      .addCase(getAllJournalsByUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(getAllJournalsByUser.fulfilled, (state, action) => {
        state.loading = false;
        state.journals = action.payload.data;
        state.error = null;
      })
      .addCase(getAllJournalsByUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error;
      })
      .addCase(updateUserProfile.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateUserProfile.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload.data;
        state.error = null;
      })
      .addCase(updateUserProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error;
      });
  },
});

export const {} = userSlice.actions;
export default userSlice.reducer;
