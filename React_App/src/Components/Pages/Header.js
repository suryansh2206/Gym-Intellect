import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { logout } from "../../reduxStore/authSlice"; // Import the logout action
import "./Header.css";

const Header = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // Fetch the username from Redux state
  const username = useSelector((state) => state.auth.user?.username);

  const handleLogout = () => {
    dispatch(logout()); // Dispatch logout action
    navigate("/"); // Redirect to login page
  };

  return (
    <header>
      {/* Left Side: Logo and Project Name */}
      <div className="logo-container">
        <img src="https://via.placeholder.com/40" alt="Logo" />
        <span>GYM INTELLECT</span>
      </div>

      {/* Center: Navigation Options */}
      <div className="nav-container">
        <nav>
          <a href="#workoutPlan">Workout Plan</a>
          <a href="#dietPlan">Diet Plan</a>
          <a href="#progress">Progress</a>
          <a href="#profile">Profile</a>
        </nav>
      </div>

      {/* Right Side: User Greeting and Logout Button */}
      <div className="logout-container">
        <div className="helloUser">Hello, {username}!</div>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};

export default Header;
