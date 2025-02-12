import { Route, Routes, Navigate } from "react-router-dom"; // Import Navigate for redirection
import AddGymProfile from "./Components/Pages/AddGymProfile"; // Import AddGymProfile component
import "./App.css";
import LoginForm from "./Components/Authentication/LoginForm";
import { useSelector } from "react-redux"; // Import useSelector to access the Redux state
import HomeOwner from "./Components/Pages/HomeOwner";
import SignupFormOwner from "./Components/Authentication/SignupFormOwner";
import HomeMember from "./Components/Pages/HomeMember";
import HomeAdmin from "./Components/Pages/HomeAdmin";
import AllGymProfiles from "./Components/Pages/AllGymProfiles"; // Import the AllGymProfiles component
import SignupFormMember from "./Components/Authentication/SignupFormMember"; // Import the new SignupFormMember component
// import GymProfiles from "./Components/Profiles/GymProfiles";
import AddMembershipPlan from "./Components/Pages/AddMembershipPlan";
import OwnerProfile from "./Components/Pages/OwnerProfile";
import MemberProfile from "./Components/Pages/MemberProfile";
import WorkoutPlans from "./Components/Pages/WorkoutPlans";
import WorkoutPlanMember from "./Components/Pages/WorkoutPlanMember";
import WorkoutRoutine from "./Components/Pages/WorkoutRoutine";
import DietPlanMember from "./Components/Pages/DietPlanMember";
import AdminMembers from "./Components/Pages/AdminMembers";

function App() {
  // Access authentication state from Redux
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  // Access user details from Redux state (assuming the user object contains a role property)
  const userRole = useSelector((state) => state.auth.user?.role);

  // Log the userRole to the console for debugging
  console.log("User role:", userRole);

  return (
    <Routes>
      {/* Redirect from root to /login */}
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<LoginForm />} />
      <Route path="/signupOwner" element={<SignupFormOwner />} />

      <Route path="/reset-password" element={<LoginForm />} />

      {/* Protected Route for Signup Member (only for GYM_OWNER) */}
      <Route
        path="/addMember"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <SignupFormMember />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />


<Route
        path="/members"
        element={
          isAuthenticated && userRole === "ADMIN" ? (
            <AdminMembers/>
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      {/* Protected Route for Owner Dashboard (only for GYM_OWNER) */}
      <Route
        path="/ownerDashboard"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <HomeOwner />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      {/* Protected Route for adding Gym Profile (only for GYM_OWNER) */}
      <Route
        path="/addGymProfile"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <AddGymProfile />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="/workoutPlan"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <WorkoutPlans />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="/workoutRoutine"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <WorkoutRoutine />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      {/* Protected Route for adding Gym Profile (only for GYM_OWNER) */}
      {/* <Route
        path="/gymProfile"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <GymProfiles />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      /> */}

      {/* Protected Route for adding Membership Plan (only for GYM_OWNER) */}
      <Route
        path="/addMembershipPlan"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <AddMembershipPlan />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="/ownerProfile"
        element={
          isAuthenticated && userRole === "GYM_OWNER" ? (
            <OwnerProfile />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="/memberprofile"
        element={
          isAuthenticated && userRole === "MEMBER" ? (
            <MemberProfile />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="/workoutplanmember"
        element={
          isAuthenticated && userRole === "MEMBER" ? (
            <WorkoutPlanMember />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

        <Route
        path="/dietplanmember"
        element={isAuthenticated && userRole === "MEMBER" ? (
          <DietPlanMember />
        ) : (
          <Navigate to="/login" replace />
        )}/>  

      {/* Protected Route for Member Dashboard (for any authenticated member) */}
      <Route
        path="/memberDashboard"
        element={
          isAuthenticated ? <HomeMember /> : <Navigate to="/login" replace />
        }
      />

      {/* Protected Route for Admin Dashboard (only for ADMIN) */}
      <Route
        path="/adminDashboard"
        element={
          isAuthenticated && userRole === "ADMIN" ? (
            <HomeAdmin />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      {/* Protected Route for AllGymProfiles accessible only by ADMIN */}
      <Route
        path="/all-gym-profiles"
        element={
          isAuthenticated && userRole === "ADMIN" ? (
            <AllGymProfiles />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
    </Routes>
  );
}

export default App;
