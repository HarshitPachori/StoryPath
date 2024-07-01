import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axiosInstance from "../../axiosInstance";
import { BASE_URL } from "../../appConstant";

export const createNewJournalEntry = createAsyncThunk(
  "journal/createJournal",
  async (data, { rejectWithValue }) => {
    try {
      const response = await axiosInstance.post(`${BASE_URL}/journal`, data);
      return response.data;
    } catch (error) {
      console.error(error);
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
  journal: null,
  error: null,
};

const journalSlice = createSlice({
  name: "journals",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(createNewJournalEntry.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createNewJournalEntry.fulfilled, (state, action) => {
        state.loading = false;
        state.journal = action.payload.data;
        state.error = null;
      })
      .addCase(createNewJournalEntry.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error;
      });
  },
});

export const {} = journalSlice.actions;
export default journalSlice.reducer;
