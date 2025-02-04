import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
// import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation
import HeaderOwner from "./HeaderOwner";
// import SignupFormMember from "../Authentication/SignupFormMember";

const HomeOwner = () => {
  // const navigate = useNavigate();

  // Retrieve authentication values from Redux
  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);

  // Fallback to localStorage if Redux state is missing (e.g., on page refresh)
  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

  const [gymProfiles, setGymProfiles] = useState(null);

  useEffect(() => {
    // Define async function to fetch gym profiles
    const fetchGymProfiles = async () => {
      try {
        const response = await fetch(
          `http://localhost:8081/api/gym-profile/gym-profiles/${userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            credentials: "include",
          }
        );

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log("Fetched Gym Profiles:", data);
        // Ensure data is an array; if not, wrap it in an array
        setGymProfiles(Array.isArray(data) ? data : [data]);
      } catch (error) {
        console.error("Error fetching gym profiles:", error);
        // Set to an empty array to finish loading and avoid indefinite "checking" state
        setGymProfiles([]);
      }
    };

    // Only fetch if both userId and token are available
    if (userId && token) {
      fetchGymProfiles();
    } else {
      console.error("Missing userId or token");
    }
  }, [userId, token]);

  // Determine if any gym profile has the status "APPROVED"
  const isAnyGymApproved = gymProfiles
    ? gymProfiles.some((profile) => profile.status === "APPROVED")
    : false;

  const handleAddMemberClick = () => {
    console.log("Add Member button clicked");
    if (gymProfiles === null) {
      alert("Please wait while we verify your gym's approval status.");
      return;
    }
    if (!isAnyGymApproved) {
      alert("None of your gyms are approved yet. You cannot add members.");
      return;
    }
    // If at least one gym is approved, navigate to the Add Member page
    // navigate("/addMember");
  };

  return (
    <div className="home-owner-container">
      <HeaderOwner
        onAddMemberClick={handleAddMemberClick}
        isApprovalChecked={gymProfiles !== null} // Indicates whether the gym profiles have finished loading
      />

      <div className="content-container">
        {gymProfiles !== null && !isAnyGymApproved && (
          <p style={{ color: "red", textAlign: "center" }}>
            None of your gym profiles are approved. Some features may be
            restricted.
          </p>
        )}
        {gymProfiles === null && (
          <p style={{ textAlign: "center" }}>Checking approval status...</p>
        )}
      </div>
    </div>
  );
};

export default HomeOwner;
