import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux"; // Importing useSelector from Redux
import "./AllGymProfiles.css"; // Importing the CSS file for styling
import { Link } from "react-router";

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
      <h1 className="display-4">All Gym Profiles</h1>
      <Link to="/adminDashboard" className="btn btn-outline-secondary">Back to Dashboard</Link>
      {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}
      {loading ? (
        <div>Loading...</div>
      ) : (
        <table className="table table-striped table-bordered">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Gym Name</th>
              <th scope="col">Location</th>
              <th scope="col">Contact</th>
              <th scope="col">Open Hours</th>
              <th scope="col">GST</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            {gymProfiles.map((profile, index) => (
              <tr key={profile.gymProfileId}>
                <th scope="row">{index + 1}</th>
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
