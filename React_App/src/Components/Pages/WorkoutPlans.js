import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import "./WorkoutPlans.css";

const WorkoutPlans = () => {
  const [formData, setFormData] = useState({
    planName: "",
    duration: "",
    description: "",
  });

  const [workoutPlans, setWorkoutPlans] = useState([]); // State to store workout plans
  const [successMessage, setSuccessMessage] = useState(""); // State for success/error message

  const userId = useSelector((state) => state.auth.userId); // Assuming user ID is stored in Redux state

  useEffect(() => {
    const fetchWorkoutPlans = async () => {
      try {
        const response = await fetch("http://localhost:8213/api/workout-plans");
        if (response.ok) {
          const data = await response.json();
          setWorkoutPlans(data);
        } else {
          throw new Error("Failed to fetch workout plans");
        }
      } catch (error) {
        console.error("Error fetching workout plans:", error);
      }
    };

    fetchWorkoutPlans();
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8213/api/workout-plans", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to create workout plan");
      }

      // Set success message on successful creation
      setSuccessMessage("Workout plan created successfully!");

      // Optionally, clear the form data after successful submission
      setFormData({
        planName: "",
        duration: "",
        description: "",
      });
    } catch (error) {
      console.error("Error:", error);
      setSuccessMessage(
        error.message || "An error occurred while creating workout plan"
      );
    }
  };

  return (
    <div className="workout-plans-container">
      <div className="workout-plans-wrapper">
        <h2>Add Workout Plan</h2>
        <form onSubmit={handleSubmit}>
          <div className="workout-plans-form-group">
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
          <div className="workout-plans-form-group">
            <label htmlFor="duration">Duration (in weeks):</label>
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
          <div className="workout-plans-form-group">
            <label htmlFor="description">Description:</label>
            <textarea
              id="description"
              name="description"
              value={formData.description}
              onChange={handleChange}
              placeholder="Enter Description"
              required
            />
          </div>
          <button type="submit" className="workout-plans-btn">
            Submit
          </button>
        </form>

        {/* Display success/error message */}
        {successMessage && (
          <p className="workout-plans-success-message">{successMessage}</p>
        )}

        <Link to="/ownerDashboard" className="dashboard-link">
          Back to Dashboard
        </Link>
      </div>
    </div>
  );
};

export default WorkoutPlans;
