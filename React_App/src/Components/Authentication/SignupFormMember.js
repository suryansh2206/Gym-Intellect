import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./SignupMember.css";
import { useSelector } from "react-redux";

const SignupFormMember = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    contact: "",
    aadhar: "",
    dob: "",
    gender: "Male", // Default value
    address: "",
    height: "",
    planId: "", // For Workout Plan
    membershipPlanId: "", // For Membership Plan
    gymProfileId: "", // For Gym Profile
  });

  const [workoutPlans, setWorkoutPlans] = useState([]);
  const [membershipPlans, setMembershipPlans] = useState([]); // Added state for membership plans
  const [gymProfiles, setGymProfiles] = useState([]);
  const [successMessage, setSuccessMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);

  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

  useEffect(() => {
    const fetchWorkoutPlans = async () => {
      try {
        const response = await fetch(
          "http://localhost:8212/api/member/workout-plans",
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          }
        );
        if (response.ok) {
          const data = await response.json();
          setWorkoutPlans(data);
          console.log("Workout Plans:", data);
        } else {
          throw new Error("Failed to fetch workout plans");
        }
      } catch (error) {
        console.error("Error fetching workout plans:", error);
      }
    };

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
        setGymProfiles(Array.isArray(data) ? data : [data]);
        console.log("Fetched Gym Profiles:", data);
      } catch (error) {
        console.error("Error fetching gym profiles:", error);
      }
    };

    fetchWorkoutPlans();
    fetchGymProfiles();
  }, [userId, token]);

  // Fetch membership plans when gymProfileId changes
  useEffect(() => {
    const fetchMembershipPlans = async () => {
      if (formData.gymProfileId) {
        try {
          const response = await fetch(
            `http://localhost:8212/api/member/plans?gymProfileId=${formData.gymProfileId}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
              },
            }
          );

          if (response.ok) {
            const data = await response.json();
            setMembershipPlans(data);
            console.log("Membership Plans:", data);
          } else {
            throw new Error("Failed to fetch membership plans");
          }
        } catch (error) {
          console.error("Error fetching membership plans:", error);
        }
      }
    };

    fetchMembershipPlans();
  }, [formData.gymProfileId, token]);

  // Handle form input changes
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    // Extract only the required fields
    const dataToSend = {
      username: formData.username,
      email: formData.email,
      password: formData.password,
      contact: formData.contact,
      aadhar: formData.aadhar,
      roleId: formData.roleId,
    };

    // Log the data to check what's being sent
    console.log("Data being sent to API:", dataToSend);

    try {
      const response = await fetch("http://localhost:8212/api/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dataToSend),
      });

      if (response.ok) {
        setSuccessMessage("Member added successfully");

        // Log the data before sending it to the second API
        console.log(
          "Data being sent to second API (Full Registration):",
          formData
        );

        try {
          const fullResponse = await fetch(
            "http://localhost:8212/api/member/register",
            {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(formData),
            }
          );

          if (!fullResponse.ok) {
            throw new Error("Failed to submit full registration data.");
          }
          console.log("Full registration data sent successfully!");
        } catch (fullError) {
          console.error("Error in full registration:", fullError);
        }

        // Reset only the required form fields
        setFormData({
          username: "",
          email: "",
          password: "",
          contact: "",
          aadhar: "",
          dob: "",
          gender: "Male",
          address: "",
          height: "",
          planId: "",
          membershipPlanId: "",
          gymProfileId: "",
        });
      } else {
        throw new Error("Failed to submit the form.");
      }
    } catch (error) {
      console.error("Error:", error);
      setSuccessMessage("Error submitting the form. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="signup-form-container">
      <div className="signup-form-wrapper">
        <h2>Register a Member</h2>
        <br />
        <form onSubmit={handleSubmit}>
          {/* First Column: Basic Information */}
          <div className="signup-form-group">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              required
              placeholder="Enter Username"
            />
          </div>
          <div className="signup-form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
              placeholder="Enter Email"
            />
          </div>
          <div className="signup-form-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
              placeholder="Enter Password"
            />
          </div>
          <div className="signup-form-group">
            <label htmlFor="contact">Contact:</label>
            <input
              type="text"
              id="contact"
              name="contact"
              value={formData.contact}
              onChange={handleChange}
              required
              placeholder="Enter Contact"
            />
          </div>
          <div className="signup-form-group">
            <label htmlFor="aadhar">Aadhar:</label>
            <input
              type="text"
              id="aadhar"
              name="aadhar"
              value={formData.aadhar}
              onChange={handleChange}
              required
              placeholder="Enter Aadhar"
            />
          </div>

          {/* Second Column: Additional Personal Details */}
          <div className="signup-form-group">
            <label htmlFor="dob">Date of Birth:</label>
            <input
              type="date"
              id="dob"
              name="dob"
              value={formData.dob}
              onChange={handleChange}
              required
            />
          </div>
          <div className="signup-form-group">
            <label htmlFor="gender">Gender:</label>
            <select
              id="gender"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              required
            >
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>
          <div className="signup-form-group">
            <label htmlFor="height">Height (cm):</label>
            <input
              type="number"
              id="height"
              name="height"
              value={formData.height}
              onChange={handleChange}
              required
              placeholder="Enter Height"
            />
          </div>

          {/* Third Column: Address and Plan Selection */}
          <div className="signup-form-group">
            <label htmlFor="address">Address:</label>
            <textarea
              id="address"
              name="address"
              value={formData.address}
              onChange={handleChange}
              required
              placeholder="Enter Address"
            />
          </div>

          {/* New Select Box for Gym Profiles */}
          <div className="signup-form-group">
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

          <div className="signup-form-group">
            <label htmlFor="membershipPlanId">Membership Plan:</label>
            <select
              id="membershipPlanId"
              name="membershipPlanId"
              value={formData.membershipPlanId}
              onChange={handleChange}
              required
            >
              <option value="" disabled>
                Select Membership Plan
              </option>
              {membershipPlans.map((plan, index) => (
                <option key={plan.id || index} value={plan.planName}>
                  {plan.planName}
                </option>
              ))}
            </select>
          </div>

          <div className="signup-form-group">
            <label htmlFor="planId">Workout Plan:</label>
            <select
              id="planId"
              name="planId"
              value={formData.planId}
              onChange={handleChange}
              required
            >
              <option value="" disabled>
                Select Workout Plan
              </option>
              {workoutPlans.map((plan, index) => (
                <option key={plan.planId || index} value={plan.planId}>
                  {plan.planName}
                </option>
              ))}
            </select>
          </div>

          <button type="submit" className="signup-btn" disabled={isSubmitting}>
            {isSubmitting ? "Adding Member..." : "Add"}
          </button>
        </form>

        {successMessage && (
          <p className="signup-success-message">{successMessage}</p>
        )}

        {/* Back to Dashboard Link */}
        <Link to="/ownerDashboard" className="dashboard-link">
          Back to Dashboard
        </Link>
      </div>
    </div>
  );
};

export default SignupFormMember;
