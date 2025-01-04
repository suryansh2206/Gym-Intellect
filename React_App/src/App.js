import { Route, Routes, Navigate } from "react-router-dom"; // Import Navigate for redirection
import "./App.css";
import LoginForm from "./Components/Authentication/LoginForm";
import HomeMember from "./Components/Pages/HomeMember";
import SignupForm from "./Components/Authentication/SignupForm";
import { useSelector } from "react-redux"; // Import useSelector to access the Redux state

function App() {
  // Access authentication state from Redux
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

  return (
    <Routes>
      {/* Public Routes */}
      <Route path="/" element={<LoginForm />} />
      <Route path="/signup" element={<SignupForm />} />

      {/* Protected Route */}
      <Route
        path="/home"
        element={isAuthenticated ? <HomeMember /> : <Navigate to="/" replace />}
      />
    </Routes>
  );
}

export default App;
