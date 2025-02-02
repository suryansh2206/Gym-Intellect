import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux"; // Importing useSelector from Redux
import "./AllGymProfiles.css"; // Importing the CSS file for styling

const AllGymProfiles = () => {
  const [gymProfiles, setGymProfiles] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(true);

  // Access the token from Redux state
  const token = useSelector((state) => state.auth.token);

  // Fetch all gym profiles
  const fetchAllGymProfiles = async () => {
    setLoading(true);
    if (!token) {
      setErrorMessage("No valid token found. Please log in again.");
      setLoading(false);
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8081/api/admin/gym-profiles",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        setGymProfiles(data);
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
    fetchAllGymProfiles();
  }, [token]); // Depend on token to refetch when it changes

  return (
    <div>
      <h1>All Gym Profiles</h1>
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
            </tr>
          </thead>
          <tbody>
            {gymProfiles.map((profile) => (
              <tr key={profile.gymProfileId}>
                <td>{profile.gymName}</td>
                <td>{profile.location}</td>
                <td>{profile.contact}</td>
                <td>{profile.openHours}</td>
                <td>{profile.gst}</td>
                <td>{profile.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AllGymProfiles;
