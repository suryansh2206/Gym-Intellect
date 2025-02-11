import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import "./OwnerProfile.css";
// Import the GymProfiles component (adjust the path as needed)
import GymProfiles from "../Profiles/GymProfiles";

const OwnerProfile = () => {
  const tokenRedux = useSelector((state) => state.auth.token);
  const ownerId = localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");
  const [owner, setOwner] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    console.log("Owner ID from localStorage:", ownerId);
    const fetchOwnerDetails = async () => {
      try {
        const response = await fetch(
          `http://localhost:8213/api/owners/${ownerId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch owner details");
        }
        const data = await response.json();
        setOwner(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchOwnerDetails();
  }, [ownerId, token]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!owner) return <p>No owner details found.</p>;

  return (
    <div className="container">
      <Link
        to="/ownerDashboard"
        className="btn btn-outline-secondary custom-back-button"
      >
        Back to Dashboard
      </Link>
      <div className="card_profile">
        <div className="profile-pic">
          <img src={require("../../assets/image.png")} alt="Profile" />
        </div>
        <div className="card_load_extreme_descripion">
          <p>
            <strong>Name:</strong> {owner.username}
          </p>
          <p>
            <strong>Email:</strong> {owner.email}
          </p>
          <p>
            <strong>Phone:</strong> {owner.contact}
          </p>
          <p>
            <strong>Aadhar Number:</strong> {owner.aadhar}
          </p>
        </div>
      </div>
      {/* Gym Profiles Section */}
      <div className="gym-profiles-section">
        {/* Render GymProfiles component below the owner profile card */}
        <GymProfiles />
      </div>
    </div>
  );
};

export default OwnerProfile;
