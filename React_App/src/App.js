import { Route, Routes, Navigate } from "react-router-dom"; // Import Navigate for redirection
import AddGymProfile from "./Components/Pages/AddGymProfile"; // Import AddGymProfile component
import "./App.css";
import LoginForm from "./Components/Authentication/LoginForm";
import { useSelector } from "react-redux"; // Import useSelector to access the Redux state
import HomeOwner from "./Components/Pages/HomeOwner";
import SignupFormOwner from "./Components/Authentication/SignupFormOwner";
import HomeMember from "./Components/Pages/HomeMember";
import HomeAdmin from "./Components/Pages/HomeAdmin";

function App() {
  // Access authentication state from Redux
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

  return (
    <Routes>
      {/* Redirect from root to /login */}
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<LoginForm />} />
      <Route path="/signupOwner" element={<SignupFormOwner />} />

      {/* Protected Route */}
      <Route
        path="/ownerDashboard"
        element={
          isAuthenticated ? <HomeOwner /> : <Navigate to="/login" replace />
        }
      />
      <Route
        path="/addGymProfile"
        element={
          isAuthenticated ? <AddGymProfile /> : <Navigate to="/login" replace />
        }
      />
      <Route
        path="/memberDashboard"
        element={
          isAuthenticated ? <HomeMember /> : <Navigate to="/login" replace />
        }
      />
      <Route
        path="/adminDashboard"
        element={
          isAuthenticated ? <HomeAdmin /> : <Navigate to="/login" replace />
        }
      />
    </Routes>
  );
}

export default App;
