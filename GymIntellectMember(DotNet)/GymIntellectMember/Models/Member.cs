using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GymIntellectMember.Models
{
    public class Member
    {
        [Key]
        [Column("member_id")]
        public int MemberId { get; set; }

        [Column("user_id")]
        public int UserId { get; set; }
        [Column("membership_id")]
        public int? MembershipId { get; set; }

        [Column("plan_id")]
        public int? PlanId { get; set; }  // This should map to WorkoutPlan's PlanId

        [Column("gym_profile_id")]
        public int? GymProfileId { get; set; }

        // Define Relationships
        [ForeignKey("UserId")]
        public User User { get; set; }

        [ForeignKey("MembershipId")]
        public MembershipPlan MembershipPlan { get; set; }

        [ForeignKey("PlanId")]
        public WorkoutPlan WorkoutPlan { get; set; }  // Explicitly defining PlanId as foreign key

        [ForeignKey("GymProfileId")]
        public GymProfile GymProfile { get; set; }
    }

}
