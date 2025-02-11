import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const AdminMembers = () => {
  const [members, setMembers] = useState([]);
  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);
  const navigate = useNavigate();

  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

//   useEffect(() => {
//     fetch("http://localhost:7172/api/members") // Update with your actual API URL
//       .then((response) => response.json())
//       .then((data) => {
//         setMembers(data);
//         setLoading(false);
//       })
//       .catch((error) => {
//         console.error("Error fetching members:", error);
//         setLoading(false);
//       });
//   }, []);
useEffect(() => {
    const fetchMembers = async () => {
        try {
            console.log(userId);
            const response = await fetch(
                `https://localhost:7172/api/members`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          credentials: "include",
        }
      );

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log("Fetched Gym Profiles:", data);
      setMembers(data);
    } catch (error) {
      console.error("Error fetching gym profiles:", error);
    }
  };

  if (userId && token) {
    fetchMembers();
  } else {
    console.error("Missing userId or token");
  }
}, [userId, token]);

console.log(members.MemberId);

return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-teal-200 to-teal-500 p-6">
      <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-4xl border border-gray-300">
      <button 
          className="absolute top-4 left-4 bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-900 transition duration-300"
          onClick={() => navigate(-1)}
        >
          Back
        </button>
        <h2 className="text-2xl font-bold mb-4 text-center text-gray-800">Members List</h2>
          <table className="w-full border-collapse border border-gray-300">
            <thead>
              <tr className="bg-blue-200"> {/* Light Blue Header */}
                <th className="border p-2">ID</th>
                <th className="border p-2">Username</th>
                <th className="border p-2">Membership Plan</th>
                <th className="border p-2">Workout Plan</th>
                <th className="border p-2">Gym Name</th>
              </tr>
            </thead>
            <tbody>
              {members.map((member) => (
                <tr key={member.memberId} className="text-center hover:bg-gray-100">
                  <td className="border p-2">{member.memberId}</td>
                  <td className="border p-2">{member.username}</td>
                  <td className="border p-2">{member.membershipPlanName}</td>
                  <td className="border p-2">{member.workoutPlanName}</td>
                  <td className="border p-2">{member.gymProfileName}</td>
                </tr>
              ))}
            </tbody>
          </table>
      </div>
    </div>
  );};

export default AdminMembers;
