import { useEffect, useState } from "react";

const AdminMembers = () => {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:7172/api/members") // Update with your actual API URL
      .then((response) => response.json())
      .then((data) => {
        setMembers(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching members:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">Members List</h2>

      {loading ? (
        <p>Loading members...</p>
      ) : (
        <table className="w-full border-collapse border border-gray-300">
          <thead>
            <tr className="bg-gray-200">
              <th className="border p-2">ID</th>
              <th className="border p-2">Username</th>
              <th className="border p-2">Membership Plan</th>
              <th className="border p-2">Workout Plan</th>
              <th className="border p-2">Gym Name</th>
            </tr>
          </thead>
          <tbody>
            {members.map((member) => (
              <tr key={member.MemberId} className="text-center">
                <td className="border p-2">{member.MemberId}</td>
                <td className="border p-2">{member.Username}</td>
                <td className="border p-2">{member.MembershipPlanName}</td>
                <td className="border p-2">{member.WorkoutPlanName}</td>
                <td className="border p-2">{member.GymProfileName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AdminMembers;
