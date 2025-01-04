import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { loginSuccess } from "../../reduxStore/authSlice"; // Import the loginSuccess action
import "./LoginForm.css";

const LoginForm = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmitLogin = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      const response = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const userData = await response.json();
        dispatch(loginSuccess(userData)); // Dispatch login success action
        navigate("/homeOwner"); // Redirect to home page after login
      } else {
        setErrorMessage("Invalid username or password.");
      }
    } catch (error) {
      console.error("Login error:", error);
      console.log(error);
      setErrorMessage("Something went wrong. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="form-container">
      <div className="form-wrapper">
        <h2>Login</h2> <br />
        {isAuthenticated ? (
          <p>You are already logged in!</p>
        ) : (
          <form onSubmit={handleSubmitLogin}>
            <div className="form-group">
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
            <div className="form-group">
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
            <button type="submit" className="btn" disabled={isSubmitting}>
              {isSubmitting ? "Logging In..." : "Login"}
            </button>
          </form>
        )}
        <p className="link">
          Forgot Password? <Link to="/forgot-password">Click Here</Link>
        </p>
        <p className="link">
          Do want to register as a Gym Owner? <br /> <Link to="/signupOwner">Sign Up</Link>
        </p>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
      </div>
    </div>
  );
};

export default LoginForm;
