import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import HeaderOwner from "./HeaderOwner";
import "./HomeOwner.css";

const HomeOwner = () => {
  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);

  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

  const [gymProfiles, setGymProfiles] = useState(null);

  useEffect(() => {
    const fetchGymProfiles = async () => {
      try {
        const response = await fetch(
          `http://localhost:8212/api/gym-profile/gym-profiles/${userId}`,
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
        setGymProfiles(Array.isArray(data) ? data : [data]);
      } catch (error) {
        console.error("Error fetching gym profiles:", error);
        setGymProfiles([]);
      }
    };

    if (userId && token) {
      fetchGymProfiles();
    } else {
      console.error("Missing userId or token");
    }
  }, [userId, token]);

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
  };

  return (
    <div className="home-owner-container">
      {/* Keep the Header Unchanged */}
      <HeaderOwner
        onAddMemberClick={handleAddMemberClick}
        isApprovalChecked={gymProfiles !== null}
      />

      {/* Main Content */}
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

      {/* Cards Container - NEW ADDITION */}
      <div className="cards-container">
        <div className="card">
          <img className="card-img-top" src={require("../../assets/Diet Plan 2.png")} alt="Add Diet Plan" />
        </div>

        <div className="card">
          <img className="card-img-top" src={require("../../assets/workout1.jpg")} alt="Add workout Plan" />
        </div>
      </div>
    </div>
  );
};

export default HomeOwner;
