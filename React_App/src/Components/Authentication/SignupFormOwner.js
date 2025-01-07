import React, { useState } from "react";
import { Link } from "react-router-dom"; // Importing Link for navigation
import "./LoginForm.css";

const SignupFormOwner = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    contact: "",
    aadhar: "",
    roleId: 2, // Default role as Gym Owner (id: 2)
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Handle form input changes
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      const response = await fetch("http://localhost:8081/api/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setSuccessMessage("Sign-up successful! You can now log in.");
        setFormData({
          username: "",
          email: "",
          password: "",
          contact: "",
          aadhar: "",
          roleId: 2, // Resetting the role to Gym Owner after successful signup
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
    <div className="login-form-container">
      <div className="login-form-wrapper">
        <h2>Register as a Gym Owner</h2>
        <br />
        <form onSubmit={handleSubmit}>
          <div className="login-form-group">
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
          <div className="login-form-group">
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
          <div className="login-form-group">
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
          <div className="login-form-group">
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
          <div className="login-form-group">
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
          <button type="submit" className="login-btn" disabled={isSubmitting}>
            {isSubmitting ? "Signing Up..." : "Sign Up"}
          </button>
        </form>

        {successMessage && (
          <p className="login-success-message">{successMessage}</p>
        )}
        <p className="login-link">
          Already have an account? <Link to="/">Login here</Link>
        </p>
      </div>
    </div>
  );
};

export default SignupFormOwner;
