import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { loginSuccess } from "../../reduxStore/authSlice";
import "./LoginForm.css";

const LoginForm = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
  });
  const [errorMessage, setErrorMessage] = useState("");
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [view, setView] = useState("login"); // 'login', 'forgot', 'reset'

  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();

  // Check for reset token in URL
  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const token = queryParams.get("token");
    if (token) {
      setView("reset");
    }
  }, [location]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: "" });
    setErrorMessage("");
  };

  const validateForm = () => {
    let isValid = true;
    const newErrors = {};

    switch (view) {
      case "login":
        if (!formData.username.trim()) {
          newErrors.username = "Username is required";
          isValid = false;
        }
        if (!formData.password) {
          newErrors.password = "Password is required";
          isValid = false;
        }
        break;

      case "forgot":
        if (!formData.email.trim()) {
          newErrors.email = "Email is required";
          isValid = false;
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
          newErrors.email = "Invalid email address";
          isValid = false;
        }
        break;

      case "reset":
        if (!formData.password) {
          newErrors.password = "Password is required";
          isValid = false;
        } else if (formData.password.length < 6) {
          newErrors.password = "Password must be at least 6 characters";
          isValid = false;
        }
        break;

      default:
        console.error(`Unexpected view state: ${view}`);
        isValid = false;
        break;
    }

    setErrors(newErrors);
    return isValid;
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setIsSubmitting(true);

    try {
      const response = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const userData = await response.json();
        dispatch(loginSuccess(userData));

        // Handle redirection based on role
        const redirectPath =
          {
            ADMIN: "/adminDashboard",
            GYM_OWNER: "/homeOwner",
            MEMBER: "/homeMember",
          }[userData.role] || "/";

        navigate(redirectPath);
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.message || "Login failed");
      }
    } catch (error) {
      setErrorMessage("Connection error. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleForgotPassword = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setIsSubmitting(true);

    try {
      const response = await fetch(
        "http://localhost:8081/api/auth/forgot-password",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email: formData.email }),
        }
      );

      if (response.ok) {
        setErrorMessage("Password reset instructions sent to your email");
        setFormData({ ...formData, email: "" });
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.message || "Failed to send reset email");
      }
    } catch (error) {
      setErrorMessage("Connection error. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setIsSubmitting(true);

    const token = new URLSearchParams(location.search).get("token");

    try {
      const response = await fetch(
        "http://localhost:8081/api/auth/reset-password",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            token,
            newPassword: formData.password,
          }),
        }
      );

      if (response.ok) {
        setErrorMessage("Password reset successfully. You can now login.");
        setView("login");
        setFormData({ username: "", password: "", email: "" });
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.message || "Password reset failed");
      }
    } catch (error) {
      setErrorMessage("Connection error. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  const renderForm = () => {
    switch (view) {
      case "login":
        return (
          <form onSubmit={handleLogin}>
            <div className="login-form-group">
              <label htmlFor="username">Username:</label>
              <input
                type="text"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                placeholder="Enter Username"
              />
              {errors.username && (
                <p className="error-message">{errors.username}</p>
              )}
            </div>
            <div className="login-form-group">
              <label htmlFor="password">Password:</label>
              <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Enter Password"
              />
              {errors.password && (
                <p className="error-message">{errors.password}</p>
              )}
            </div>
            <button type="submit" className="login-btn" disabled={isSubmitting}>
              {isSubmitting ? "Logging In..." : "Login"}
            </button>
          </form>
        );

      case "forgot":
        return (
          <form onSubmit={handleForgotPassword}>
            <div className="login-form-group">
              <label htmlFor="email">Email:</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Enter your email"
              />
              {errors.email && <p className="error-message">{errors.email}</p>}
            </div>
            <button type="submit" className="login-btn" disabled={isSubmitting}>
              {isSubmitting ? "Sending..." : "Send Reset Link"}
            </button>
          </form>
        );

      case "reset":
        return (
          <form onSubmit={handleResetPassword}>
            <div className="login-form-group">
              <label htmlFor="password">New Password:</label>
              <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Enter new password"
              />
              {errors.password && (
                <p className="error-message">{errors.password}</p>
              )}
            </div>
            <button type="submit" className="login-btn" disabled={isSubmitting}>
              {isSubmitting ? "Resetting..." : "Reset Password"}
            </button>
          </form>
        );

      default:
        return null;
    }
  };

  return (
    <div className="login-form-container">
      <div className="login-form-wrapper">
        {isAuthenticated ? (
          <p>You are already logged in!</p>
        ) : (
          <>
            <h2>
              {view === "login" && "Login"}
              {view === "forgot" && "Forgot Password"}
              {view === "reset" && "Reset Password"}
            </h2>

            {renderForm()}

            <div className="login-links">
              {view === "login" ? (
                <>
                  <p className="login-link">
                    Forgot Password?{" "}
                    <Link to="#" onClick={() => setView("forgot")}>
                      Click Here
                    </Link>
                  </p>
                  <p className="login-link">
                    Want to register as a Gym Owner? <br />
                    <Link to="/signupOwner">Sign Up</Link>
                  </p>
                </>
              ) : view === "forgot" ? (
                <p className="login-link">
                  Remember your password?{" "}
                  <Link to="#" onClick={() => setView("login")}>
                    Login Here
                  </Link>
                </p>
              ) : (
                <p className="login-link">
                  <Link to="#" onClick={() => setView("login")}>
                    Back to Login
                  </Link>
                </p>
              )}
            </div>

            {errorMessage && (
              <p className="error-message" aria-live="polite">
                {errorMessage}
              </p>
            )}
          </>
        )}
      </div>
    </div>
  );
};

export default LoginForm;
