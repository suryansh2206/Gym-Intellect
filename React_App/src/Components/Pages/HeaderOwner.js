import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "../../reduxStore/authSlice"; // Import the logout action
import "./Header.css";

const HeaderOwner = () => {
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
        <span>GYM INTELLECT</span>
      </div>

      {/* Center: Navigation Options */}
      <div className="nav-container">
        <nav>
          <Link to="/addGymProfile">
            <i className="bi bi-file-earmark-text"></i> Add Gym
          </Link>
          {/* <Link to="/gymProfile">
            <i className="bi bi-file-earmark-text"></i> My Gym Profiles
          </Link> */}
          <Link to="/addMember">
            <i className="bi bi-person-add"></i> Add Member
          </Link>
          <Link to="/addMembershipPlan">
            <i className="bi bi-person-add"></i> Add Membership Plan
          </Link>
          <Link to="/ownerProfile">
            <i className="bi bi-person"></i> Profile
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

export default HeaderOwner;
