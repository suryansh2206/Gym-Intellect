import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import "./AddGymProfile.css";

const AddGymProfile = () => {
  const [formData, setFormData] = useState({
    gymName: "",
    location: "",
    contact: "",
    openHours: "",
    gst: "",
  });

  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const userId = useSelector((state) => state.auth.userId);

  console.log("Authenticated:", isAuthenticated);
  console.log("User ID:", userId);

  const [errorMessage, setErrorMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setErrorMessage("");

    if (!isAuthenticated || !userId) {
      setErrorMessage("Authentication required. Please log in.");
      return;
    }

    // Log the data being sent in the POST request
    console.log("Data being sent:", {
      ...formData,
      userId: userId,
    });

    try {
      const response = await fetch(
        "http://localhost:8212/api/gym-profile/create",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            // No need for Authorization header if using Redux for auth
          },
          body: JSON.stringify({
            ...formData,
            userId: userId,
          }),
        }
      );

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to create gym profile");
      }

      // Alert box for successful submission
      alert("Gym profile created successfully!");

      // Redirect to dashboard after successful creation
      navigate("/ownerDashboard");
    } catch (error) {
      setErrorMessage(
        error.message || "An error occurred while saving gym profile"
      );
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="add-gym-profile-container">
      <div className="add-gym-profile-wrapper">
        <h2>Add Gym Profile</h2>
        {errorMessage && <div className="error-message">{errorMessage}</div>}
        <form onSubmit={handleSubmit}>
          <div className="add-gym-form-group">
            <label htmlFor="gymName">Gym Name:</label>
            <input
              type="text"
              id="gymName"
              name="gymName"
              value={formData.gymName}
              onChange={handleChange}
              placeholder="Enter Gym Name"
              required
            />
          </div>
          <div className="add-gym-form-group">
            <label htmlFor="location">Location:</label>
            <input
              type="text"
              id="location"
              name="location"
              value={formData.location}
              onChange={handleChange}
              placeholder="Enter Location"
              required
            />
          </div>
          <div className="add-gym-form-group">
            <label htmlFor="contact">Contact Number:</label>
            <input
              type="text"
              id="contact"
              name="contact"
              value={formData.contact}
              onChange={handleChange}
              placeholder="Enter Contact Number"
              required
            />
          </div>
          <div className="add-gym-form-group">
            <label htmlFor="openHours">Open Hours:</label>
            <input
              type="text"
              id="openHours"
              name="openHours"
              value={formData.openHours}
              onChange={handleChange}
              placeholder="Enter Open Hours"
              required
            />
          </div>
          <div className="add-gym-form-group">
            <label htmlFor="gst">GST:</label>
            <input
              type="text"
              id="gst"
              name="gst"
              value={formData.gst}
              onChange={handleChange}
              placeholder="Enter GST"
              required
            />
          </div>
          <button type="submit" className="add-gym-btn" disabled={isSubmitting}>
            {isSubmitting ? "Submitting..." : "Submit"}
          </button>
        </form>
        <Link to="/ownerDashboard" className="dashboard-link">
          Back to Dashboard
        </Link>
      </div>
    </div>
  );
};

export default AddGymProfile;
