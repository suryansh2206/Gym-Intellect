import { createSlice } from "@reduxjs/toolkit";

// src/reduxStore/authSlice.js
const authSlice = createSlice({
  name: "auth",
  initialState: {
    isAuthenticated: Boolean(localStorage.getItem("isAuthenticated")),
    user: JSON.parse(localStorage.getItem("user")) || null,
    token: localStorage.getItem("token") || null, // Ensure token is retrieved from localStorage
  },
  reducers: {
    loginSuccess: (state, action) => {
      state.isAuthenticated = true;
      state.user = action.payload;
      state.token = action.payload.token;

      localStorage.setItem("isAuthenticated", "true");
      localStorage.setItem("user", JSON.stringify(action.payload));
      localStorage.setItem("token", action.payload.token); // Store token in localStorage
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.user = null;
      state.token = null;

      localStorage.removeItem("isAuthenticated");
      localStorage.removeItem("user");
      localStorage.removeItem("token"); // Clear token on logout
    },
  },
});

export const { loginSuccess, logout } = authSlice.actions;

export default authSlice.reducer;
