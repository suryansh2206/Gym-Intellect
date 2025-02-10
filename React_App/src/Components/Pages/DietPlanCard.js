// import React, { useEffect, useState } from "react";
// import { useSelector } from "react-redux";

// const DietPlanCard = () => {

// // //   const { ownerIdRedux } = useSelector((state) => state.auth.userId);
// //   const tokenRedux = useSelector((state) => state.auth.token);
// //   const ownerId = localStorage.getItem("userId");
// //   const token = tokenRedux || localStorage.getItem("token");
// //   const [owner, setOwner] = useState(null);
// //   const [loading, setLoading] = useState(true);
// //   const [error, setError] = useState(null);

  
// //   useEffect(() => {
// //     console.log(localStorage.getItem("userId"));
// //     const fetchOwnerDetails = async () => {
// //       try {
// //         const response = await fetch(`http://localhost:8213/api/owners/${ownerId}`, {
// //           method: "GET",
// //           headers: {
// //             "Authorization": `Bearer ${token}`,
// //             "Content-Type": "application/json"
// //           }
// //         });
// //         if (!response.ok) {
// //           throw new Error("Failed to fetch owner details");
// //         }
// //         const data = await response.json();
// //         setOwner(data);
// //       } catch (error) {
// //         setError(error.message);
// //       } finally {
// //         setLoading(false);
// //       }
// //     };

// //     fetchOwnerDetails();
// //   }, [ownerId, token]);

// //   if (loading) return <p>Loading...</p>;
// //   if (error) return <p>Error: {error}</p>;
// //   if (!owner) return <p>No owner details found.</p>;

//   return (
//     <div className="container">
//       <div className="card">
//         <div className="card_load"></div>
//         <div className="card_load_extreme_title">{owner.name}</div>
//         <div className="card_load_extreme_descripion">
//           <p><strong>Email:</strong> {}</p>
//           <p><strong>Phone:</strong> {}</p>
//           <p><strong>Gym Name:</strong> {}</p>
//           <p><strong>Aadhar Number:</strong> {}</p>
//         </div>
//       </div>
//     </div>
//   );
  
// };

// export default DietPlanCard;
