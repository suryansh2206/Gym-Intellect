import { createSlice } from "@reduxjs/toolkit";

// src/reduxStore/authSlice.js
const authSlice = createSlice({
  name: "auth",
  initialState: {
    isAuthenticated: Boolean(localStorage.getItem("isAuthenticated")), // Load state from localStorage
    user: JSON.parse(localStorage.getItem("user")) || null,
  },
  reducers: {
    loginSuccess: (state, action) => {
      state.isAuthenticated = true;
      state.user = action.payload;
      state.userId = action.payload.userId;

      // Save to localStorage
      localStorage.setItem("isAuthenticated", true);
      localStorage.setItem("user", JSON.stringify(action.payload));
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.user = null;

      // Clear localStorage
      localStorage.removeItem("isAuthenticated");
      localStorage.removeItem("user");
    },
  },
});

export const { loginSuccess, logout } = authSlice.actions;

export default authSlice.reducer;
