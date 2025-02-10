import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import "./AddMembershipPlan.css";

const AddMembershipPlan = () => {
  const [formData, setFormData] = useState({
    planName: "",
    duration: "",
    price: "",
    gymProfileId: "", // Added field for Gym Profile ID
  });

  const [gymProfiles, setGymProfiles] = useState([]); // State to store gym profiles
  const [successMessage, setSuccessMessage] = useState(""); // State for success/error message

  // Get userId from Redux state; fall back to localStorage if not available
  const userIdRedux = useSelector((state) => state.auth.userId);
  const userId = userIdRedux || localStorage.getItem("userId");

  useEffect(() => {
    const fetchGymProfiles = async () => {
      if (!userId) return; // Ensure userId is available before making the API call

      try {
        const response = await fetch(
          `http://localhost:8212/api/gym-profile/gym-profiles/${userId}`
        );
        if (response.ok) {
          const data = await response.json();
          setGymProfiles(data);
        } else {
          throw new Error("Failed to fetch gym profiles");
        }
      } catch (error) {
        console.error("Error fetching gym profiles:", error);
      }
    };

    fetchGymProfiles();
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8213/membershipPlans", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(
          errorData.message || "Failed to create membership plan"
        );
      }

      // Set success message on successful creation
      setSuccessMessage("Membership plan created successfully!");

      // Optionally, clear the form data after successful submission
      setFormData({
        planName: "",
        duration: "",
        price: "",
        gymProfileId: "",
      });
    } catch (error) {
      console.error("Error:", error);
      setSuccessMessage(
        error.message || "An error occurred while creating membership plan"
      );
    }
  };

  return (
    <div className="add-membership-plan-container">
      <div className="add-membership-plan-wrapper">
        <h2>Add Membership Plan</h2>
        <form onSubmit={handleSubmit}>
          <div className="add-membership-form-group">
            <label htmlFor="gymProfileId">Gym Profile:</label>
            <select
              id="gymProfileId"
              name="gymProfileId"
              value={formData.gymProfileId}
              onChange={handleChange}
              required
            >
              <option value="" disabled>
                Select Gym Profile
              </option>
              {gymProfiles
                .filter((profile) => profile.status === "APPROVED")
                .map((profile, index) => (
                  <option
                    key={profile.gymProfileId || index}
                    value={profile.gymProfileId}
                  >
                    {profile.gymName}
                  </option>
                ))}
            </select>
          </div>
          <div className="add-membership-form-group">
            <label htmlFor="planName">Plan Name:</label>
            <input
              type="text"
              id="planName"
              name="planName"
              value={formData.planName}
              onChange={handleChange}
              placeholder="Enter Plan Name"
              required
            />
          </div>
          <div className="add-membership-form-group">
            <label htmlFor="duration">Duration (months):</label>
            <input
              type="text"
              id="duration"
              name="duration"
              value={formData.duration}
              onChange={handleChange}
              placeholder="Enter Duration"
              required
            />
          </div>
          <div className="add-membership-form-group">
            <label htmlFor="price">Price:</label>
            <input
              type="text"
              id="price"
              name="price"
              value={formData.price}
              onChange={handleChange}
              placeholder="Enter Price"
              required
            />
          </div>
          <button type="submit" className="add-membership-btn">
            Submit
          </button>
        </form>

        {/* Display success/error message */}
        {successMessage && (
          <p className="add-membership-success-message">{successMessage}</p>
        )}

        <Link to="/ownerDashboard" className="dashboard-link">
          Back to Dashboard
        </Link>
      </div>
    </div>
  );
};

export default AddMembershipPlan;
