import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import HeaderOwner from "./HeaderOwner";
import SignupFormMember from "../Authentication/SignupFormMember";

const HomeOwner = () => {
  const [showSignupForm, setShowSignupForm] = useState(false);
  const [isGymApproved, setIsGymApproved] = useState(false);
  const userId = useSelector((state) => state.auth.userId); // Access userId from Redux
  const token = useSelector((state) => state.auth.token); // Access token from Redux

  useEffect(() => {
    // Fetch gym profile approval status when the component mounts
    const fetchGymApprovalStatus = async () => {
      try {
        const response = await fetch(
          `http://localhost:8081/api/gym-profile/owner/${userId}/is-approved`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`, // Include token in the authorization header
            },
            credentials: "include", // If authentication is needed
          }
        );

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        setIsGymApproved(data);
      } catch (error) {
        console.error("Error fetching gym approval status:", error);
      }
    };

    fetchGymApprovalStatus();
  }, [userId, token]); // Add userId and token as dependencies

  const handleAddMemberClick = () => {
    if (!isGymApproved) {
      alert("Your gym profile is not approved yet. You cannot add members.");
      return;
    }
    setShowSignupForm(!showSignupForm);
  };

  return (
    <div className="home-owner-container">
      <HeaderOwner onAddMemberClick={handleAddMemberClick} />

      <div className="content-container">
        {!isGymApproved && (
          <p style={{ color: "red", textAlign: "center" }}>
            Your gym profile is pending approval. Some features may be
            restricted.
          </p>
        )}

        {showSignupForm && (
          <div className="signup-form-container">
            <SignupFormMember />
          </div>
        )}
      </div>
    </div>
  );
};

export default HomeOwner;
