import { Route, Routes, Navigate } from "react-router-dom"; // Import Navigate for redirection
import "./App.css";
import LoginForm from "./Components/Authentication/LoginForm";
import { useSelector } from "react-redux"; // Import useSelector to access the Redux state
import HomeOwner from "./Components/Pages/HomeOwner";
import SignupFormOwner from "./Components/Authentication/SignupFormOwner";

function App() {
  // Access authentication state from Redux
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

  return (
    <Routes>
      {/* Public Routes */}
      <Route path="/" element={<LoginForm />} />
      <Route path="/signupOwner" element={<SignupFormOwner />} />

      {/* Protected Route */}
      <Route
        path="/homeOwner"
        element={isAuthenticated ? <HomeOwner /> : <Navigate to="/" replace />}
      />
    </Routes>
  );
}

export default App;
