import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "../../reduxStore/authSlice"; // Import the logout action
import "./Header.css";

const HeaderAdmin = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // Fetch the username from Redux state
  const username = useSelector((state) => state.auth.user?.username);

  const handleLogout = () => {
    dispatch(logout()); // Dispatch logout action
    navigate("/login"); // Redirect to login page (adjust the path if needed)
  };

  return (
    <header className="header-admin">
      {/* Left Side: Logo and Project Name */}
      <div className="logo-container">
        <img src="https://via.placeholder.com/40" alt="Logo" />
        <span>GYM INTELLECT</span>
      </div>

      {/* Center: Navigation Options */}
      <div className="nav-container">
        <nav>
          {/* Updated the link path to match the route for AllGymProfiles */}
          <Link to="/all-gym-profiles">
            <i className="bi bi-file-earmark-text"></i> Gym Profiles
          </Link>
          <Link to="/members">
            <i className="bi bi-file-earmark-text"></i> Members
          </Link>
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

export default HeaderAdmin;
