import { createSlice } from "@reduxjs/toolkit";

// src/reduxStore/authSlice.js
const authSlice = createSlice({
  name: "auth",
  initialState: {
    isAuthenticated: Boolean(localStorage.getItem("isAuthenticated")),
    user: JSON.parse(localStorage.getItem("user")) || null,
    token: localStorage.getItem("token") || null,
    userId: null, // Add userId to the initial state
  },
  reducers: {
    loginSuccess: (state, action) => {
      state.isAuthenticated = true;
      state.user = action.payload;
      state.token = action.payload.token;
      state.userId = action.payload.userId; // Store userId in the state

      localStorage.setItem("isAuthenticated", "true");
      localStorage.setItem("user", JSON.stringify(action.payload));
      localStorage.setItem("token", action.payload.token);
      localStorage.setItem("userId", action.payload.userId); // Store userId in localStorage
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.user = null;
      state.token = null;
      state.userId = null; // Clear userId on logout

      localStorage.removeItem("isAuthenticated");
      localStorage.removeItem("user");
      localStorage.removeItem("token");
      localStorage.removeItem("userId"); // Clear userId on logout
    },
  },
});

export const { loginSuccess, logout } = authSlice.actions;

export default authSlice.reducer;
