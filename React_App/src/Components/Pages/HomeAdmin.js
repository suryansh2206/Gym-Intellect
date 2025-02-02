import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux"; // Importing useSelector from Redux
import HeaderAdmin from "./HeaderAdmin"; // Importing HeaderAdmin
import { useNavigate } from "react-router-dom"; // For navigation

const HomeAdmin = () => {
  const [pendingProfiles, setPendingProfiles] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate(); // Hook for navigation

  // Access the token from Redux state
  const token = useSelector((state) => state.auth.token);
  console.log("Token:", token); // Log the token to check its value

  // Fetch pending gym profiles
  const fetchPendingProfiles = async () => {
    setLoading(true);
    if (!token) {
      setErrorMessage("No valid token found. Please log in again.");
      setLoading(false);
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8081/api/admin/gym-profiles-pending",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        setPendingProfiles(data);
      } else if (response.status === 401) {
        setErrorMessage("Session expired. Please log in again.");
        navigate("/login"); // Redirect to login page
      } else {
        throw new Error("Failed to fetch gym profiles.");
      }
    } catch (error) {
      setErrorMessage(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPendingProfiles();
  }, [token]); // Depend on token to refetch when it changes

  const handleApprove = async (id) => {
    try {
      const response = await fetch(
        `http://localhost:8081/api/admin/gym-profiles/${id}/approve`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        fetchPendingProfiles(); // Refresh the list
      } else {
        throw new Error("Failed to approve gym profile.");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  const handleReject = async (id) => {
    try {
      const response = await fetch(
        `http://localhost:8081/api/admin/gym-profiles/${id}/reject`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        fetchPendingProfiles(); // Refresh the list
      } else {
        throw new Error("Failed to reject gym profile.");
      }
    } catch (error) {
      setErrorMessage(error.message);
    }
  };

  return (
    <div>
      <HeaderAdmin />
      <h1>Pending Gym Profiles</h1>

      {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}

      {loading ? (
        <div>Loading...</div>
      ) : (
        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr>
              <th>Gym Name</th>
              <th>Location</th>
              <th>Contact</th>
              <th>Open Hours</th>
              <th>GST</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {pendingProfiles.map((profile) => (
              <tr key={profile.gym_profile_id}>
                <td>{profile.gym_name}</td>
                <td>{profile.location}</td>
                <td>{profile.contact}</td>
                <td>{profile.open_hours}</td>
                <td>{profile.gst}</td>
                <td>{profile.status}</td>
                <td>
                  {profile.status === "PENDING" ? (
                    <>
                      <button
                        onClick={() => handleApprove(profile.gym_profile_id)}
                      >
                        Approve
                      </button>
                      <button
                        onClick={() => handleReject(profile.gym_profile_id)}
                      >
                        Reject
                      </button>
                    </>
                  ) : (
                    <span>{profile.status}</span>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default HomeAdmin;
